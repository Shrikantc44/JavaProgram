package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class DoctorManager {
    private Connection connection;
    private Scanner scanner;

    public DoctorManager(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void manageDoctors() {
        while (true) {
            System.out.println("\n=== DOCTOR MANAGEMENT ===");
            System.out.println("1. Add New Doctor");
            System.out.println("2. View All Doctors");
            System.out.println("3. View Doctor Details");
            System.out.println("4. Update Doctor Record");
            System.out.println("5. Delete Doctor");
            System.out.println("6. Search Doctors");
            System.out.println("7. Back to Main Menu");
            
            int choice = InputValidator.getIntInput(scanner, "Enter your choice: ", 1, 7);
            
            switch (choice) {
                case 1:
                    addDoctor();
                    break;
                case 2:
                    viewAllDoctors();
                    break;
                case 3:
                    viewDoctorDetails();
                    break;
                case 4:
                    updateDoctor();
                    break;
                case 5:
                    deleteDoctor();
                    break;
                case 6:
                    searchDoctors();
                    break;
                case 7:
                    return;
            }
        }
    }

    public void addDoctor() {
        System.out.println("\n=== ADD NEW DOCTOR ===");
        String name = InputValidator.getStringInput(scanner, "Enter doctor name: ");
        String specialization = InputValidator.getStringInput(scanner, "Enter specialization: ");
        String contact = InputValidator.getStringInput(scanner, "Enter contact number: ", false);
        String email = InputValidator.getStringInput(scanner, "Enter email: ", false);
        double consultationFee = InputValidator.getDoubleInput(scanner, "Enter consultation fee: ", 0, 10000);
        String availableDays = InputValidator.getStringInput(scanner, "Enter available days (e.g., Mon-Fri): ", false);
        String availableTime = InputValidator.getStringInput(scanner, "Enter available time (e.g., 9AM-5PM): ", false);

        try {
            String query = "INSERT INTO doctors(name, specialization, contact, email, consultation_fee, available_days, available_time) " +
                          "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, specialization);
            preparedStatement.setString(3, contact);
            preparedStatement.setString(4, email);
            preparedStatement.setDouble(5, consultationFee);
            preparedStatement.setString(6, availableDays);
            preparedStatement.setString(7, availableTime);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Doctor added successfully!");
            } else {
                System.out.println("Failed to add doctor.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void viewAllDoctors() {
        System.out.println("\n=== ALL DOCTORS ===");
        try {
            String query = "SELECT id, name, specialization, consultation_fee FROM doctors ORDER BY name";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("+-----+----------------------+----------------------+----------------+");
            System.out.println("| ID  | Name                 | Specialization       | Consultation   |");
            System.out.println("|     |                      |                      | Fee            |");
            System.out.println("+-----+----------------------+----------------------+----------------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                double fee = resultSet.getDouble("consultation_fee");

                System.out.printf("| %-3d | %-20s | %-20s | $%-13.2f |\n", 
                                id, name, specialization, fee);
            }
            System.out.println("+-----+----------------------+----------------------+----------------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void viewDoctorDetails() {
        int doctorId = InputValidator.getIntInput(scanner, "Enter doctor ID: ");
        try {
            String query = "SELECT * FROM doctors WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("\n=== DOCTOR DETAILS ===");
                System.out.println("ID: " + resultSet.getInt("id"));
                System.out.println("Name: " + resultSet.getString("name"));
                System.out.println("Specialization: " + resultSet.getString("specialization"));
                System.out.println("Contact: " + resultSet.getString("contact"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Consultation Fee: $" + resultSet.getDouble("consultation_fee"));
                System.out.println("Available Days: " + resultSet.getString("available_days"));
                System.out.println("Available Time: " + resultSet.getString("available_time"));
            } else {
                System.out.println("Doctor not found with ID: " + doctorId);
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void updateDoctor() {
        int doctorId = InputValidator.getIntInput(scanner, "Enter doctor ID to update: ");
        
        try {
            // First check if doctor exists
            if (!doctorExists(doctorId)) {
                System.out.println("Doctor not found with ID: " + doctorId);
                return;
            }

            System.out.println("\n=== UPDATE DOCTOR ===");
            System.out.println("Leave field blank to keep current value");
            
            String name = InputValidator.getStringInput(scanner, "Enter new name: ", true);
            String specialization = InputValidator.getStringInput(scanner, "Enter new specialization: ", true);
            String contact = InputValidator.getStringInput(scanner, "Enter new contact: ", true);
            String email = InputValidator.getStringInput(scanner, "Enter new email: ", true);
            Double consultationFee = InputValidator.getDoubleInput(scanner, "Enter new consultation fee: ", true, 0, 10000);
            String availableDays = InputValidator.getStringInput(scanner, "Enter new available days: ", true);
            String availableTime = InputValidator.getStringInput(scanner, "Enter new available time: ", true);

            StringBuilder queryBuilder = new StringBuilder("UPDATE doctors SET ");
            boolean needsComma = false;

            if (!name.isEmpty()) {
                queryBuilder.append("name = ?");
                needsComma = true;
            }
            if (!specialization.isEmpty()) {
                if (needsComma) queryBuilder.append(", ");
                queryBuilder.append("specialization = ?");
                needsComma = true;
            }
            if (!contact.isEmpty()) {
                if (needsComma) queryBuilder.append(", ");
                queryBuilder.append("contact = ?");
                needsComma = true;
            }
            if (!email.isEmpty()) {
                if (needsComma) queryBuilder.append(", ");
                queryBuilder.append("email = ?");
                needsComma = true;
            }
            if (consultationFee != null) {
                if (needsComma) queryBuilder.append(", ");
                queryBuilder.append("consultation_fee = ?");
                needsComma = true;
            }
            if (!availableDays.isEmpty()) {
                if (needsComma) queryBuilder.append(", ");
                queryBuilder.append("available_days = ?");
                needsComma = true;
            }
            if (!availableTime.isEmpty()) {
                if (needsComma) queryBuilder.append(", ");
                queryBuilder.append("available_time = ?");
            }
            
            queryBuilder.append(" WHERE id = ?");
            
            PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.toString());
            int paramIndex = 1;
            
            if (!name.isEmpty()) preparedStatement.setString(paramIndex++, name);
            if (!specialization.isEmpty()) preparedStatement.setString(paramIndex++, specialization);
            if (!contact.isEmpty()) preparedStatement.setString(paramIndex++, contact);
            if (!email.isEmpty()) preparedStatement.setString(paramIndex++, email);
            if (consultationFee != null) preparedStatement.setDouble(paramIndex++, consultationFee);
            if (!availableDays.isEmpty()) preparedStatement.setString(paramIndex++, availableDays);
            if (!availableTime.isEmpty()) preparedStatement.setString(paramIndex++, availableTime);
            
            preparedStatement.setInt(paramIndex, doctorId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Doctor updated successfully!");
            } else {
                System.out.println("Failed to update doctor.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void deleteDoctor() {
        int doctorId = InputValidator.getIntInput(scanner, "Enter doctor ID to delete: ");
        
        try {
            // Confirm deletion
            String confirm = InputValidator.getStringInput(scanner, 
                "Are you sure you want to delete this doctor and all associated records? (yes/no): ");
            
            if (!confirm.equalsIgnoreCase("yes")) {
                System.out.println("Deletion cancelled.");
                return;
            }

            String query = "DELETE FROM doctors WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Doctor and all associated records deleted successfully!");
            } else {
                System.out.println("No doctor found with ID: " + doctorId);
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void searchDoctors() {
        System.out.println("\n=== SEARCH DOCTORS ===");
        System.out.println("1. Search by Name");
        System.out.println("2. Search by Specialization");
        System.out.println("3. Search by Availability");
        System.out.println("4. Back to Doctor Menu");
        
        int choice = InputValidator.getIntInput(scanner, "Enter your choice: ", 1, 4);
        
        switch (choice) {
            case 1:
                searchDoctorsByName();
                break;
            case 2:
                searchDoctorsBySpecialization();
                break;
            case 3:
                searchDoctorsByAvailability();
                break;
            case 4:
                return;
        }
    }

    private void searchDoctorsByName() {
        String name = InputValidator.getStringInput(scanner, "Enter name or part of name to search: ");
        try {
            String query = "SELECT id, name, specialization, consultation_fee FROM doctors WHERE name LIKE ? ORDER BY name";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + name + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n=== SEARCH RESULTS ===");
            System.out.println("+-----+----------------------+----------------------+----------------+");
            System.out.println("| ID  | Name                 | Specialization       | Consultation   |");
            System.out.println("|     |                      |                      | Fee            |");
            System.out.println("+-----+----------------------+----------------------+----------------+");

            boolean found = false;
            while (resultSet.next()) {
                found = true;
                int id = resultSet.getInt("id");
                String doctorName = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                double fee = resultSet.getDouble("consultation_fee");

                System.out.printf("| %-3d | %-20s | %-20s | $%-13.2f |\n", 
                                id, doctorName, specialization, fee);
            }
            
            if (!found) {
                System.out.println("| No doctors found matching the search criteria. |");
            }
            System.out.println("+-----+----------------------+----------------------+----------------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private void searchDoctorsBySpecialization() {
        String specialization = InputValidator.getStringInput(scanner, "Enter specialization to search: ");
        try {
            String query = "SELECT id, name, specialization, consultation_fee FROM doctors WHERE specialization LIKE ? ORDER BY name";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + specialization + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n=== SEARCH RESULTS ===");
            System.out.println("+-----+----------------------+----------------------+----------------+");
            System.out.println("| ID  | Name                 | Specialization       | Consultation   |");
            System.out.println("|     |                      |                      | Fee            |");
            System.out.println("+-----+----------------------+----------------------+----------------+");

            boolean found = false;
            while (resultSet.next()) {
                found = true;
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String doctorSpecialization = resultSet.getString("specialization");
                double fee = resultSet.getDouble("consultation_fee");

                System.out.printf("| %-3d | %-20s | %-20s | $%-13.2f |\n", 
                                id, name, doctorSpecialization, fee);
            }
            
            if (!found) {
                System.out.println("| No doctors found matching the search criteria. |");
            }
            System.out.println("+-----+----------------------+----------------------+----------------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private void searchDoctorsByAvailability() {
        String day = InputValidator.getStringInput(scanner, "Enter day to check availability (e.g., Mon): ");
        try {
            String query = "SELECT id, name, specialization, available_days, available_time " +
                          "FROM doctors WHERE available_days LIKE ? ORDER BY name";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + day + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n=== SEARCH RESULTS ===");
            System.out.println("+-----+----------------------+----------------------+----------------------+----------------+");
            System.out.println("| ID  | Name                 | Specialization       | Available Days       | Available Time |");
            System.out.println("+-----+----------------------+----------------------+----------------------+----------------+");

            boolean found = false;
            while (resultSet.next()) {
                found = true;
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String specialization = resultSet.getString("specialization");
                String availableDays = resultSet.getString("available_days");
                String availableTime = resultSet.getString("available_time");

                System.out.printf("| %-3d | %-20s | %-20s | %-20s | %-14s |\n", 
                                id, name, specialization, availableDays, availableTime);
            }
            
            if (!found) {
                System.out.println("| No doctors found available on " + day + ". |");
            }
            System.out.println("+-----+----------------------+----------------------+----------------------+----------------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public boolean doctorExists(int doctorId) {
        try {
            String query = "SELECT id FROM doctors WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }

    public String getDoctorName(int doctorId) {
        try {
            String query = "SELECT name FROM doctors WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return "Unknown";
    }

    public double getConsultationFee(int doctorId) {
        try {
            String query = "SELECT consultation_fee FROM doctors WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getDouble("consultation_fee");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
        return 0.0;
    }

    public boolean isDoctorAvailable(int doctorId, String date, String time) {
        try {
            // First check if doctor is available on the requested day
            String dayQuery = "SELECT available_days, available_time FROM doctors WHERE id = ?";
            PreparedStatement dayStatement = connection.prepareStatement(dayQuery);
            dayStatement.setInt(1, doctorId);
            ResultSet dayResult = dayStatement.executeQuery();
            
            if (!dayResult.next()) {
                return false; // Doctor not found
            }
            
            String availableDays = dayResult.getString("available_days");
            String availableTime = dayResult.getString("available_time");
            
            // Simple check - in a real system you'd parse the days and times
            if (!availableDays.toLowerCase().contains(date.toLowerCase())) {
                return false;
            }
            
            // Then check if doctor has any appointments at the requested time
            String appointmentQuery = "SELECT id FROM appointments WHERE doctor_id = ? AND appointment_date = ? AND appointment_time = ?";
            PreparedStatement appointmentStatement = connection.prepareStatement(appointmentQuery);
            appointmentStatement.setInt(1, doctorId);
            appointmentStatement.setString(2, date);
            appointmentStatement.setString(3, time);
            ResultSet appointmentResult = appointmentStatement.executeQuery();
            
            return !appointmentResult.next(); // Available if no appointment exists
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }
}