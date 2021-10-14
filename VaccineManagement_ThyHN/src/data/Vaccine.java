
package data;

import java.io.Serializable;

public class Vaccine implements Serializable{
    
    private String vaccineID ;
    private String vaccineName ;

    public Vaccine() {
    }

    public Vaccine(String vaccineID, String vaccineName) {
        this.vaccineID = vaccineID;
        this.vaccineName = vaccineName;
    }

    public String getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(String vaccineID) {
        this.vaccineID = vaccineID;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    @Override
    public String toString() {
        return " ";
    }
    public void showInfor(){
        System.out.printf("|%-20s|%-20s|\n" , vaccineID ,vaccineName);
    }
    
    
}


