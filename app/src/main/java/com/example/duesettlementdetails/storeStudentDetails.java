package com.example.duesettlementdetails;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class storeStudentDetails implements Serializable {


    private String studentName;
    private  String rollNo;
    private  String book;
    private  String email;
    private Double fine;
    private String dept;
    private String id;


    public storeStudentDetails() {
    }

    public storeStudentDetails(String studentName, String rollNo,String book, double fine ,String dept,String email) {


        this.studentName = studentName;
        this.rollNo = rollNo;
        this.book = book;
        this.fine = fine;
        this.dept = dept;
        this.email = email;


    }

    public void setId(String id) {
        this.id = id;
    }





    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getBook() {
        return book;
    }

    public Double getFine() {
        return fine;
    }

    public String getDept() {
        return dept;
    }

    public String getId() {
        return id;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFine(Double fine) {
        this.fine = fine;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }
}
