package com.example.duesettlementdetails;

public class storeStudentDetails {

    String id;
    String studentName;
    String RollNo;
    String book;
    String fine;
    String dept;

    public storeStudentDetails(){}

    public storeStudentDetails(String id , String studentName, String RollNo , String book ,  String fine ,String dept){

        this.id=id;
        this.studentName=studentName;
        this.RollNo = RollNo;
        this.book = book ;
        this.fine = fine ;
        this.dept = dept;


    }

    public String getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getRollNo() {
        return RollNo;
    }

    public String getBook() {
        return book;
    }

    public String getFine() {
        return fine;
    }

    public String getDept() {
        return dept;
    }


}
