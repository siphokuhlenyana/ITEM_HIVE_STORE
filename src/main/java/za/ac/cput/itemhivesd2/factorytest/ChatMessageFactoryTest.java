package za.ac.cput.itemhivesd2.factorytest;
/*Chat Message Factory Test Class
Kelly Pauline Nolte(218275358)
Date:25.July.2026*/

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import za.ac.cput.itemhivesd2.domain.ChatMessage;
import za.ac.cput.itemhivesd2.factory.ChatMessageFactory;

public class ChatMessageFactoryTest {

    // create a message using the factory
    @Test
    void createMessage() {

        ChatMessage message = ChatMessageFactory.createMessage(
                "Paul",
                "Sam",
                "cook books",
                "Hi, is this item still available?"
        );

        // checking if the message was created
        assertNotNull(message);

        // check the fields
        assertEquals("Paul", message.getSenderID());
        assertEquals("Sam", message.getReceiverID());
        assertEquals("cook books", message.getListingID());
        assertEquals("Hi, is this item still available?", message.getMessageText());
        assertEquals("SENT", message.getStatus());

    } // end of createMessage test method

} // end of class
