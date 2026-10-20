package za.ac.cput.itemhivesd2.domain;
/*
SellerIdGenerator.java
SellerID Generator model class
Author:Athini Ngquke(222352302)
Date: 15 March 2026
 */
public class SellerIdGenerator {
    private static SellerIdGenerator instance;
    private final String sellerId = "S";
    private int num = 1000;

    private SellerIdGenerator(){

    }
    public static SellerIdGenerator getInstance(){
        if(instance == null){
            instance = new SellerIdGenerator();

        }
        return instance;
    }
    public String getSellerId(){
        num++;
        return sellerId+num;
    }
}
