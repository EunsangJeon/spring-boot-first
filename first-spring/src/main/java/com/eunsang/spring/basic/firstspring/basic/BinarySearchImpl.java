package com.eunsang.spring.basic.firstspring.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import javax.annotation.PostConstruct;
//import javax.annotation.PreDestroy;

@Component // #3
public class BinarySearchImpl {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    // #2 To give little bit of lose coupling
    //private SortAlgorithm sortAlgorithm;

    // #3 using Spring
    @Autowired
    private SortAlgorithm sortAlgorithm;
    // Technically, it is called "constructor injection".
//    public BinarySearchImpl(SortAlgorithm sortAlgorithm) {
//        super();
//        System.out.println(sortAlgorithm); // to see which object has been passed as parameter
//        this.sortAlgorithm = sortAlgorithm;
//    }

    // Here is setter injection
//    public void setSortAlgorithm(SortAlgorithm sortAlgorithm) {
//        this.sortAlgorithm = sortAlgorithm;
//    }
    // Actually you don't even need to do this.



    public int binarySearch(int[] numbers, int numberToSearchFor) {

        // #1 Let's say that sort logic is located here.
        // BubbleSortAlgorithm bubbleSortAlgorithm = new BubbleSortAlgorithm();
        // int[] sortedNumber = bubbleSortAlgorithm.sort(numbers);
        // We can say that it is tightly coupled.

        // #2
        int[] sortedNumber = sortAlgorithm.sort(numbers);


        // SEARCH LOGIC HERE

        System.out.println(sortAlgorithm);

        return 3;
    }

//    @PostConstruct
//    public void postConstruct() {
//        logger.info("postConstruct");
//    }
//
//    @PreDestroy
//    public void preDestroy() {
//        logger.info("preDestroy");
//    }
}
