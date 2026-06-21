package za.ac.cput.itemhivesd2.Domain;


/*Chat Message Domain Class
Kelly Pauline Nolte(218275358)
Date:25.March.2026*/

import java.time.LocalDateTime;


public class ChatMessage {

    private String messageID;
    private String senderID;
    private String receiverID;
    private String listingID;
    private String messageText;
    private String status;
    private LocalDateTime timestamp;



    //private constructor
    private ChatMessage(Builder builder){
        this.messageID = builder.messageID;
        this.senderID = builder.senderID;
        this.receiverID = builder.receiverID;
        this.listingID = builder.listingID;
        this.messageText = builder.messageText;
        this.status = builder.status;
        this.timestamp = builder.timestamp;
    } //end of constructor

    //getters
    public String getSenderID() {
        return senderID;
    }//end of senderID

    public String getMessageID() {
        return messageID;
    }//end of messageID

    public String getReceiverID() {
        return receiverID;
    }//end of receiverID

    public String getListingID() {
        return listingID;
    }//end of listingID

    public String getMessageText() {
        return messageText;
    }//end of messageText

    public String getStatus() {
        return status;
    }//end of status

    public LocalDateTime getTimestamp() {
        return timestamp;
    }//end of timestamp

    //builder class
    public static class Builder{
        private String messageID;
        private String senderID;
        private String receiverID;
        private String listingID;
        private String messageText;
        private String status;
        private LocalDateTime timestamp;

        public Builder setMessageID(String messageID){
            this.messageID = messageID;
            return this;
        }//end of set messageID

        public Builder setSenderID(String senderID) {
            this.senderID = senderID;
            return this;
        }//end of set senderID

        public Builder setReceiverID(String receiverID) {
            this.receiverID = receiverID;
            return this;
        }//end of set receiverID

        public Builder setListingID(String listingID) {
            this.listingID = listingID;
            return this;
        }//end of set listingID

        public Builder setMessageText(String messageText) {
            this.messageText = messageText;
            return this;
        }//end of set messageTest

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }//end of set status

        public Builder setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }//end of ste Timestamp

        public ChatMessage build(){
            if(messageID == null || senderID == null){
                throw new IllegalArgumentException("Message ID and Sender ID are required");
            }//end of if
            return new ChatMessage(this);
        }//end of return
    }//end of builder class
}//end of class