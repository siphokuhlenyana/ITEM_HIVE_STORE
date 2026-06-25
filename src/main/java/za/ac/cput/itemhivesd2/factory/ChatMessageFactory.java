package za.ac.cput.itemhivesd2.factory;
/*Chat Message Factory Class
Kelly Pauline Nolte(218275358)
Date:25.July.2026*/


import za.ac.cput.itemhivesd2.domain.ChatMessage;

import java.time.LocalDateTime;
import java.util.UUID;

public class ChatMessageFactory {

    public static ChatMessage createMessage(
            String senderID,
            String receiverID,
            String listingID,
            String messageText) {

        if (senderID == null || senderID.isEmpty() ||
                receiverID == null || receiverID.isEmpty() ||
                listingID == null || listingID.isEmpty() ||
                messageText == null || messageText.isEmpty()) {
            return null;
        }//end of validation block

        String messageID = UUID.randomUUID().toString();

        return new ChatMessage.Builder()
                .setMessageID(messageID)
                .setSenderID(senderID)
                .setReceiverID(receiverID)
                .setListingID(listingID)
                .setMessageText(messageText)
                .setTimestamp(LocalDateTime.now())
                .setStatus("SENT")
                .build();
    }//end of createMessage method
}//end of class
