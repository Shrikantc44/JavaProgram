package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class PatientManager {
    private Connection connection;
    private Scanner scanner;

    public PatientManager(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void managePatients() {
        while (true) {
            System.out.println("\n=== PATIENT MANAGEMENT ===");
            System.out.println("1. Add New Patient");
            System.out.println("2. View All Patients");
            System.out.println("3. View Patient Details");
            System.out.println("4. Update Patient Record");
            System.out.println("5. Delete Patient");
            System.out.println("6. Search Patients");
            System.out.println("7. Back to Main Menu");
            
            int choice = InputValidator.getIntInput(scanner, "Enter your choice: ", 1, 7);
            
            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    viewAllPatients();
                    break;
                case 3:
                    viewPatientDetails();
                    break;
                case 4:
                    updatePatient();
                    break;
                case 5:
                    deletePatient();
                    break;
                case 6:
                    searchPatients();
                    break;
                case 7:
                    return;
            }
        }
    }

    public void addPatient() {
        System.out.println("\n=== ADD NEW PATIENT ===");
        String name = InputValidator.getStringInput(scanner, "Enter patient name: ");
        int age = InputValidator.getIntInput(scanner, "Enter patient age: ", 0, 120);
        String gender = InputValidator.getStringInput(scanner, "Enter patient gender (M/F/O): ");
        String contact = InputValidator.getStringInput(scanner, "Enter contact number: ", false);
        String address = InputValidator.getStringInput(scanner, "Enter address: ", false);
        String medicalHistory = InputValidator.getStringInput(scanner, "Enter medical history: ", false);

        try {
            String query = "INSERT INTO patients(name, age, gender, contact, address, medical_history, registration_date) " +
                          "VALUES (?, ?, ?, ?, ?, ?, CURDATE())";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, contact);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, medicalHistory);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient added successfully!");
            } else {
                System.out.println("Failed to add patient.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void viewAllPatients() {
        System.out.println("\n=== ALL PATIENTS ===");
        try {
            String query = "SELECT id, name, age, gender, contact, registration_date FROM patients ORDER BY name";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("+-----+----------------------+-----+--------+----------------+----------------+");
            System.out.println("| ID  | Name                 | Age | Gender | Contact        | Registered On  |");
            System.out.println("+-----+----------------------+-----+--------+----------------+----------------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String contact = resultSet.getString("contact");
                String regDate = resultSet.getString("registration_date");

                System.out.printf("| %-3d | %-20s | %-3d | %-6s | %-14s | %-14s |\n", 
                                id, name, age, gender, contact, regDate);
            }
            System.out.println("+-----+----------------------+-----+--------+----------------+----------------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void viewPatientDetails() {
        int patientId = InputValidator.getIntInput(scanner, "Enter patient ID: ");
        try {
            String query = "SELECT * FROM patients WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("\n=== PATIENT DETAILS ===");
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Age: " + resultSet.getInt("age"));
                System.out.println("Gender: " + resultSet.getString("gender"));
                System.out.println("Contact: " + resultSet.getString("contact"));
                System.out.println("Address: " + resultSet.getString("address"));
                System.out.println("Medical History: " + resultSet.getString("medical_history"));
                System.out.println("Registration Date: " + resultSet.getString("registration_date"));
            } else {
                System.out.println("Patient not found with ID: " + patientId);
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void updatePatient() {
        int patientId = InputValidator.getIntInput(scanner, "Enter patient ID to update: ");
        
        try {
            // First check if patient exists
            if (!patientExists(patientId)) {
                System.out.println("Patient not found with ID: " + patientId);
                return;
            }

            System.out.println("\n=== UPDATE PATIENT ===");
            System.out.println("Leave field blank to keep current value");
            
            String name = InputValidator.getStringInput(scanner, "Enter new name: ", true);
            Integer age = InputValidator.getIntInput(scanner, "Enter new age (0-120): ", true, 0, 120);
            String gender = InputValidator.getStringInput(scanner, "Enter new gender (M/F/O): ", true);
            String contact = InputValidator.getStringInput(scanner, "Enter new contact: ", true);
            String address = InputValidator.getStringInput(scanner, "Enter new address: ", true);
            String medicalHistory = InputValidator.getStringInput(scanner, "Enter new medical history: ", true);

            StringBuilder queryBuilder = new StringBuilder("UPDATE patients SET ");
            boolean needsComma = false;

            if (!name.isEmpty()) {
                queryBuilder.append("name = ?");
                needsComma = true;
            }
            if (age != null) {
                if (needsComma) queryBuilder.append(", ");
                queryBuilder.append("age = ?");
                needsComma = true;
            }
            if (!gender.isEmpty()) {
                if (needsComma) queryBuilder.append(", ");
                queryBuilder.append("gender = ?");
                needsComma = true;
            }
            if (!contact.isEmpty()) {
                if (needsComma) queryBuilder.append(", ");
                queryBuilder.append("contact = ?");
                needsComma = true;
            }
            if (!address.isEmpty()) {
                if (needsComma) queryBuilder.append(", ");
                queryBuilder.append("address = ?");
                needsComma = true;
            }
            if (!medicalHistory.isEmpty()) {
                if (needsComma) queryBuilder.append(", ");
                queryBuilder.append("medical_history = ?");
            }
            
            queryBuilder.append(" WHERE id = ?");
            
            PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString());
            int paramIndex = 1;
            
            if (!name.isEmpty()) preparedStatement.setString(paramIndex++, name);
            if (age != null) preparedStatement.setInt(paramIndex++, age);
            if (!gender.isEmpty()) preparedStatement.setString(paramIndex++, gender);
            if (!contact.isEmpty()) preparedStatement.setString(paramIndex++, contact);
            if (!address.isEmpty()) preparedStatement.setString(paramIndex++, address);
            if (!medicalHistory.isEmpty()) preparedStatement.setString(paramIndex++, medicalHistory);
            
            preparedStatement.setInt(paramIndex, patientId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient updated successfully!");
            } else {
                System.out.println("Failed to update patient.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void deletePatient() {
        int patientId = InputValidator.getIntInput(scanner, "Enter patient ID to delete: ");
        
        try {
            // Confirm deletion
            String confirm = InputValidator.getStringInput(scanner, 
                "Are you sure you want to delete this patient and all associated records? (yes/no): ");
            
            if (!confirm.equalsIgnoreCase("yes")) {
                System.out.println("Deletion cancelled.");
                return;
            }

            String query = "DELETE FROM patients WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Patient and all associated records deleted successfully!");
            } else {
                System.out.println("No patient found with ID: " + patientId);
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void searchPatients() {
        System.out.println("\n=== SEARCH PATIENTS ===");
        System.out.println("1. Search by Name");
        System.out.println("2. Search by Contact");
        System.out.println("3. Search by Age Range");
        System.out.println("4. Back to Patient Menu");
        
        int choice = InputValidator.getIntInput(scanner, "Enter your choice: ", 1, 4);
        
        switch (choice) {
            case 1:
                searchByName();
                break;
            case 2:
                searchByContact();
                break;
            case 3:
                searchByAgeRange();
                break;
            case 4:
                return;
        }
    }

    private void searchByName() {
        String name = InputValidator.getStringInput(scanner, "Enter name or part of name to search: ");
        try {
            String query = "SELECT id, name, age, gender, contact FROM patients WHERE name LIKE ? ORDER BY name";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n=== SEARCH RESULTS ===");
            System.out.println("+-----+----------------------+-----+--------+----------------+");
            System.out.println("| ID  | Name                 | Age | Gender | Contact        |");
            System.out.println("+-----+----------------------+-----+--------+----------------+");

            boolean found = false;
            while (resultSet.next()) {
                found = true;
                int id = resultSet.getInt("id");
                String patientName = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String contact = resultSet.getString("contact");

                System.out.printf("| %-3d | %-20s | %-3d | %-6s | %-14s |\n", 
                                id, patientName, age, gender, contact);
            }
            
            if (!found) {
                System.out.println("| No patients found matching the search criteria. |");
            }
            System.out.println("+-----+----------------------+-----+--------+----------------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private void searchByContact() {
        String contact = InputValidator.getStringInput(scanner, "Enter contact number to search: ");
        try {
            String query = "SELECT id, name, age, gender, contact FROM patients WHERE contact LIKE ? ORDER BY name";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + contact + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n=== SEARCH RESULTS ===");
            System.out.println("+-----+----------------------+-----+--------+----------------+");
            System.out.println("| ID  | Name                 | Age | Gender | Contact        |");
            System.out.println("+-----+----------------------+-----+--------+----------------+");

            boolean found = false;
            while (resultSet.next()) {
                found = true;
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String patientContact = resultSet.getString("contact");

                System.out.printf("| %-3d | %-20s | %-3d | %-6s | %-14s |\n", 
                                id, name, age, gender, patientContact);
            }
            
            if (!found) {
                System.out.println("| No patients found matching the search criteria. |");
            }
            System.out.println("+-----+----------------------+-----+--------+----------------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private void searchByAgeRange() {
        int minAge = InputValidator.getIntInput(scanner, "Enter minimum age: ", 0, 120);
        int maxAge = InputValidator.getIntInput(scanner, "Enter maximum age: ", minAge, 120);
        
        try {
            String query = "SELECT id, name, age, gender, contact FROM patients WHERE age BETWEEN ? AND ? ORDER BY age";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, minAge);
            preparedStatement.setInt(2, maxAge);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n=== SEARCH RESULTS ===");
            System.out.println("+-----+----------------------+-----+--------+----------------+");
            System.out.println("| ID  | Name                 | Age | Gender | Contact        |");
            System.out.println("+-----+----------------------+-----+--------+----------------+");

            boolean found = false;
            while (resultSet.next()) {
                found = true;
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String contact = resultSet.getString("contact");

                System.out.printf("| %-3d | %-20s | %-3d | %-6s | %-14s |\n", 
                                id, name, age, gender, contact);
            }
            
            if (!found) {
                System.out.println("| No patients found in the specified age range. |");
            }
            System.out.println("+-----+----------------------+-----+--------+----------------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public boolean patientExists(int patientId) {
        try {
            String query = "SELECT id FROM patients WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }

    public String getPatientName(int patientId) {
        try {
            String query = "SELECT name FROM patients WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return "Unknown";
    }
}