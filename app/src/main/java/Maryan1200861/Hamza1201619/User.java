package Maryan1200861.Hamza1201619;

import androidx.annotation.NonNull;

public class User {

    // firstname, lastname, email, gender, phone, hashed_password

    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String gender;
    private String phone;
    private String hashed_password;

    public User(String firstname, String lastname, String email, String gender, String phone, String hashed_password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.hashed_password = hashed_password;
    }

    public User() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHashedPassword() {
        return hashed_password;
    }

    public void setHashedPassword(String hashed_password) {
        this.hashed_password = hashed_password;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "\nID= " + id +
                "\nFirstname= " + firstname +
                "\nLastname= " + lastname +
                "\nEmail= " + email + '\n';

    }
}
