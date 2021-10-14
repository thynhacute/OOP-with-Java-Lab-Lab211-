
package runtime;

import data.Food;
import data.Refrigerator;
import java.io.IOException;
import java.util.ArrayList;
import ui.Menu;
import util.MyToys;

public class Program {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Menu menu = new Menu("Food System");
        menu.addNewOption("|-----Welcome to Food Management - @ 2021 by <SE151101 - HoangNhaThy >------|");
        menu.addNewOption("|  1. Add a new food.                                                       |");
        menu.addNewOption("|  2. Search a food by name.                                                |");
        menu.addNewOption("|  3. Remove the food by ID.                                                |");
        menu.addNewOption("|  4. Print the food list in the descending order of expired date.          |");
        menu.addNewOption("|  5. List Available.                                                       |");
        menu.addNewOption("|  6. Quit                                                                  |");
        menu.addNewOption("|---------------------------------------------------------------------------|");

        ArrayList<Food> foodList = new ArrayList<>();
        Refrigerator foodRefrigerator = new Refrigerator();
        int choice;
        do {
            menu.printMenu();
            choice = menu.getChoice();
            boolean answer;
            switch (choice) {
                case 1: {
                    do {
                        System.out.println("You are preparing to input a new"
                                + " food profile");
                        foodRefrigerator.addNewFood();
                        answer = MyToys.checkInputYN();
                    } while (answer == true);
                    break;
                }
                case 2: {
                    do {
                        System.out.println("You are preparing to input"
                                + " a food name to search");
                        foodRefrigerator.searchFoodByName();
                        answer = MyToys.checkSearchYN();
                    } while (answer == true);
                    break;
                }
                case 3: {
                    System.out.println("You are preparing to"
                            + " remove the food by ID");
                    foodRefrigerator.removeFood();
                    break;
                }
                case 4: {
                    foodRefrigerator.printFoodListDecendingByExpiredDate();
                    foodRefrigerator.printFood();
                    break;
                }
                case 5: {
                    System.out.println("You are preparing to"
                            + " list available of the food.");
                    foodRefrigerator.listAvailable();
                    break;
                }
                case 6: {
                    System.out.println("Bye bye!!! See you later!!");
                    break;
                }
            }
        } while (choice != 6);
    }
}
