# How to optimize
* use task executor to define parallel threads count.

have a bean like this

```java
@Bean
    public TaskExecutor taskExecutor(){
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);
        return asyncTaskExecutor;
    }
```
specify the execturor in the step definition like this
```java
@Bean
    public Step importStep(){
        return new StepBuilder("csvImport",jobRepository)
                .<Student,Student>chunk(10,platformTransactionManager)
                .reader(itemReader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor()) //this line
                .build();
    }
```
It reduced the processing time to 1/6th

* Another way is to increase chunk size. 
```java

@Bean
    public Step importStep(){
        return new StepBuilder("csvImport",jobRepository)
                .<Student,Student>chunk(100,platformTransactionManager)
                .reader(itemReader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }
```
increasing chunk size increased the efficiency by 10-15%

Further increase of efficiency can be done using parition