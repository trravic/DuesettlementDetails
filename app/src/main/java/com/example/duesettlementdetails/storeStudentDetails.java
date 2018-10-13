package com.example.duesettlementdetails;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class storeStudentDetails implements Serializable {


    private String studentName;
    private  String rollNo;
    private  String book;
    private Double fine;
    private String dept;

    @Exclude private String id;

    public storeStudentDetails() {
    }

    public storeStudentDetails(String studentName, String rollNo,String book, double fine ,String dept) {

        this.studentName = studentName;
        this.rollNo = rollNo;
        this.book = book;
        this.fine = fine;
        this.dept = dept;


    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
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
}
