package za.ac.cput.domain;

import za.ac.cput.util.UtilValidator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
/*
Transaction.java
Transaction entity class
Author:Athini Ngquke(222352302)
Date: 15 March 2026
 */
@Entity
public class Transaction {
    @Id
    private String transactionId;
    
    @ManyToOne
    private Buyer buyerId;

    @ManyToOne
    private Seller sellerId;

    @ManyToOne
    private Listing listing;
    
    private int quantity;
    private double price;
    private double total;
    private LocalDate pdate;

    public Transaction(Builder builder) {
        this.transactionId = builder.transactionId;
        this.buyerId = builder.buyerId;
        this.sellerId = builder.sellerId;
        this.listing = builder.listing;
        this.quantity = builder.quantity;
        this.price = builder.price;
        this.pdate = builder.pdate;
        this.total = builder.total;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Buyer getBuyerId() {

        return buyerId;
    }

    public Seller getSellerId() {

        return sellerId;
    }

    public Listing getListing() {

        return listing;
    }
    public int quantity(){
        
        return quantity;
    }
    public double getPrice() {

        return price;
    }

    public LocalDate getPdate() {

        return pdate;
    }
     public double getTotal(){
         return total;
     }
    public void calculateTotal(){
        total = quantity*price;
    }
    
    @Override
    public String toString() {
        return"Transaction [transactionId=" + transactionId + ", buyerId=" + buyerId + ", sellerId=" + sellerId
                + ", listing=" + listing +", quantity=" + quantity + ", price=" + price + ", pdate=" + pdate +", total=" + total + "]";
    }

    public static class Builder {
        private String transactionId;
        private Buyer buyerId;
        private Seller sellerId;
        private Listing listing;
        private int quantity;
        private double price;
        private LocalDate pdate;
        private double total;

        
        public Builder setTransactionId(String transactionId) {
            if(transactionId == null || transactionId.isEmpty()){
                throw new IllegalArgumentException("transactionId cannot be null or empty");
            }
            this.transactionId = transactionId;
            return this;
        }
        public Builder setBuyerId(Buyer buyerId) {
            if(buyerId == null ){
                throw new IllegalArgumentException("buyerId cannot be null ");
            }
            this.buyerId = buyerId;
            return this;
        }
        public Builder setSellerId(Seller sellerId) {
            if(sellerId == null){
                throw new IllegalArgumentException("sellerId cannot be null ");
            }
            this.sellerId = sellerId;
            return this;
        }
        public Builder setListing(Listing listing) {
            if(listing == null ){
                throw new IllegalArgumentException("listing cannot be null ");
            }
            this.listing = listing;
            return this;
        }
        public Builder setQuantity(int quantity){
            this.quantity = quantity;
            return this;
        }
        public Builder setPrice(double price) {
            this.price = price;
            return this;
        }
        public Builder setPdate(LocalDate pdate) {
            if(pdate == null){
                throw new IllegalArgumentException("pdate cannot be null");
            }
            if(pdate.isBefore(LocalDate.now())){
                throw new IllegalArgumentException("pdate cannot be in the past");
            }
            if(pdate.isAfter(LocalDate.now())){
                throw new IllegalArgumentException("pdate cannot be in the future");
            }
            this.pdate = pdate;
            return this;
        }
        public Builder setTotal(double total){
            this.total = total;
            return this;
        }
        public Builder copy(Transaction trans) {
            this.transactionId = trans.transactionId;
            this.buyerId = trans.buyerId;
            this.sellerId = trans.sellerId;
            this.listing = trans.listing;
            this.quantity = trans.quantity;
            this.price = trans.price;
            this.pdate = trans.pdate;
            return this;
        }
        public Transaction build() {

            this.transactionId = TransactionIdGenerator.getInstance().getTransactionId();
            this.buyerId = BuyerIdGenerator.getInstance().getBuyerId();
            this.sellerId = SellerIdGenerator.getInstance().getSellerId();
            this.listing = ListingGenerator.getInstance().getListing();
            this.pdate = LocalDate.from(CurrentDateGenerator.getInstance().getPdate());

            UtilValidator.requireNotNullorEmpty(transactionId, "Transaction ID");
            UtilValidator.requireNotNullorEmpty(buyerId, "Buyer ID");
            UtilValidator.requireNotNullorEmpty(sellerId, "Seller ID");
            UtilValidator.requireNotNullorEmpty(listing, "Listing");
           

            UtilValidator.requireNoPreviousDate(pdate,"Past date");
            UtilValidator.requireNoFutureDate(pdate,"Future date");
            UtilValidator.requireNotNull(pdate, "pdate");
            UtilValidator.requireNotZero(price, "price");
            UtilValidator.requireNotZero(quantity, "quantity");
            UtilValidator.requireNotZero(total, "total");
                
            return new Transaction(this);
        }


    }


}
