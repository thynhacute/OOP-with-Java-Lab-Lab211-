
package util;

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

    public static double getADouble(String inputMsg, String errorMsg, double lowerBound) {
        double i;
        while (true) {
            try {
                System.out.println(inputMsg);
                i = Double.parseDouble(sc.nextLine());
                if (i < lowerBound) {
                    throw new Exception();
                }
                return i;
            } catch (Exception e) {
                System.err.println("Error! Please input the number greater than" + lowerBound + ".");
            }
        }
    }

    public static String getStringNoFormat(String inputMsg, String errorMsg) {
        String code;
        while (true) {
            System.out.println(inputMsg);
            code = sc.nextLine().trim();
            if (code.length() == 0 || code.isEmpty()) {
                System.err.println(errorMsg);
            } else {
                return code;
            }
        }
    }

    public static String getString(String inputMsg, String errorMsg, String format) {
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

    public static boolean checkInputYN() {
        while (true) {
            String result = getStringNoFormat("Do you want to input more "
                    + "food? Please answer Y/N", "Your answer is required!");
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Input Y/N or y/n!");
        }
    }

    public static boolean checkRemoveYN() {
        while (true) {
            String result = getStringNoFormat("Do you want to remove food? Please answer Y/N", "Your answer is required!");
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Input Y/N or y/n!");
        }
    }

    public static boolean checkSearchYN() {
        while (true) {
            String result = getStringNoFormat("Do you want to search more food? Please answer Y/N", "Your answer is required!");
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Input Y/N or y/n!");
        }
    }
    public static int confirmMessage(String inputMsg) {
        int result = -1;
        System.out.print(inputMsg);
        String answer = sc.nextLine();
        if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
            result = 1;
        } else if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")) {
            result = 0;
        }
        return result;
    }
}
