package za.ac.cput.domain;
/*
BuyerIdGenerator.java
Buyer ID Generator model class
Author:Athini Ngquke(222352302)
Date: 15 March 2026
 */
public class BuyerIdGenerator {
    private static BuyerIdGenerator instance;
    private final String buyerId = "B";
    private int num = 1000;

    private BuyerIdGenerator(){

    }
    public static BuyerIdGenerator getInstance(){
        if(instance == null){
            instance = new BuyerIdGenerator();
        }
        return instance;
    }
    public String getBuyerId(){
        num++;
        return buyerId+num;
    }
}
