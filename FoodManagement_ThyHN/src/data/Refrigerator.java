
package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import util.MyToys;

public class Refrigerator {

    private ArrayList<Food> foodList = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void addNewFood() {
        String id, name, type, place;
        LocalDate expiredDate;
        double weight;
        Food food;
        do {
            id = MyToys.getString("Input the ID of the Food: (Ex: SExxxxxx or SSxxxxxx)",
                    "The ID of the Food must not exist yet.", "^(S|s)[(E|e)(S|s)]\\d{6}$");
            food = searchFoodObjectByID(id);
            if (food != null) {
                System.err.println("The ID of the Food already exists."
                        + " Input another one!");
            }
        } while (food != null);
        name = MyToys.getString("Input the Name of the Food: ", "The Food name is "
                + "required!", "[a-zA-Z0-9\\s]{1,}");
        weight = MyToys.getADouble("Input the Weight of the Food: (Ex: 1, 2.1, 3.3,...)",
                "The Weight of Food must be greater than 0.", 0);
        type = MyToys.getString("Input the Type of the Food: ", "The Type of the "
                + "Food is required!", "[a-zA-Z0-9\\s]{1,}");
        place = MyToys.getString("Input the Place to put the Food: ", "The Place "
                + "to put the Food is required!", "[a-zA-Z0-9\\s]{1,}");

        while (true) {
            try {
                expiredDate = LocalDate.parse(MyToys.getStringNoFormat("Input the "
                        + "Expired Date of the Food: (Ex: yyyy-mm-dd)", "The "
                        + "Expired Date of the Food is required!"),
                        DateTimeFormatter.ISO_LOCAL_DATE);
                foodList.add(new Food(id, name, weight, type, place, expiredDate));
                System.out.println("A Food information is added sucessfully");
                break;
            } catch (Exception E) {
                System.err.println("Wrong Format Expired Date!!");
            }
        }
        
    }

    public Food searchFoodObjectByID(String id) {
        if (foodList.isEmpty()) {
            return null;
        }
        for (Food x : foodList) {
            if (x.getId().equalsIgnoreCase(id)) {
                return x;
            }
        }
        return null;
    }
    
    public ArrayList<Food> searchFoodByName(ArrayList<Food> foodList, String name) {
        ArrayList<Food> searchFoodByName = new ArrayList<>();
        for (Food x : foodList) {
            if (x.getName().contains(name)) {
                searchFoodByName.add(x);
            }
        }
        return searchFoodByName;
    }
    public void searchFoodByName() {
        String name;
        name = MyToys.getString("Input the Name of the Food you want to find: ",
                "The Food Name is required!", "[a-zA-Z0-9\\s]{1,}");
        ArrayList<Food> searchFoodByName = searchFoodByName(foodList, name);
        if (searchFoodByName.isEmpty()) {
            System.err.println("This food does not exist. Nothing to search");
        } else {
            System.out.printf("|%-10s|%-15s|%-15s|%-15s|%-20s|%-20s|\n", "ID", "Name", 
                    "Weight", "Type", "Place", "ExpiredDate");
            for (Food x : searchFoodByName) {
                x.showInformation();
            }
        }
    }
    
    //xoá theo id
    public void removeFood() {
        String id;
        boolean answer;
        id = MyToys.getString("Input the ID of the Food: ", "The ID of "
                + "the Food is required!", "[a-zA-Z0-9\\s]{1,}");
        Food food = searchFoodObjectByID(id);
        System.out.println("------------------------------------");
        answer = MyToys.checkRemoveYN();
        if (answer == true) {
            if (food == null) {
                System.err.println("Not found!!! The selected Food is removed unsuccessfully");
            } else {
                foodList.remove(food);
                System.out.println("The selected Food is removed successfully!");
            }
        } else {
            System.out.println("You chose No selection. So the selected Food will not be removed");
        }
    }
    
