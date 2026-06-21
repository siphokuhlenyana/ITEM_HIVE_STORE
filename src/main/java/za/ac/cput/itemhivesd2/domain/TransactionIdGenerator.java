package za.ac.cput.domain;
/*
TransactionIdGenerator.java
Transaction ID Generator model class
Author:Athini Ngquke(222352302)
Date: 18 March 2026
 */
public class TransactionIdGenerator {
    public static TransactionIdGenerator trans ;
    //public String transactionId = "T1000";
    private final String letter = "TD";
    private int num = 1000;

    private TransactionIdGenerator(){

    }
    public static TransactionIdGenerator getInstance(){
        if(trans == null){
            trans = new TransactionIdGenerator();

        }

        return trans;
    }
    public String getTransactionId(){
        num++;
        return letter + num;
    }
}