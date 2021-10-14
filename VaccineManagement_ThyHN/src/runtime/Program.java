package runtime;

import data.InjectionManagement;
import ui.Menu;

public class Program {
    public static void main(String[] args) {
        int choice = 0;
        InjectionManagement cabinet = new InjectionManagement();
        cabinet.saveFileStudent();
        cabinet.saveFileVaccine();
        cabinet.saveFileInjection();
        Menu menu = new Menu("Vaccine Management System");
        menu.addNewOption("| 1. Show information all students have been injected.                         |");
        menu.addNewOption("| 2. Add student's vaccine injection information.                              |");
        menu.addNewOption("| 3. Updating information of students' vaccine injection.                      |");
        menu.addNewOption("| 4. Delete student vaccine injection information.                             |");
        menu.addNewOption("| 5. Search for injection information by studentID.                            |");
        menu.addNewOption("| 6. Print List.                                                               |");
        menu.addNewOption("| 7. Save to file.                                                             |");
        menu.addNewOption("| 8. Others - Quit.                                                            |");
        do {
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:{
                    cabinet.showInjection();
                    break;
                }
                case 2:{
                    cabinet.addInjection();
                    break;
                }
                case 3:{
                    cabinet.updateInjection();
                    break;
                }
                case 4:{
                    cabinet.deleteInforStudentID();
                    break;
                }
                case 5:{
                    cabinet.searchInjectionByDate();
                    break;
                }
                case 6:{
                    cabinet.removeDateLimmited();
                    break;
                }
                case 7:{
                    cabinet.saveFileInjection();
                    break;
                }
                case 8:{
                    System.out.println("Bye bye! See you later!");
                }
            }
        } while (choice != 8);
    }
}