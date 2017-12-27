package com.example.ignite.models;

public class Student {

    private Integer rollNumber;
    private String firstName;
    private String lastName;

    public Student() {

    }

    public Student(Integer rollNumber, String firstName, String lastName) {

        setRollNumber(rollNumber);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public Integer getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(Integer rollNumber) {
        this.rollNumber = rollNumber;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;

    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;

    }

}
