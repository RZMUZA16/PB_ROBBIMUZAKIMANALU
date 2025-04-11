package com.example.model.memo_tugas_uts;

public class User {
    public String fullName;
    public String email;
    public String password;
    public String birthDate;
    public String gender;
    public String location;

    // Constructor kosong dibutuhkan oleh Firebase
    public User() {}

    public User(String fullName, String email, String password, String birthDate, String gender, String location) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.gender = gender;
        this.location = location;
    }
}
