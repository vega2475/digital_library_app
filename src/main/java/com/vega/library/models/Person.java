package com.vega.library.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Incorrect format of Name")
    private String full_name;
    @Min(value = 0, message = "Incorrect!")
    private int yearOfTheBirth;
    @Email
    @NotEmpty(message = "Email cant be empty!")
    private String email;

    public Person(int id, String full_name, int yearOfTheBirth, String email) {
        this.id = id;
        this.full_name = full_name;
        this.yearOfTheBirth = yearOfTheBirth;
        this.email = email;
    }

    public Person() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public int getYearOfTheBirth() {
        return yearOfTheBirth;
    }

    public void setYearOfTheBirth(int yearOfTheBirth) {
        this.yearOfTheBirth = yearOfTheBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