    //search food by id
    public void searchFoodByID() {
        String id;
        Food x;
        id = MyToys.getString("Input the ID of Food: ", "The ID of Food is required!", "^(S|s)[(E|e)(S|s)]\\d{6}$");
        x = searchFoodObjectByID(id);
        System.out.println("------------------------------------");
        if (x == null)
            System.out.println("Not found!!!");
        else {
            System.out.println("Here is the Food "
                    + "that you want to search");
            System.out.printf("|%-10s|%-15s|%-15s|%-15s|%-20s|%-20s|\n", "ID", "Name", 
                    "Weight", "Type", "Place", "ExpiredDate");
            x.showInformation();
        }     
    }
    
    
    public void printFoodListDecendingByExpiredDate() {
        String status;
        if (foodList.isEmpty()) {
            System.err.println("List empty");
        } else {
            System.out.println("You are preparing to print the food list descending "
                    + "by order of expired date");
            Collections.sort(foodList);
        for (int i = 0; i < foodList.size(); i++) {
            
        
        if (foodList.get(i).getWeight() > 2) {
                status = "heavy";
                
            } else{
                status = "light";
                
            }
        }
            System.out.printf("|%-10s|%-15s|%-15s|%-15s|%-20s|%-20s|%-20s|\n", "ID", "Name",
                    "Weight", "Type", "Place", "ExpiredDate", "Status");
            for (Food x : foodList) {
                x.showInformation();
            }
        }
        
    }
    
    public void listAvailable() {
        if (foodList.isEmpty()) {
            foodList.add(new Food("SE150001", "spaghetti", 2.5, "dessert", "upper compartment", LocalDate.parse("2021-09-09")));
            foodList.add(new Food("SE150006", "cuury", 3.0, "soup", "center", LocalDate.parse("2021-09-19")));
            foodList.add(new Food("SE150003", "cocktail", 1.5, "dessert", "upper compartment", LocalDate.parse("2021-09-21")));
            foodList.add(new Food("SE150007", "hotpot", 4.5, "main dishes", "center", LocalDate.parse("2021-09-20")));
            foodList.add(new Food("SE150005", "pizza", 2.3, "fastfood", "lower compartment", LocalDate.parse("2021-09-18")));
            for (Food x : foodList) {
                x.showInformation();
            }
        } else {
            for (Food x : foodList) {
                x.showInformation();
            }
        }
    }
    
    //search theo weight X
    public void searchFoodXByWeight() {
        double x;
        x = MyToys.getADouble("Input the small limitted of Weight of the Food: (Ex: 1, 2.1, 3.3,...)",
                "The Limitted Weight of Food must be greater than 0.", 0);
        if (foodList.isEmpty()) {
            System.out.println("Not found!!!");
        } else {
            System.out.println("Here is the list you want:");
            System.out.printf("|%-10s|%-15s|%-15s|%-15s|%-20s|%-20s|\n", "ID", "Name",
                    "Weight", "Type", "Place", "ExpiredDate");
            for (int i = 0; i < foodList.size(); i++) {
                if (foodList.get(i).getWeight() >= x && foodList.get(i).getWeight() < x + 1) {
                    foodList.get(i).showInformation();
                }
            }
        }
 
    }
    
    //search theo weight
    public void searchFoodByWeight() {
        double weight;//sửa ở đây
        weight = MyToys.getADouble("input key you want search: ", "pls input again", 0);
        int pos = searchWeight(weight);
        if (pos >= 0) {
            for (int i = 0; i < foodList.size(); i++) {
                if (foodList.get(i).getWeight() == weight) {
                    foodList.get(i).showInformation();
                }
            }
        } else {
            System.out.println("The Food name is not exist");
        }
        boolean result = true;
        Scanner sc = new Scanner(System.in);
        String choice;
        boolean check = true;
        do {
            do {
                System.out.print("(continue choice y or back menu choice n): ");
                choice = sc.nextLine();
            } while (!(choice.equals("y") || choice.equals("n")));
            if (choice.equalsIgnoreCase("y")) {
                result = true;
                this.searchFoodByWeight();
            } else {
                result = false;
                break;
            }
            result = false;
        } while (check);
    }

    public int searchWeight(double weight) {
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getWeight() == weight) {
                return i;
            }
        }
        return -1;
    }

    //search theo Type
    public void searchFoodByType() {
        String nFood;
        nFood = MyToys.getString("input key you want search: ", "pls input again", "[a-zA-Z0-9\\s]{1,}");
        int pos = searchType(nFood);
        if (pos >= 0) {
            for (int i = 0; i < foodList.size(); i++) {
                if (foodList.get(i).getType().contains(nFood)) {
                    foodList.get(i).showInformation();
                }
            }
        } else {
            System.out.println("The Food name is not exist");
        }
        boolean result = true;
        Scanner sc = new Scanner(System.in);
        String choice;
        boolean check = true;
        do {
            do {
                System.out.print("(continue choice y or back menu choice n): ");
                choice = sc.nextLine();
            } while (!(choice.equals("y") || choice.equals("n")));
            if (choice.equalsIgnoreCase("y")) {
                result = true;
                this.searchFoodByType();
            } else {
                result = false;
                break;
            }
            result = false;
        } while (check);
    }

    public int searchType(String nFood) {
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getType().contains(nFood)) {
                return i;
            }
        }
        return -1;
    }
    
