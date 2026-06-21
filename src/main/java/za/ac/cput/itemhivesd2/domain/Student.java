package za.ac.cput.domain;

/* Student.java
   Student Builder
   Author: Mihle Nosilela
   Date: 21 June 2026
*/
// Student entity implementation
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {

    @Id
    private String studentID;

    private String name;
    private String email;
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Listing> listings = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<ChatMessage> messages = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "reportedBy", cascade = CascadeType.ALL)
    private List<Flag> flags = new ArrayList<>();

    protected Student() {}

    private Student(Builder builder) {
        this.studentID = builder.studentID;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Listing> getListings() {
        return listings;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public List<Flag> getFlags() {
        return flags;
    }

    public static class Builder {
        private String studentID;
        private String name;
        private String email;
        private String password;

        public Builder studentID(String studentID) {
            this.studentID = studentID;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
