package za.ac.cput.itemhivesd2.factory;
/*Flag Report Factory Class
Kelly Pauline Nolte(218275358)
Date:25.July.2026*/


import za.ac.cput.itemhivesd2.domain.FlagReport;

import java.time.LocalDateTime;

public class FlagReportFactory {

    // count for ids
    private static int counter = 1;

    public static FlagReport createFlagReport(
            String studentID,
            String listingID,
            String reason,
            String description
    ) {
        // Validation
        if (studentID == null || studentID.isEmpty()) {
            throw new IllegalArgumentException("Student ID is required");
        } // end of if studentID

        if (listingID == null || listingID.isEmpty()) {
            throw new IllegalArgumentException("Listing ID is required");
        } // end of if listingID

        if (reason == null || reason.isEmpty()) {
            throw new IllegalArgumentException("Reason is required");
        } // end of if reason

        // Auto-generated values
        String flagID = generateID(); // uses counter-based ID
        LocalDateTime dateReported = LocalDateTime.now();
        String status = "Pending";

        // Build object
        return new FlagReport.Builder()
                .setFlagID(flagID)
                .setStudentID(studentID)
                .setListingID(listingID)
                .setReason(reason)
                .setDescription(description)
                .setDateReported(dateReported)
                .setStatus(status)
                .build();

    } // end of createFlagReport method

    // ID generator
    private static String generateID() {
        return "F" + counter++;
    } // end of generateID method

} // end of class
