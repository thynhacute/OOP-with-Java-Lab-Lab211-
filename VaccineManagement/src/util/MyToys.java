package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class MyToys {

    private static Scanner sc = new Scanner(System.in);

    public static int getAChoice(String inputMsg, String errorMsg, int lowerBound,
            int upperBound) {
        if (lowerBound > upperBound) {
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }
        int n;
        while (true) {
            try {
                System.out.println(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                if (n < lowerBound || n > upperBound) {
                    throw new Exception();
                }
                return n;
            } catch (Exception e) {
                System.err.println(errorMsg);
            }
        }
    }
    public static double getDouble(String inputMsg, String errorMsg) {
        double n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Double.parseDouble(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String getString(String inputMsg, String errorMsg) {
        String name;
        while (true) {
            System.out.print(inputMsg);

            name = sc.nextLine().trim();
            if (name.length() == 0 || name.isEmpty()) {
                System.out.println(errorMsg);
            } else {
                return name;
            }
        }
    }
    
    public static String getStringFormat(String inputMsg, String errorMsg, String format) {
        String id;
        boolean match;
        while (true) {
            System.out.println(inputMsg);
            id = sc.nextLine().trim();
            match = id.matches(format);
            if (id.length() == 0 || id.isEmpty() || match == false) {
                System.err.println(errorMsg);
            } else {
                return id;
            }
        }
    }

    public static String getId(String inputMsg, String errorMsg) {
        String id;
        boolean match;
        while (true) {
            System.out.print(inputMsg);
            id = sc.nextLine().trim().toUpperCase();
            if (id.length() == 0 || id.isEmpty()) {
                System.out.println(errorMsg);
            } else {
                return id;
            }
        }
    }

    public static int getInteger(String inputMsg, String errorMsg) {
        int n;
        while (true) {
            try {
                System.out.print(inputMsg);
                n = Integer.parseInt(sc.nextLine());
                return n;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static boolean getBooleanRemove() {
        while (true) {
            String result = MyToys.getString("Are you sure you will remove? (Yes/No): ", "only (Yes/No)");

            if (result.equalsIgnoreCase("Yes")) {
                return true;
            }

            if (result.equalsIgnoreCase("No")) {
                return false;
            }

            System.err.println("Please Enter <Yes/No> Or <yes/no>");
        }

    }

    public static boolean getBooleanAdd() {
        while (true) {
            String result = MyToys.getString("Do you want to add to the list? (Yes/No): ", "only (Yes/No)");

            if (result.equalsIgnoreCase("Yes")) {
                return true;
            }

            if (result.equalsIgnoreCase("No")) {
                return false;
            }

            System.err.println("Please Enter <Yes/No> or <yes/no>");
        }
    }

    public static Date getDate(){
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = null;
        String date2 = null;
        boolean check = false;
        date.setLenient(false);
        do {
            do {
                try {
                    System.out.println("Input Date: ");
                    date2 = sc.nextLine();
                    if (!date2.matches("\\d{1,2}[/|/]\\d{1,2}[/|/]\\d{4}")) {
                        throw new Exception();
                    }check = true;
                } catch (Exception e) {
                    System.out.println("ERROR!!!");
                }

            } while (check == false);
            try {
                date1 = date.parse(date2);
            } catch (Exception e) {
                check = false;
            }
        } while (check == false);
        return date1;
    }

    public static String dateToString(Date date) {
        SimpleDateFormat date1 = new SimpleDateFormat("dd/MM/yyyy");
        if (date == null) {
            return String.format(" ", date);
        }
        return date1.format(date);
    }
}
