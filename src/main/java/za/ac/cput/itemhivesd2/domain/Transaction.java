package za.ac.cput.domain;

import za.ac.cput.util.UtilValidator;

import java.time.LocalDate;
/*
Transaction.java
Transaction model class
Author:Athini Ngquke(222352302)
Date: 15 March 2026
 */
public class Transaction {
    private String transactionId;
    private String buyerId;
    private String sellerId;
    private String listing;
    private double price;
    private LocalDate pdate;

    public Transaction(Builder builder) {
        this.transactionId = builder.transactionId;
        this.buyerId = builder.buyerId;
        this.sellerId = builder.sellerId;
        this.listing = builder.listing;
        this.price = builder.price;
        this.pdate = builder.pdate;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getBuyerId() {

        return buyerId;
    }

    public String getSellerId() {

        return sellerId;
    }

    public String getListing() {

        return listing;
    }

    public double getPrice() {

        return price;
    }

    public LocalDate getPdate() {

        return pdate;
    }

    @Override
    public String toString() {
        return"Transaction [transactionId=" + transactionId + ", buyerId=" + buyerId + ", sellerId=" + sellerId
                + ", listing=" + listing + ", price=" + price + ", pdate=" + pdate + "]";
    }

    public static class Builder {
        private String transactionId;
        private String buyerId;
        private String sellerId;
        private String listing;
        private double price;
        private LocalDate pdate;

        /*public Builder(String transactionId, String buyerId, String sellerId, String listing, double price, LocalDate pdate) {
            this.transactionId = transactionId;
            this.buyerId = buyerId;
            this.sellerId = sellerId;
            this.listing = listing;
            this.price = price;
            this.pdate = pdate;
        }*/
        public Builder setTransactionId(String transactionId) {
            if(transactionId == null || transactionId.isEmpty()){
                throw new IllegalArgumentException("transactionId cannot be null or empty");
            }
            this.transactionId = transactionId;
            return this;
        }
        public Builder setBuyerId(String buyerId) {
            if(buyerId == null || buyerId.isEmpty()){
                throw new IllegalArgumentException("buyerId cannot be null or empty");
            }
            this.buyerId = buyerId;
            return this;
        }
        public Builder setSellerId(String sellerId) {
            if(sellerId == null || sellerId.isEmpty()){
                throw new IllegalArgumentException("sellerId cannot be null or empty");
            }
            this.sellerId = sellerId;
            return this;
        }
        public Builder setListing(String listing) {
            if(listing == null || listing.isEmpty()){
                throw new IllegalArgumentException("listing cannot be null or empty");
            }
            this.listing = listing;
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

        public Builder copy(Transaction trans) {
            this.transactionId = trans.transactionId;
            this.buyerId = trans.buyerId;
            this.sellerId = trans.sellerId;
            this.listing = trans.listing;
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
           // UtilValidator.requireNotNullorEmpty(String.valueOf(price), "The price");

            UtilValidator.requireNoPreviousDate(pdate,"Past date");
            UtilValidator.requireNoFutureDate(pdate,"Future date");
            UtilValidator.requireNotNull(pdate, "pdate");
            UtilValidator.requireNotZero(price, "price");
            return new Transaction(this);
        }


    }


}