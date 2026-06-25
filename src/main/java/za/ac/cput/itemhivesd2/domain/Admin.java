 /* Admin.java
    Admin + Builder
     Author: Siphokuhle Nyana (218123191)
     Date: 21 June 2026
*/ 

package za.ac.cput.itemhivesd2.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    private String  adminID   ;

    private  String name ;
    private String email;
    private String password ;

    
//@JsonBackReference
@JsonIgnore
@OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
private List<Admin> admins = new ArrayList<>();

protected Admin() {}


private Admin(Builder builder) {
    this.adminID   = builder.adminID   ;
    this.name = builder.name;
    this.email = builder.email;
    this.password = builder.password;
}
public String getAdminID() {
    return adminID;
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

public List<Admin> getOrders() {
    return admins;
}

public static class Builder{
    private String  adminID;

    private  String name ;
    private String email;
    private String password ;

    public Builder adminID(String adminID){
        this.adminID = adminID;
        return this;

    }

    public Builder name(String name){
        this.name = name;
        return this;

    }
    public Builder email(String email){
        this.email = email;
        return this;

    }


    public Builder password(String password){
        this.password = password;
        return this;

    }

    public Admin build(){
        return new Admin(this);
    }
}
}
