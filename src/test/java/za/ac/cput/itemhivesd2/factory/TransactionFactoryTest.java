package za.ac.cput.transactionentity.factoryTest;

import org.junit.jupiter.api.Test;
import za.ac.cput.transactionentity.domain.Transaction;
import za.ac.cput.transactionentity.factory.TransactionFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TransactionFactoryTest {
    @Test
    void TransactionFactory() {

        Transaction record = TransactionFactory.createTransaction(
               "TR-25363",
                "BI-36363",
                "SI-37363",
                "LI-73633",
                65,
                653.48,
                12000.00,
                null


        );
        assertNotNull(record);
        assertTrue(record instanceof Transaction);
        assertEquals("TR-25363", record.getTransactionId());
        assertEquals("BI-36363", record.getBuyerId());
        assertEquals("SI-37363", record.getSellerId());
        assertEquals("LI-73633", record.getListing());
        assertEquals(63, record.getQuantity());
        assertEquals(653.48, record.getPrice());
        assertEquals(12000.00, record.getTotal());
        assertEquals(null, record.getPdate());

    }

    @Test
    void testTransaction_null() {
        Transaction record = TransactionFactory.createTransaction(
                "TR-25363",
                "BI-36363",
                "SI-37363",
                "LI-73633",
                0,
                653.48,
                12000.00,
                null
        );
        assertNotNull(record);
    }
}