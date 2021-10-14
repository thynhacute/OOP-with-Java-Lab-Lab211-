package data;

import java.io.Serializable;

public class Student implements Serializable{
    private String studentID ;
    private String studentName ;

    public Student() {
    }

    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return " ";
    }
    
    public void showProfile(){
        System.out.printf("|%-20s|%-20s|\n",studentID,studentName);
    }
}


