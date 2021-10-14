package data;

import util.MyToys;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InjectionManagement implements InjectionList {

    static int lastVaccineID;
    static int lastStudentID;
    static int lastInjectionID;
    private List<Injection> listInjection = new ArrayList();
    Student student[];
    Vaccine vaccine[];
    File studentList = new File("Student.dat");
    File vaccineList = new File("Vaccine.dat");
    File injectionList = new File("Injection.dat");

    public InjectionManagement() {
        student = new Student[5];
        vaccine = new Vaccine[5];
        lastStudentID = 0;
        if (studentList.exists()) {
            try {
                FileInputStream fis = new FileInputStream(studentList);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (fis.available() > 0) {
                    student[lastStudentID++] = (Student) ois.readObject();
                }
                ois.close();
                fis.close();
            } catch (Exception e) {

            }
        }
        lastVaccineID = 0;
        if (vaccineList.exists()) {
            try {
                FileInputStream fis = new FileInputStream(vaccineList);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (fis.available() > 0) {
                    vaccine[lastVaccineID++] = (Vaccine) ois.readObject();
                }
                ois.close();
                fis.close();
            } catch (Exception e) {
            }
        }
    }

    public void showInjection() {
        if (listInjection.isEmpty()) {
            readFileInjection();
        } else {
            for (Injection x : listInjection) {
                x.showInjection();
            }
        }
    }

    @Override
    public void addInjection() {
        String injectionID;
        String firstPlace;
        Date firstDate;
        String studentID;
        String vaccineID;
        int count;
        DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int pos;
        int pos1;
        int pos2;
        do {
            injectionID = MyToys.getString("Enter the ID of injection: ", "Please try again !!!");
            pos = searchInjectionID(injectionID);
            if (pos != -1) {
                System.out.println("This ID already exists. Please inupt another one !");
            }
        } while (pos != -1);
        for (int i = 0; i < student.length; i++) {
            student[i].showProfile();
        }
        do {
            do {
                studentID = MyToys.getString("Enter the ID of student you want to add in Injection List: ", "Please try again!!!");
                count = searchStudentIDInjectionList(studentID);
                pos1 = searchStudentID(studentID);
                if (pos1 == -1) {
                    System.out.println("This Student ID has not exsits.");
                }
            } while (pos1 == -1);
            if (count != -1) {

                System.out.println("This student has not injected before.");
                studentID = student[pos1].getStudentID();
            }
        } while (count != -1);
            do {
                vaccineID = MyToys.getString("Enter The ID Of Vaccine You Want To Add In Injection List: ", "Please Try Again!!!");
                count = searchVaccineIDInjectionList(vaccineID);
                pos2 = searchVaccineID(vaccineID);
                if (pos2 == -1) {
                    System.out.println("This Vaccine ID Has Not Exsits.");
                }
            } while (pos2 == -1);
        firstPlace = MyToys.getString("Input First Place: ", "The First Place Is Required!");
        firstDate = MyToys.getDate();
        listInjection.add(new Injection(injectionID, firstPlace, firstDate, studentID, vaccineID));
    }

    private int searchInjectionID(String injectionID) {
        String pos;
        if (listInjection.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < listInjection.size(); i++) {
            if (listInjection.get(i).getInjectionID().equalsIgnoreCase(injectionID)) {
                return i;
            }
        }
        return -1;
    }

    private int searchStudentIDInjectionList(String studentID) {
        String pos;
        if (studentID.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < listInjection.size(); i++) {
            if (listInjection.get(i).getStudentID().equalsIgnoreCase(studentID)) {
                return i;
            }
        }
        return -1;
    }

//    private int searchInjectionByDate(Date date) {
//        if(listInjection.isEmpty()) {
//            return -1;
//        }
//        for (int i = 0; i < listInjection.size(); i++) {
//            if (listInjection.get(i).equals(date)) {
//                return i;
//            }
//        }
//        return -1;
//    }
//    
//    public void searchInjectionByDate() {
//        int pos;
//        Date date;
//        if (listInjection.isEmpty()) {
//            System.out.println("Empty List");
//        } else {
//            date = MyToys.getDate();
//            pos = searchInjectionByDate(date);
//            if (pos != -1) {
//                for (int i = 0; i < listInjection.size(); i++) {
//                    if (listInjection.get(i).getFirstDate().equals(date)) {
//                        listInjection.get(i).showInjection();
//                    }
//                }
//            } else {
//                System.out.println("This Injection Does Not Exist");
//            }
//        }
//    }
    
    private int searchStudentID(String studentID) {
        if (student.length == 0) {
            return -1;
        }
        for (int i = 0; i < student.length; i++) {
            if (student[i].getStudentID().equalsIgnoreCase(studentID)) {
                return i;
            }
        }
        return -1;
    }

    private int searchVaccineIDInjectionList(String vaccineID) {
        String pos;
        if (vaccineID.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < listInjection.size(); i++) {
            if (listInjection.get(i).getVaccineID().equalsIgnoreCase(vaccineID)) {
                return i;
            }
        }
        return -1;
    }

    private int searchVaccineID(String vaccineID) {
        if (vaccine.length == 0) {
            return -1;
        }
        for (int i = 0; i < vaccine.length; i++) {
            if (vaccine[i].getVaccineID().equalsIgnoreCase(vaccineID)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void updateInjection() {
        String injectionID;
        Date firstDate;
        Date secondDate;
        String secondPlace;
        long diff;
        if (listInjection.isEmpty()) {
            System.out.println("invalid");
        } else {
            for (Injection x : listInjection) {
                x.showInjection();
            }
            String input = MyToys.getId("Enter the ID of injection: ", "Please try again !!!");
            int pos, pos1;
            pos = searchInjectionID(input);
            if (pos != -1) {
                for (int i = 0; i < listInjection.size(); i++) {
                    if (listInjection.get(i).getInjectionID().equalsIgnoreCase(input)) {
                        listInjection.get(i).showInjection();

                        do {
                            do {
                                input = MyToys.getString("Enter The ID Of Vaccine You Want To Add In Injection List: ", "Please Try Again!!!");
                                pos1 = searchVaccineID(input);
                                if (pos1 == -1) {
                                    System.out.println("This Vaccine ID Has Not Exsits.");
                                }
                            } while (pos1 == -1);
                            if (listInjection.get(pos).getVaccineID().equalsIgnoreCase(input) != true) {
                                System.out.println("Vaccines Are Not Of The Same Type");
                            }
                        } while (listInjection.get(pos).getVaccineID().equalsIgnoreCase(input) != true);
                        secondPlace = MyToys.getString("Input Second Place: ", "The Second Place Is Required!");
                        listInjection.get(i).setSecondPlace(secondPlace);
                        do {
                            Date date = MyToys.getDate();
                            long diffInMillies = Math.abs(listInjection.get(i).getFirstDate().getTime() - date.getTime());
                            diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                            if (diff >= 28 && diff <= 84) {
                                listInjection.get(i).setSecondDate(date);
                            }
                        } while (diff < 28 || diff > 84);

                        listInjection.get(i).showInjection();
                        if (listInjection.get(pos).getSecondDate() != null) {
                            System.out.println("Student has completed 2 injections!");
                        } else {
                            System.out.println("Update call cancel");
                        }
                        break;
                    }
                }
            } else {
                System.out.println("ID not vail");
            }
        }
    }

    @Override
    public void deleteInforStudentID() {
        if (listInjection.isEmpty()) {
            System.out.println("invalid");
        }
        String injectionID;
        boolean x;
        int pos;
        injectionID = MyToys.getString("Input Injection ID: ", "Injection ID Is Required!");
        pos = searchInjectionID(injectionID);
        if (pos == -1) {
            System.out.println("Not Found!!!");
        } else {
            for (int i = 0; i < listInjection.size(); i++) {
                if (listInjection.get(i).getInjectionID().equalsIgnoreCase(injectionID)) {
                    listInjection.get(i).showInjection();
                }
            }
            x = MyToys.getBooleanRemove();
            if (x == true) {
                listInjection.remove(pos);
                System.out.println("The Selected Injection Is Removed Successfully!!!");
            } else {
                System.out.println("The Selected Injection Is Removed Fail!!!");
            }
        }
    }

    @Override
    public void searchInjectionByStudentID() {
        int pos;
        String studentID;
        if (listInjection.isEmpty()) {
            System.out.println("Empty List");
        } else {
            studentID = MyToys.getString("Enter A Student ID You Want To Search: ",
                    "ERROR. Please Try Again !!!");
            pos = searchStudentID(studentID);
            if (pos != -1) {
                for (int i = 0; i < listInjection.size(); i++) {
                    if (listInjection.get(i).getStudentID().equalsIgnoreCase(studentID)) {
                        listInjection.get(i).showInjection();
                    }
                }
            } else {
                System.out.println("This Injection Does Not Exist");
            }
        }
    }

    public void saveFileVaccine() {
        try {
            String fileName = "vaccine.dat";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            List<Vaccine> listVaccine = new ArrayList<>();
            listVaccine.add(new Vaccine("Covid-V001", "AstraZeneca"));
            listVaccine.add(new Vaccine("Covid-V002", "SPUTNIK V"));
            listVaccine.add(new Vaccine("Covid-V003", "Vero Cell"));
            listVaccine.add(new Vaccine("Covid-V004", "Pfizer"));
            listVaccine.add(new Vaccine("Covid-V005", "Moderna"));
            for (Vaccine vc : listVaccine) {
                oos.writeObject(vc);
            }
            oos.close();
            fos.close();
        } catch (Exception e) {
        }
    }

    public void saveFileStudent() {
        try {
            String fileName = "student.dat";
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            List<Student> list = new ArrayList<>();
            list.add(new Student("SE151101", "Hoang Nha Thy"));
            list.add(new Student("SE150083", "Pham Quang Linh"));
            list.add(new Student("SE151046", "Nguyen Hoang Tan"));
            list.add(new Student("SE151074", "Ngo Xuan Thiep"));
            list.add(new Student("SE151086", "Dao Duc Thinh"));
            for (Student st : list) {
                oos.writeObject(st);
            }
            oos.close();
            fos.close();
        } catch (Exception e) {
        }
    }

    public void saveFileInjection() {
        if (listInjection.isEmpty()) {
            System.out.println("List Injection is empty");
        } else {
            try {
                FileOutputStream fos = new FileOutputStream(injectionList);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for (int i = 0; i < listInjection.size(); i++) {
                    oos.writeObject(listInjection.get(i));
                }
                oos.close();
                fos.close();
            } catch (Exception e) {
            }
            System.out.println("Save sucessfully!!!");
        }
    }

    public void readFileInjection() {
        if (injectionList.exists()) {
            try {
                FileInputStream fis = new FileInputStream(injectionList);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (fis.available() > 0) {
                    Injection inj = (Injection) ois.readObject();
                    listInjection.add(inj);
                }
                for (int i = 0; i < listInjection.size(); i++) {
                    listInjection.get(i).showInjection();
                }
                fis.close();
                ois.close();
            } catch (Exception e) {
            }
        } else {
            try {
                FileOutputStream fos = new FileOutputStream(injectionList);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String date1 = "30/08/2021";
                String date2 = "25/09/2021";
                String date3 = "28/07/2021";
                String date4 = "09/09/2021";
                Date d1 = sdf.parse(date1);
                Date d2 = sdf.parse(date2);
                Date d3 = sdf.parse(date3);
                Date d4 = sdf.parse(date4);
                listInjection.add(new Injection("1", "Vung Tau", "NULL", d1, null, 
                        "SE151074", "Covid-V001", ""));
                listInjection.add(new Injection("2", "Da Nang", "NULL", d2, null, 
                        "SE151086", "Covid-V003", ""));
                listInjection.add(new Injection("3", "Ho Chi Minh", "Dong Nai", d3, d1, 
                        "SE151101", "Covid-V001", ""));
                listInjection.add(new Injection("4", "Dong Nai", "NULL", d4, null, 
                        "SE151046", "Covid-V002", ""));
                for (Injection vc : listInjection) {
                    oos.writeObject(vc);
                }
                oos.close();
                fos.close();
            } catch (Exception e) {

            }
            for (int i = 0; i < listInjection.size(); i++) {
                listInjection.get(i).showInjection();
            }
        }
    }
    
    //search Injection theo ngày
    public void searchInjectionByDate() {
        int pos;
        Date date;
        if (listInjection.isEmpty()) {
            System.out.println("Empty List");
        } else {
            date = MyToys.getDate();
            pos = searchDate(date);
            if (pos != -1) {
                for (int i = 0; i < listInjection.size(); i++) {
                    if (listInjection.get(i).getFirstDate().compareTo(date) == 0) {
                        listInjection.get(i).showInjection();
                    } else {
                        if (listInjection.get(i).getSecondDate() != null && listInjection.get(i).getSecondDate().compareTo(date) == 0) {
                            listInjection.get(i).showInjection();
                        }
                    }
                }
            } else {
                System.out.println("This Date Does Not Exist");
            }
        }
    }

    private int searchDate(Date date) {
        if (listInjection.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < listInjection.size(); i++) {
            if (listInjection.get(i).getFirstDate().compareTo(date) == 0) {
                return i;
            } else {
                if (listInjection.get(i).getSecondDate() != null && listInjection.get(i).getSecondDate().compareTo(date) == 0) {
                    listInjection.get(i).showInjection();
                }
            }
        }
        return -1;
    }

    //search Injection theo place
    public void searchInjectionByPlace() {
        int pos;
        String place;
        if (listInjection.isEmpty()) {
            System.out.println("Empty List");
        } else {
            place = MyToys.getString("Enter A Place You Want To Search: ",
                    "ERROR. Please Try Again !!!");
            pos = searchPlace(place);
            if (pos != -1) {
                for (int i = 0; i < listInjection.size(); i++) {
                    if (listInjection.get(i).getFirstPlace().equalsIgnoreCase(place)) {
                        listInjection.get(i).showInjection();
                    } else {
                        if (listInjection.get(i).getSecondPlace().equalsIgnoreCase(place)) {
                            listInjection.get(i).showInjection();
                        }
                    }
                }
            } else {
                System.out.println("This Injection Does Not Exist");
            }
        }
    }

    private int searchPlace(String place) {
        if (student.length == 0) {
            return -1;
        }
        for (int i = 0; i < student.length; i++) {
            if (listInjection.get(i).getFirstPlace().equalsIgnoreCase(place)) {
                return i;
            } else {
                if (listInjection.get(i).getSecondPlace().equalsIgnoreCase(place)) {
                    return i;
                }
            }   
        }
        return -1;
    }
    
    //search injection theo tên sinh viên
    public void searchInjectionByNameStudent() {
        int pos;
        String studentName;
        if (listInjection.isEmpty()) {
            System.out.println("Emptty List");
        } else {
            for (Student st : student) {
                st.showProfile();
            }
            studentName = MyToys.getString("Enter the Name of Student you want to search in Injection List: ", "Please try again!!!");
            pos = searchStudentName(studentName);
            if (pos != -1) {
                System.out.println("The result in Student table:");
                student[pos].showProfile();
                System.out.println("The result in Injection table");
                for (int i = 0; i < listInjection.size(); i++) {
                    if (student[pos].getStudentID().equalsIgnoreCase(listInjection.get(i).getStudentID())) {
                        listInjection.get(i).showInjection();
                    }
                }
            } else {
                System.out.println("This Student Does Not Exist");
            }
        }
    }
    private int searchStudentName(String studentName) {
        if (student.length == 0) {
            return -1;
        }
        for (int i = 0; i < student.length; i++) {
            if (student[i].getStudentName().equals(studentName)) {
                return i;
            }
        }
        return -1;
    }
    
    //search injection theo id jnjection
    public void searchInjectionByIDInjection() {
        int pos;
        String injectionID;
        if (listInjection.isEmpty()) {
            System.out.println("Empty List");
        } else {
            injectionID = MyToys.getString("Enter The ID Of Injection You Want To Search In Injection List: ", "Please Try Again!!!");
            pos = searchInjectionID(injectionID);
            if (pos != -1) {
                for (int i = 0; i < listInjection.size(); i++) {
                    if (listInjection.get(i).getInjectionID().equalsIgnoreCase(injectionID)) {
                        listInjection.get(i).showInjection();
                    }
                }
            } else {
                System.out.println("This Injection Does Not Exist");
            }
        }
    }
    
    //search injection theo id vaccine
    public void searchInjectionByIDVaccine() {
        int pos;
        String vaccineID;
        if (listInjection.isEmpty()) {
            System.out.println("Empty List");
        } else {
            vaccineID = MyToys.getString("Enter The ID Of Vaccine You Want To Search In Injection List: ", "Please Try Again!!!");
            pos = searchVaccineID(vaccineID);
            if (pos != -1) {
                for (int i = 0; i < listInjection.size(); i++) {
                    if (listInjection.get(i).getVaccineID().equalsIgnoreCase(vaccineID)) {
                        listInjection.get(i).showInjection();
                    }
                }
            } else {
                System.out.println("This Injection Does Not Exist");
            }
        }
    }
    
    //search injection theo tên vaccine
    public void searchInjectionByNameVaccine() {
        int pos;
        String vaccineName;
        if (listInjection.isEmpty()) {
            System.out.println("Empty List");
        } else {
            for (Vaccine vc : vaccine) {
                vc.showInfor();
            }
            vaccineName = MyToys.getString("Enter the Name of Vaccine you want to search in Injection List: ", "Please try again!!!");
            pos = searchVaccineName(vaccineName);
            if (pos != -1) {
                System.out.println("The result in Vaccine table:");
                vaccine[pos].showInfor();
                for (int i = 0; i < listInjection.size(); i++) {
                    if (vaccine[pos].getVaccineID().equalsIgnoreCase(listInjection.get(i).getVaccineID())) {
                        listInjection.get(i).showInjection();
                    }
                }
            } else {
                System.out.println("This Injection Does Not Exist");
            }
        }
    }
    private int searchVaccineName(String vaccineName) {
        if (vaccine.length == 0) {
            return -1;
        }
        for (int i = 0; i < vaccine.length; i++) {
            if (vaccine[i].getVaccineName().equalsIgnoreCase(vaccineName)) {
                return i;
            }
        }
        return -1;
    }
    
    
    //xoá theo date
    public void deleteDate() {
        int count = 0;
        if (listInjection.isEmpty()) {
            System.out.println("invalid");
        }
        Date date;
        boolean x;
        int pos;
        date = MyToys.getDate();
        pos = deleteFirstDate(date);
        count = searchDateV1(date);
        System.out.println(count);
        if (pos == -1) {
            System.out.println("Not Found!!!");
        } else {
            x = MyToys.getBooleanRemove();
            if (x == true) {
                System.out.println("The Selected Date Is Removed Successfully!!!");
                do {
                    for (int i = 0; i < listInjection.size(); i++) {
                        if (listInjection.get(i).getFirstDate().compareTo(date) == 0) {
                            count--;
                            listInjection.remove(i);
                        } else {
                            if (listInjection.get(i).getSecondDate() != null && listInjection.get(i).getSecondDate().compareTo(date) == 0) {
                                count--;
                                listInjection.remove(i);
                            }
                        }
                    }

                } while (count != 0);
            } else {
                System.out.println("The Selected Date Is Removed Fail!!!");
            }
            return;
        }

    }

    private int deleteFirstDate(Date date) {
        if (listInjection.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < listInjection.size(); i++) {
            if (listInjection.get(i).getFirstDate().compareTo(date) == 0) {
                return i;
            } else {
                if (listInjection.get(i).getSecondDate() != null && listInjection.get(i).getSecondDate().compareTo(date) == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    private int searchDateV1(Date date) {
        int count = 0;
        if (listInjection.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < listInjection.size(); i++) {
            if (listInjection.get(i).getFirstDate().compareTo(date) == 0) {
                count++;
            } else {
                if (listInjection.get(i).getSecondDate() != null && listInjection.get(i).getSecondDate().compareTo(date) == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    //xoá theo place
    public void removePlace() {
        int pos;
        String place;
        if (listInjection.isEmpty()) {
            System.out.println("Empty List");
        } else {
            place = MyToys.getString("Enter A Place You Want To Remove: ",
                    "ERROR. Please Try Again !!!");
            pos = searchPlace(place);
            if (pos != -1) {
                for (int i = 0; i < listInjection.size(); i++) {
                    if (listInjection.get(i).getFirstPlace().equalsIgnoreCase(place)) {
                        listInjection.remove(i);
                    }
                }
                for (int i = 0; i < listInjection.size(); i++) {
                    if (listInjection.get(i).getSecondPlace().equalsIgnoreCase(place)) {
                        listInjection.remove(i);
                    }
                }
            } else {
                System.out.println("This Injection Does Not Exist");
            }
        }
    }
    
    //xoá theo name student
    public void removeNameStudent() {
        int pos;
        String studentName;
        if (listInjection.isEmpty()) {
            System.out.println("Emptty List");
        } else {
            for (Student st : student) {
                st.showProfile();
            }
            studentName = MyToys.getString("Enter the Name of Student you want to remove in Injection List: ", "Please try again!!!");
            pos = searchStudentName(studentName);
            if (pos != -1) {
                System.out.println("The Student you want to remove:");
                student[pos].showProfile();
                for (int i = 0; i < listInjection.size(); i++) {
                    if (student[pos].getStudentID().equalsIgnoreCase(listInjection.get(i).getStudentID())) {
                        listInjection.remove(i);
                    }
                }
            } else {
                System.out.println("This Student Does Not Exist");
            }
        }
    }
    
    //xoá theo student id
    public void removeIDStudent() {
        int pos;
        String studentID;
        if (listInjection.isEmpty()) {
            System.out.println("Empty List");
        } else {
            studentID = MyToys.getString("Enter A Student ID You Want To Remove: ",
                    "ERROR. Please Try Again !!!");
            pos = searchStudentID(studentID);
            if (pos != -1) {
                for (int i = 0; i < listInjection.size(); i++) {
                    if (listInjection.get(i).getStudentID().equalsIgnoreCase(studentID)) {
                        listInjection.remove(i);
                    }
                }
            } else {
                System.out.println("This Injection Does Not Exist");
            }
        }
    }
    
    //xoá theo name vaccine
    public void removeNameVaccine() {
        int pos;
        String vaccineName;
        if (listInjection.isEmpty()) {
            System.out.println("Empty List");
        } else {
            for (Vaccine vc : vaccine) {
                vc.showInfor();
            }
            vaccineName = MyToys.getString("Enter the Name of Vaccine you want to remove in Injection List: ", "Please try again!!!");
            pos = searchVaccineName(vaccineName);
            if (pos != -1) {
                System.out.println("The Vaccine you want to remove:");
                vaccine[pos].showInfor();
                for (int i = 0; i < listInjection.size(); i++) {
                    if (vaccine[pos].getVaccineID().equalsIgnoreCase(listInjection.get(i).getVaccineID())) {
                        listInjection.remove(i);
                    }
                }
            } else {
                System.out.println("This Injection Does Not Exist");
            }
        }
    }
    
    //xoá theo id vaccine
    public void removeIDVaccine() {
        int pos;
        String vaccineID;
        if (listInjection.isEmpty()) {
            System.out.println("Empty List");
        } else {
            vaccineID = MyToys.getString("Enter The ID Of Vaccine You Want To Remove In Injection List: ", "Please Try Again!!!");
            pos = searchVaccineID(vaccineID);
            if (pos != -1) {
                for (int i = 0; i < listInjection.size(); i++) {
                    if (listInjection.get(i).getVaccineID().equalsIgnoreCase(vaccineID)) {
                        listInjection.remove(i);
                    }
                }
            } else {
                System.out.println("This Injection Does Not Exist");
            }
        }
    }
    
    //xoá theo id injection
    public void removeIDInjection() {
        int pos;
        String injectionID;
        if (listInjection.isEmpty()) {
            System.out.println("Empty List");
        } else {
            injectionID = MyToys.getString("Enter The ID Of Injection You Want To Remove In Injection List: ", "Please Try Again!!!");
            pos = searchInjectionID(injectionID);
            if (pos != -1) {
                for (int i = 0; i < listInjection.size(); i++) {
                    if (listInjection.get(i).getInjectionID().equalsIgnoreCase(injectionID)) {
                        listInjection.remove(i);
                    }
                }
            } else {
                System.out.println("This Injection Does Not Exist");
            }
        }
    }
    
    //xoá nếu date 2 > date 1 hơn 30 ngày
    public void removeDateLimmited() {
        long days;
        int count = 0;
        for (int i = 0; i < listInjection.size(); i++) {
            if (listInjection.get(i).getSecondDate() != null) {
                days = getDifferenceDays(listInjection.get(i).getFirstDate(), listInjection.get(i).getSecondDate());
                if (days > 30) {
                    System.out.println("This is the list that have Date 2 > Date 1 more than 30 days");
                    listInjection.get(i).showInjection();
                    count++;
                }
            }
        }
        do {
            for (int i = 0; i < listInjection.size(); i++) {
                if (listInjection.get(i).getSecondDate() != null) {
                    days = getDifferenceDays(listInjection.get(i).getFirstDate(), listInjection.get(i).getSecondDate());
                    if (days > 30) {
                        count--;
                        listInjection.remove(i);
                        System.out.println("Delete succesfully!");
                    }
                }
            }
        } while (count != 0);
    }
    
    public long getDifferenceDays(Date d1, Date d2) {
        long diffInmillies = Math.abs(d1.getTime() - d2.getTime());
        long diff = TimeUnit.DAYS.convert(diffInmillies, TimeUnit.MILLISECONDS);
        return diff;
    }
    
    //xoá nếu date now > date 2 hơn 30 ngày
    public void removeDateLimmitedNow() {
        Date dateNow = new Date();
        long days;
        int count = 0;
        for (int i = 0; i < listInjection.size(); i++) {
            if (listInjection.get(i).getSecondDate() != null) {
                days = getDifferenceDays(listInjection.get(i).getSecondDate(), dateNow);
                if (days > 30) {
                    System.out.println("This is the list that have Date Now > Date 2 more than 30 days");
                    listInjection.get(i).showInjection();
                    count++;
                }
            }
        }
        do {
            for (int i = 0; i < listInjection.size(); i++) {
                if (listInjection.get(i).getSecondDate() != null) {
                    days = getDifferenceDays(listInjection.get(i).getFirstDate(), listInjection.get(i).getSecondDate());
                    if (days > 30) {
                        count--;
                        listInjection.remove(i);
                        System.out.println("Delete succesfully!");
                    }
                }
            }
        } while (count != 0);
    }
    
    public void printInjection() {
        if (listInjection.isEmpty()) {
            System.out.println("List Injection is empty");
        } else {
            for (int i = 0; i < listInjection.size(); i++) {
                if (listInjection.get(i).getSecondDate() != null) {
                    listInjection.get(i).setStatus("Completed");
                } else {
                    listInjection.get(i).setStatus("Not Completed");
                }
            }
            for (Injection x : listInjection) {
                x.showInjection();
            }
        }
    }
}