//search theo place

    public void searchFoodByPlace() {
        String nFood;
        nFood = MyToys.getString("input key you want search: ", "pls input again", "[a-zA-Z0-9\\s]{1,}");
        int pos = searchPlace(nFood);
        if (pos >= 0) {
            for (int i = 0; i < foodList.size(); i++) {
                if (foodList.get(i).getPlace().contains(nFood)) {
                    foodList.get(i).showInformation();
                }
            }
        } else {
            System.out.println("The Food name is not exist");
        }
        boolean result = true;
        Scanner sc = new Scanner(System.in);
        String choice;
        boolean check = true;
        do {
            do {
                System.out.print("(continue choice y or back menu choice n): ");
                choice = sc.nextLine();
            } while (!(choice.equals("y") || choice.equals("n")));
            if (choice.equalsIgnoreCase("y")) {
                result = true;
                this.searchFoodByPlace();
            } else {
                result = false;
                break;
            }
            result = false;
        } while (check);
    }

    public int searchPlace(String nFood) {
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getPlace().contains(nFood)) {
                return i;
            }
        }
        return -1;
    }
    
     
    //search Date
    public void searchFoodByDate() {
        LocalDate date;
        while (true) {
                try {
                    date = LocalDate.parse(MyToys.getStringNoFormat("Input the "
                            + "Expired Date of the Food: (Ex: yyyy-mm-dd)", "The "
                            + "Expired Date of the Food is required!"),
                            DateTimeFormatter.ISO_LOCAL_DATE);
                    break;
                } catch (Exception E) {
                    System.err.println("Wrong Format Expired Date!!");
                }
            }
        int pos = searchDate(date);
        if (pos >= 0) {
            for (int i = 0; i < foodList.size(); i++) {
                if (foodList.get(i).getExpiredDate().compareTo(date) == 0) {
                    foodList.get(i).showInformation();
                }
            }
        } else {
            System.out.println("The Food name is not exist");
        }
        boolean result = true;
        Scanner sc = new Scanner(System.in);
        String choice;
        do {
            do {
                System.out.print("(continue choice y or back menu choice n): ");
                choice = sc.nextLine();
            } while (!(choice.equals("y") || choice.equals("n")));
            if (choice.equalsIgnoreCase("y")) {
                result = true;
                this.searchFoodByDate();
            } else {
                result = false;
                break;
            }
            result = false;
        } while (result);
    }

    public int searchDate(LocalDate nFood) {
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getExpiredDate().compareTo(nFood) == 0) {
                return i;
            }
        }
        return -1;
    }
    
    
    public ArrayList<Food> searchFoodByExpiredDate(ArrayList<Food> foodList, LocalDate expiredDate) {
        ArrayList<Food> searchFoodByExpiredDate = new ArrayList<>();
        for (Food x : foodList) {
            if (x.getExpiredDate().equals(expiredDate)) {
                searchFoodByExpiredDate.add(x);
            }
        }
        return searchFoodByExpiredDate;
    }
    public void searchFoodByExpiredDate() {
        LocalDate expiredDate;
        while (true) {
            try {
                expiredDate = LocalDate.parse(MyToys.getStringNoFormat("Input the "
                        + "Expired Date of the Food: (Ex: yyyy-mm-dd)", "The "
                        + "Expired Date of the Food is required!"),
                        DateTimeFormatter.ISO_LOCAL_DATE);
                break;
            } catch (Exception E) {
                System.err.println("Wrong Format Expired Date!!");
            }
        }
        ArrayList<Food> searchFoodByExpiredDate = searchFoodByExpiredDate(foodList, expiredDate);
        if (searchFoodByExpiredDate.isEmpty()) {
            System.err.println("This food does not exist. Nothing to search");
        } else {
            System.out.printf("|%-10s|%-15s|%-15s|%-15s|%-20s|%-20s|\n", "ID", "Name", 
                    "Weight", "Type", "Place", "ExpiredDate");
            for (Food x : searchFoodByExpiredDate) {
                x.showInformation();
            }
        }
        
    }
    
    
    
    
    public void updateFood() {
        String id;
        String tmpName, tmpType, tmpPlace;  //nhập tên mới thay cho tên cũ
        double tmpWeight;
        LocalDate tmpExpiredDate;
        Food x;  //con trỏ trỏ tạm thời đến Pet tìm thấy
        id = MyToys.getString("Input pet id: ", "Pet id is required!", "^(S|s)[(E|e)(S|s)]\\d{6}$");
        x = searchFoodObjectByID(id);
        System.out.println("------------------------------------");
        if (x == null)
            System.out.println("Not found!!!");
        else {
            System.out.println("Here is the Food before updating");
            x.showInformation();
            System.out.println("You are required to input a new name");
            tmpName = MyToys.getString("Input new name: ", "Name is required!", "[a-zA-Z0-9]{1,}");
            x.setName(tmpName);
            System.out.println("You are required to input a new weight");
            tmpWeight = MyToys.getADouble("Input the Weight of the Food: (Ex: 1, 2.1, 3.3,...)",
                "The Weight of Food must be greater than 0.", 0);
            x.setWeight(tmpWeight);
            System.out.println("You are required to input a new type");
            tmpType = MyToys.getString("Input the Type of the Food: ", "The Type of the "
                + "Food is required!", "[a-zA-Z0-9\\s]{1,}");
            x.setType(tmpType);
            System.out.println("You are required to input a new place");
            tmpPlace = MyToys.getString("Input the Place to put the Food: ", "The Place "
                + "to put the Food is required!", "[a-zA-Z0-9\\s]{1,}");
            x.setPlace(tmpPlace);
            System.out.println("You are required to input a new expiredDate");
            while (true) {
                try {
                    tmpExpiredDate = LocalDate.parse(MyToys.getStringNoFormat("Input the "
                            + "Expired Date of the Food: (Ex: yyyy-mm-dd)", "The "
                            + "Expired Date of the Food is required!"),
                            DateTimeFormatter.ISO_LOCAL_DATE);
                    break;
                } catch (Exception E) {
                    System.err.println("Wrong Format Expired Date!!");
                }
            }
            x.setExpiredDate(tmpExpiredDate);
            System.out.println("The food information is updated successfully!");
        }     
    }
    
    
    
    //remove theo weight
    public void removeWeight() {
        double weight;
        int pos;
        weight = MyToys.getADouble("Input food weight: ", "The weight is required", 0);
        pos = searchFoodByWeightV1(weight);//sửa ở đây
        int count = searchFoodByWeightV2(weight);
        if (pos == -1) {
            System.out.println("This food does not exist");
        } else {
            if (MyToys.confirmMessage("Do you want to delete this food? (y/n) ") == 1) {
              do{
                  for (int i = 0; i < foodList.size(); i++) {
                    if (foodList.get(i).getWeight() == weight) {
                        count--;
                        foodList.remove(i);
                        System.out.println("The selected food is removed successfully!\n");
                    }
                  }
              }while(count!=0);
            }else {
                        System.out.println("The selected food is removed failed!\n");
                    }
                }
            }
    public int searchFoodByWeightV1(double weight) {
        int pos;
        if (foodList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getWeight() == weight) {
                return i;
            }
        }
        return -1;
    }
 public int searchFoodByWeightV2(double weight) {
        int count = 0;
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getWeight() == weight) {
                count++;
            }
        }
        return count;
    }
 
    //xoá theo ngày
    public void removeDate() {
        LocalDate date;
        int pos;
        while (true) {
                try {
                    date = LocalDate.parse(MyToys.getStringNoFormat("Input the "
                            + "Expired Date of the Food: (Ex: yyyy-mm-dd)", "The "
                            + "Expired Date of the Food is required!"),
                            DateTimeFormatter.ISO_LOCAL_DATE);
                    break;
                } catch (Exception E) {
                    System.err.println("Wrong Format Expired Date!!");
                }
            }
        pos = searchFoodByDateV1(date);
        int count = searchFoodByDateV2(date);
        if (pos == -1) {
            System.out.println("This food does not exist");
        } else {
            if (MyToys.confirmMessage("Do you want to delete this food? (y/n) ") == 1) {
                do {
                    for (int i = 0; i < foodList.size(); i++) {
                        if (foodList.get(i).getExpiredDate().compareTo(date) == 0) {
                            count--;
                            foodList.remove(i);
                            System.out.println("The selected food is removed successfully!\n");
                        }
                    }
                } while (count != 0);
            } else {
                System.out.println("The selected food is removed failed!\n");
            }
        }
    }
    public int searchFoodByDateV1(LocalDate date) {
        if (foodList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getExpiredDate().compareTo(date) == 0) {
                return i;
            }
        }
        return -1;
    }

    public int searchFoodByDateV2(LocalDate date) {
        int count = 0;
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getExpiredDate().compareTo(date) == 0) {
                count++;
            }
        }
        return count;
    }
    
    //xoá theo type
    public void removeType() {
        String idFood;
        int pos;
        idFood = MyToys.getString("Input food ID: ", "Id is required", "^(S|s)[(E|e)(S|s)]\\d{6}$");
        pos = searchFoodByTypeV1(idFood);
        int count = searchFoodByTypeV2(idFood);
        if (pos == -1) {
            System.out.println("This food does not exist");
        } else {
            if (MyToys.confirmMessage("Do you want to delete this food? (y/n) ") == 1) {
                do {
                    for (int i = 0; i < foodList.size(); i++) {
                        if (foodList.get(i).getType().compareTo(idFood) == 0) {
                            count--;
                            foodList.remove(i);
                            System.out.println("The selected food is removed successfully!\n");
                        }
                    }
                } while (count != 0);
            } else {
                System.out.println("The selected food is removed failed!\n");
            }
        }
    }
    public int searchFoodByTypeV1(String idFood) {
        int pos;
        if (foodList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getType().compareTo(idFood) == 0) {
                return i;
            }
        }
        return -1;
    }
    public int searchFoodByTypeV2(String idFood) {
        int count = 0;
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getType().compareTo(idFood) == 0) {
                count++;
            }
        }
        return count;
    }
    
    //xoá theo place
    public void removePlace() {
        String placeFood;
        int pos;
        placeFood = MyToys.getString("Input food place: ", "The place is required", "[a-zA-Z0-9\\s]{1,}");
        pos = searchFoodByPlaceV1(placeFood);
        int count = searchFoodByPlaceV2(placeFood);
        if (pos == -1) {
            System.out.println("This food does not exist");
        } else {
            if (MyToys.confirmMessage("Do you want to delete this food? (y/n) ") == 1) {
                do {
                    for (int i = 0; i < foodList.size(); i++) {
                        if (foodList.get(i).getPlace().compareTo(placeFood) == 0) {
                            count--;
                            foodList.remove(i);
                            System.out.println("The selected food is removed successfully!\n");
                        }
                    }
                } while (count != 0);
            } else {
                System.out.println("The selected food is removed failed!\n");
            }
        }
    }
    public int searchFoodByPlaceV1(String idFood) {
        int pos;
        if (foodList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getPlace().compareTo(idFood) == 0) {
                return i;
            }
        }
        return -1;
    }
     public int searchFoodByPlaceV2(String idFood) {
        int count = 0;
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getPlace().compareTo(idFood) == 0) {
                count++;
            }
        }
        return count;
    }

    //remove theo name
    public void removeName() {
        String nameFood;
        int pos;
        nameFood = MyToys.getString("Input food name: ", "The name is required", "[a-zA-Z0-9\\s]{1,}");
        pos = searchFoodNameV1(nameFood);
        int count = searchFoodNameV2(nameFood);
        if (pos == -1) {
            System.out.println("This food does not exist");
        } else {
            if (MyToys.confirmMessage("Do you want to delete this food? (y/n) ") == 1) {
                do{
                    for (int i = 0; i < foodList.size(); i++) {
                    if (foodList.get(i).getName().compareTo(nameFood) == 0) {
                        count--;
                        foodList.remove(i);
                        System.out.println("The selected food is removed successfully!\n");
                    }
                    }
                }while(count!=0);
            }else {
                        System.out.println("The selected food is removed failed!\n");
                    }
                }
            }

    public int searchFoodNameV1(String nameFood) {
        if (foodList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getName().compareTo(nameFood) == 0) {
                return i;
            }
        }
        return -1;
    }
    public int searchFoodNameV2(String nameFood) {
        int count = 0;
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getName().compareTo(nameFood) == 0) {
                count++;
            }
        }
        return count;
    }

    //xếp giảm theo id
    public void printDescendingId() {
        Comparator nameBalance = new Comparator<Food>() {
            public int compare(Food o1, Food o2) {
                return o2.getId().compareToIgnoreCase(o1.getId());//tăng dần thì đảo o2 với o1
            }
        };
        Collections.sort(foodList, nameBalance);
        String header = String.format("|%-10s|%-15s|%-15s|%-15s|%-20s|%-20s|", "ID", "Name", "Weight", "Type", "Place", "Date");
        System.out.println(header);
        //2 dòng trên coppy của getList
        for (int i = 0; i < foodList.size(); i++) {
            foodList.get(i).showInformation();
        }
        if (foodList.isEmpty()) {
            System.out.println("nothing!");
            return;
        }

    }
    
    //xếp giảm theo type
    public void printDescendingType() {
        Comparator nameBalance = new Comparator<Food>() {
            public int compare(Food o1, Food o2) {
                return o2.getType().compareToIgnoreCase(o1.getType());//tăng dần thì đảo o2 với o1
            }
        };
        Collections.sort(foodList, nameBalance);
        String header = String.format("|%-10s|%-15s|%-15s|%-15s|%-20s|%-20s|", "ID", "Name", "Weight", "Type", "Place", "Date");
        System.out.println(header);
        //2 dòng trên coppy của getList
        for (int i = 0; i < foodList.size(); i++) {
            foodList.get(i).showInformation();
        }
        if (foodList.isEmpty()) {
            System.out.println("nothing");
            return;
        }
    }
    
    //xếp giảm theo Place
    public void printDescendingPlace() {
        if (foodList.isEmpty()) {
            System.out.println("The list is empty. Nothing to print!");
            return;
        }
        Comparator nameBalance = new Comparator<Food>() {
            public int compare(Food o1, Food o2) {
                return o2.getPlace().compareToIgnoreCase(o1.getPlace());
            }
        };
        Collections.sort(foodList, nameBalance);
        String header = String.format("|%-10s|%-15s|%-15s|%-15s|%-20s|%-20s|", "ID", "Name", "Weight", "Type", "Place", "Date");
        System.out.println(header);
        //2 dòng trên coppy của getList
        for (int k = 0; k < foodList.size(); k++) {
            foodList.get(k).showInformation();
        }
    }
     
    //xếp giảm theo Name
    public void printDescendingName() {
        if (foodList.isEmpty()) {
            System.out.println("The list is empty. Nothing to print!");
            return;
        }
        Comparator nameBalance = new Comparator<Food>() {
            public int compare(Food o1, Food o2) {
                return o2.getName().compareToIgnoreCase(o1.getName());
            }
        };
        Collections.sort(foodList, nameBalance);
        String header = String.format("|%-10s|%-15s|%-15s|%-15s|%-20s|%-20s|", "ID", "Name", "Weight", "Type", "Place", "Date");
        System.out.println(header);
        //2 dòng trên coppy của getList
        for (int k = 0; k < foodList.size(); k++) {
            foodList.get(k).showInformation();
        }
    }
    
    //xếp giảm theo weight

    public void printDescendingWeight() {
        if (foodList.isEmpty()) {
            System.out.println("The list is empty. Nothing to print!");
            return;
        }
        Comparator nameBalance = new Comparator<Food>() {
            public int compare(Food o1, Food o2) {
                if (o2.getWeight() > o1.getWeight()) {
                    return 1;
                } else if (o2.getWeight() < o1.getWeight()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
        Collections.sort(foodList, nameBalance);
        String header = String.format("|%-10s|%-15s|%-15s|%-15s|%-20s|%-20s|", "ID", "Name", "Weight", "Type", "Place", "Date");
        System.out.println(header);
        //2 dòng trên coppy của getList
        for (int k = 0; k < foodList.size(); k++) {
            foodList.get(k).showInformation();
        }
    }

    
    public void printFood() {
        if (foodList.isEmpty()) {
            System.out.println("List Food is empty");
        } else {
            for (int i = 0; i < foodList.size(); i++) {
                if (foodList.get(i).getWeight() > 2) {
                    foodList.get(i).setName("Completed");
                } else {
                    foodList.get(i).setName("Not Completed");
                }
            }
            for (Food x : foodList) {
                x.showInformation();
            }
        }
    }

    public void writeFile(ArrayList<Food> foodList, String fName) throws IOException, ClassNotFoundException {
        FileOutputStream f = new FileOutputStream(fName);
        ObjectOutputStream of = new ObjectOutputStream(f);
        of.writeObject(foodList);
        f.close();
        of.close();
    }

    public ArrayList<Food> readFile(String fName) throws IOException, ClassNotFoundException {
        ArrayList<Food> foodList = null;
        FileInputStream f = new FileInputStream(fName);
        ObjectInputStream of = new ObjectInputStream(f);
        foodList = (ArrayList<Food>) of.readObject();
        f.close();
        of.close();
        return foodList;
    }
    
    
}



