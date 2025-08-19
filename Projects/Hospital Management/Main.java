package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Initialize database
            DatabaseManager.initializeDatabase();
            
            // Create connection
            Connection connection = DatabaseManager.getConnection();
            Scanner scanner = new Scanner(System.in);
            
            // Create managers
            PatientManager patientManager = new PatientManager(connection, scanner);
            DoctorManager doctorManager = new DoctorManager(connection, scanner);
            AppointmentManager appointmentManager = new AppointmentManager(connection, scanner, patientManager, doctorManager);
            BillingManager billingManager = new BillingManager(connection, scanner, patientManager, doctorManager);
            
            // Main menu
            boolean running = true;
            while (running) {
                System.out.println("\n=== HOSPITAL MANAGEMENT SYSTEM ===");
                System.out.println("1. Patient Management");
                System.out.println("2. Doctor Management");
                System.out.println("3. Appointment Management");
                System.out.println("4. Billing Management");
                System.out.println("5. Reports");
                System.out.println("6. Exit");
                
                int choice = InputValidator.getIntInput(scanner, "Enter your choice: ", 1, 6);
                
                switch (choice) {
                    case 1:
                        patientManager.managePatients();
                        break;
                    case 2:
                        doctorManager.manageDoctors();
                        break;
                    case 3:
                        appointmentManager.manageAppointments();
                        break;
                    case 4:
                        billingManager.manageBilling();
                        break;
                    case 5:
                        showReportsMenu(patientManager, doctorManager, appointmentManager, billingManager);
                        break;
                    case 6:
                        running = false;
                        System.out.println("Exiting Hospital Management System. Goodbye!");
                        break;
                }
            }
            
            // Close resources
            scanner.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            System.err.println("Database connection error: " + e.getMessage());
        }
    }

    private static void showReportsMenu(PatientManager patientManager, DoctorManager doctorManager,
                                      AppointmentManager appointmentManager, BillingManager billingManager) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("\n=== REPORTS ===");
            System.out.println("1. Patient List");
            System.out.println("2. Doctor List");
            System.out.println("3. Appointment History");
            System.out.println("4. Financial Report");
            System.out.println("5. Back to Main Menu");
            
            int choice = InputValidator.getIntInput(scanner, "Enter your choice: ", 1, 5);
            
            switch (choice) {
                case 1:
                    patientManager.viewAllPatients();
                    break;
                case 2:
                    doctorManager.viewAllDoctors();
                    break;
                case 3:
                    appointmentManager.viewAllAppointments();
                    break;
                case 4:
                    billingManager.generateFinancialReport();
                    break;
                case 5:
                    return;
            }
        }
    }
}