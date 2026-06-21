package za.ac.cput.domain;
/*
ListingGeneratorId.java
Listing Generator model class
Author:Athini Ngquke(222352302)
Date: 15 March 2026
 */
public class ListingGenerator {
    private static ListingGenerator instance;
    private  final String listing = "L";
    private int num = 1000;

    private ListingGenerator(){

    }
    public static ListingGenerator getInstance(){
        if(instance == null){
            instance = new ListingGenerator();
        }
        return instance;
    }
    public String getListing(){
        num++;
        return listing+num;
    }
}
