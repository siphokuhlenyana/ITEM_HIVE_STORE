package za.ac.cput.itemhivesd2.domain;

import java.time.LocalDateTime;
/*
Transaction.java
Transaction model class
Author:Athini Ngquke(222352302)
Date: 15 March 2026
 */
public class CurrentDateGenerator {
    private static CurrentDateGenerator instance;
    //private String pdate = "2026-03-17";

    private CurrentDateGenerator() {

    }

    public static synchronized CurrentDateGenerator getInstance() {
        if (instance == null) {
            instance = new CurrentDateGenerator();

        }
        return instance;
    }

    public LocalDateTime getPdate() {
        return LocalDateTime.now();
    }
}
