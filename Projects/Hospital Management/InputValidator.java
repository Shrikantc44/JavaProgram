package HospitalManagementSystem;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputValidator {
    public static int getIntInput(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static Integer getIntInput(Scanner scanner, String prompt, boolean allowBlank, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            
            if (allowBlank && input.isEmpty()) {
                return null;
            }
            
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static double getDoubleInput(Scanner scanner, String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try {
                double input = Double.parseDouble(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static Double getDoubleInput(Scanner scanner, String prompt, boolean allowBlank, double min, double max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            
            if (allowBlank && input.isEmpty()) {
                return null;
            }
            
            try {
                double value = Double.parseDouble(input);
                if (value >= min && value <= max) {
                    return value;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    public static String getStringInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public static String getStringInput(Scanner scanner, String prompt, boolean allowBlank) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (allowBlank || !input.isEmpty()) {
                return input;
            }
            
            System.out.println("This field cannot be empty. Please enter a value.");
        }
    }

    public static String getDateInput(Scanner scanner, String prompt) {
        return getDateInput(scanner, prompt, false);
    }

    public static String getDateInput(Scanner scanner, String prompt, boolean allowBlank) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (allowBlank && input.isEmpty()) {
                return "";
            }
            
            try {
                LocalDate date = LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
                return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }

    public static String getTimeInput(Scanner scanner, String prompt) {
        return getTimeInput(scanner, prompt, false);
    }

    public static String getTimeInput(Scanner scanner, String prompt, boolean allowBlank) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            
            if (allowBlank && input.isEmpty()) {
                return "";
            }
            
            try {
                LocalTime time = LocalTime.parse(input, DateTimeFormatter.ofPattern("HH:mm"));
                return time.format(DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please use HH:MM (24-hour format).");
            }
        }
    }
}