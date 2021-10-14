package data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

public class Food implements Comparable<Food>, Serializable{

    private String id;
    private String name;
    private double weight;
    private String type;
    private String place;
    private LocalDate expiredDate;

    public Food(String id, String name, double weight, String type, String place,
            LocalDate expiredDate) {
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.type = type;
        this.place = place;
        this.expiredDate = expiredDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public LocalDate getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(LocalDate expiredDate) {
        this.expiredDate = expiredDate;
    }

    @Override
    public String toString() {
        return "Food{" + "id=" + id + ", name=" + name + ", weight=" + weight + ", type=" + type + ", place=" + place + ", expiredDate=" + expiredDate + '}';
    }

    public void showInformation() {
        String msg = String.format("|%-10s|%-15s|%-15s|%-15s|%-20s|%-20s|",
                id, name, weight, type, place, expiredDate);
        System.out.println(msg);
    }

    @Override
    public int compareTo(Food x) {
        if (x.expiredDate.isBefore(this.expiredDate)) {
            return 1;
        } else if (x.expiredDate.isAfter(this.expiredDate)) {
            return -1;
        } else {
            return 0;
        }
    }
    
    
}

