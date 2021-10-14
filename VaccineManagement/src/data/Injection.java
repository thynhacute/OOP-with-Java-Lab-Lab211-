package data;

import java.io.Serializable;
import java.util.Date;
import util.MyToys;

public class Injection implements Serializable {

    private String injectionID;
    private String firstPlace;
    private String secondPlace;
    private Date firstDate;
    private Date secondDate;
    private String studentID;
    private String vaccineID;
    private String status;

    public Injection() {
    }

    public Injection(String injectionID, String firstPlace, Date firstDate, String studentID, String vaccineID) {
        this.injectionID = injectionID;
        this.firstPlace = firstPlace;
        this.firstDate = firstDate;
        this.studentID = studentID;
        this.vaccineID = vaccineID;
        this.secondPlace = null;
        this.secondDate = null;
    }

    public Injection(String injectionID, String firstPlace, String secondPlace, Date firstDate, Date secondDate, String studentID, String vaccineID, String status) {
        this.injectionID = injectionID;
        this.firstPlace = firstPlace;
        this.secondPlace = secondPlace;
        this.firstDate = firstDate;
        this.secondDate = secondDate;
        this.studentID = studentID;
        this.vaccineID = vaccineID;
        this.status = status;
    }

    public String getInjectionID() {
        return injectionID;
    }

    public void setInjectionID(String injectionID) {
        this.injectionID = injectionID;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(Date secondDate) {
        this.secondDate = secondDate;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(String vaccineID) {
        this.vaccineID = vaccineID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Injection{" + "injectionID=" + injectionID + ", firstPlace=" + firstPlace + ", secondPlace=" + secondPlace + ", firstDate=" + firstDate + ", secondDate=" + secondDate + ", studentID=" + studentID + ", vaccineID=" + vaccineID + ", status=" + status + '}';
    }
    
    

    public void showInjection() {
        System.out.printf("|%-2s|%-18s|%-12s|%-18s|%-12s|%-10s|%-12s|%-12s|\n",
                injectionID, firstPlace, MyToys.dateToString(firstDate), secondPlace, MyToys.dateToString(secondDate), studentID, vaccineID, status);
    }

}



