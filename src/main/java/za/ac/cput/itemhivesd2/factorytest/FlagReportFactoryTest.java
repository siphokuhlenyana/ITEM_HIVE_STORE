package za.ac.cput.itemhivesd2.factorytest;
/*Flag Report Factory Test Class
Kelly Pauline Nolte(218275358)
Date:25.July.2026*/
import org.junit.jupiter.api.Test;
import za.ac.cput.itemhivesd2.domain.FlagReport;
import za.ac.cput.itemhivesd2.factory.FlagReportFactory;

import static org.junit.jupiter.api.Assertions.*;

public class FlagReportFactoryTest {

    // test creating a flag report using factory
    @Test
    void createFlagReport() { // start of test method

        FlagReport report = FlagReportFactory.createFlagReport(
                "S123",
                "L456",
                "Spam content",
                "This listing looks like spam"
        ); // end of factory call

        // check object is created
        assertNotNull(report);

        // check student ID
        assertEquals("S123", report.getStudentID());

        // check listing ID
        assertEquals("L456", report.getListingID());

        // check reason
        assertEquals("Spam content", report.getReason());

        // check description
        assertEquals("This listing looks like spam", report.getDescription());

        // check default status
        assertEquals("Pending", report.getStatus());

    } // end of createFlagReport test method

} // end of class