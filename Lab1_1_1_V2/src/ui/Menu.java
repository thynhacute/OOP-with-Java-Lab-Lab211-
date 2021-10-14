package ui;

import java.util.ArrayList;
import util.MyToys;

public class Menu {

    private String menuTitle;
    private ArrayList<String> optionList = new ArrayList<>();

    public Menu(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public void addNewOption(String newOption) {
        optionList.add(newOption);
    }

    public void printMenu() {
        if (optionList.isEmpty()) {
            System.out.println("There is no item in the menu");
            return;
        }
        System.out.println("-------------------------------------");
        System.out.println(menuTitle);
        for (String o : optionList) {
            System.out.println(o);
        }
    }

    public int getChoice() {
        String inputMsg = "Choose[1...9]";
        String errorMsg = "You are required to choose the option 1 ...9";
        return MyToys.getAChoice(inputMsg, errorMsg, 1, 9);
    }
}
