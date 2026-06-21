package za.ac.cput.itemhivesd2.domain;

/*Flag Report Domain Class
Kelly Pauline Nolte(218275358)
Date:19.July.2026*/

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class FlagReport {

    @Id
    private String flagID;
    private String studentID;
    private String listingID;
    private String reason;
    private String description;
    private String status;
    private LocalDateTime dateReported;

    //private constructor(Builder pattern)
    private FlagReport(Builder builder){
        this.flagID = builder.flagID;
        this.studentID = builder.studentID;
        this.listingID = builder.listingID;
        this.reason = builder.reason;
        this.description = builder.description;
        this.dateReported = builder.dateReported;
        this.status = builder.status;
    }//end of builder pattern

    //getters


    public String getFlagID() {
        return flagID;
    }//end of get flagID

    public String getStudentID() {
        return studentID;
    }//end of get studentID

    public String getListingID() {
        return listingID;
    }//end of get listingID

    public String getReason() {
        return reason;
    }//end of get reason

    public String getDescription() {
        return description;
    }//end of get description

    public LocalDateTime getDateReported() {
        return dateReported;
    }//end of get dateReported
    public String getStatus(){
        return status;
    }//end of get status

    @Override
    public String toString() {
        return  "FlagReport{" +
                "flagId='" + flagID + '\'' +
                ", studentId='" + studentID + '\'' +
                ", listingId='" + listingID + '\'' +
                ", reason='" + reason + '\'' +
                ", description='" + description + '\'' +
                ", dateReported=" + dateReported +
                ", status='" + status + '\'' +
                '}';
    }//end of override

    //Builder class
    public static class Builder {
        private String flagID;
        private String studentID;
        private String listingID;
        private String reason;
        private String description;
        private String status;
        private LocalDateTime dateReported;

        public Builder setFlagID(String flagID) {
            this.flagID = flagID;
            return this;
        }//end of set flagID

        public Builder setStudentID(String studentID) {
            this.studentID = studentID;
            return this;
        }//end of set StudentId

        public Builder setListingID(String listingID) {
            this.listingID = listingID;
            return this;
        }//end of set listingID

        public Builder setReason(String reason) {
            this.reason = reason;
            return this;
        }//end of set reason

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }//end of set description

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }//end of set status

        public Builder setDateReported(LocalDateTime dateReported) {
            this.dateReported = dateReported;
            return this;
        }//end of set dateReported

        public FlagReport build() {
            if (flagID == null || studentID == null){
                throw new IllegalArgumentException("Flag ID and Student) ID are required");
            }//end of if
            return new FlagReport(this);
        }//end of build
    }//end of builder class
}//end of class
