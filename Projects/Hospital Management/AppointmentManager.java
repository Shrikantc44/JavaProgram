package HospitalManagementSystem;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AppointmentManager {
    private Connection connection;
    private Scanner scanner;
    private PatientManager patientManager;
    private DoctorManager doctorManager;

    public AppointmentManager(Connection connection, Scanner scanner, 
                            PatientManager patientManager, DoctorManager doctorManager) {
        this.connection = connection;
        this.scanner = scanner;
        this.patientManager = patientManager;
        this.doctorManager = doctorManager;
    }

    public void manageAppointments() {
        while (true) {
            System.out.println("\n=== APPOINTMENT MANAGEMENT ===");
            System.out.println("1. Book New Appointment");
            System.out.println("2. View All Appointments");
            System.out.println("3. View Appointment Details");
            System.out.println("4. Update Appointment");
            System.out.println("5. Cancel Appointment");
            System.out.println("6. Search Appointments");
            System.out.println("7. Back to Main Menu");
            
            int choice = InputValidator.getIntInput(scanner, "Enter your choice: ", 1, 7);
            
            switch (choice) {
                case 1:
                    bookAppointment();
                    break;
                case 2:
                    viewAllAppointments();
                    break;
                case 3:
                    viewAppointmentDetails();
                    break;
                case 4:
                    updateAppointment();
                    break;
                case 5:
                    cancelAppointment();
                    break;
                case 6:
                    searchAppointments();
                    break;
                case 7:
                    return;
            }
        }
    }

    public void bookAppointment() {
        System.out.println("\n=== BOOK NEW APPOINTMENT ===");
        
        // Display all patients for reference
        patientManager.viewAllPatients();
        int patientId = InputValidator.getIntInput(scanner, "Enter patient ID: ");
        
        if (!patientManager.patientExists(patientId)) {
            System.out.println("Patient not found with ID: " + patientId);
            return;
        }
        
        // Display all doctors for reference
        doctorManager.viewAllDoctors();
        int doctorId = InputValidator.getIntInput(scanner, "Enter doctor ID: ");
        
        if (!doctorManager.doctorExists(doctorId)) {
            System.out.println("Doctor not found with ID: " + doctorId);
            return;
        }
        
        // Get appointment date
        String date = InputValidator.getDateInput(scanner, "Enter appointment date (YYYY-MM-DD): ");
        
        // Get appointment time
        String time = InputValidator.getTimeInput(scanner, "Enter appointment time (HH:MM): ");
        
        // Check doctor availability
        if (!doctorManager.isDoctorAvailable(doctorId, date, time)) {
            System.out.println("Doctor is not available at the requested date/time.");
            return;
        }
        
        // Get appointment notes
        String notes = InputValidator.getStringInput(scanner, "Enter appointment notes (optional): ", true);
        
        try {
            String query = "INSERT INTO appointments(patient_id, doctor_id, appointment_date, appointment_time, notes) " +
                          "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            preparedStatement.setInt(2, doctorId);
            preparedStatement.setString(3, date);
            preparedStatement.setString(4, time);
            preparedStatement.setString(5, notes.isEmpty() ? null : notes);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Appointment booked successfully!");
                
                // Automatically create a bill for the appointment
                createAppointmentBill(patientId, doctorId);
            } else {
                System.out.println("Failed to book appointment.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private void createAppointmentBill(int patientId, int doctorId) {
        try {
            // Get the last inserted appointment ID
            String lastIdQuery = "SELECT LAST_INSERT_ID() as last_id";
            Statement lastIdStatement = connection.createStatement();
            ResultSet lastIdResult = lastIdStatement.executeQuery(lastIdQuery);
            
            int appointmentId = 0;
            if (lastIdResult.next()) {
                appointmentId = lastIdResult.getInt("last_id");
            }
            
            // Get doctor's consultation fee
            double fee = doctorManager.getConsultationFee(doctorId);
            
            // Insert bill record
            String billQuery = "INSERT INTO bills(patient_id, doctor_id, appointment_id, amount, date, status) " +
                             "VALUES (?, ?, ?, ?, CURDATE(), 'Pending')";
            PreparedStatement billStatement = connection.prepareStatement(billQuery);
            billStatement.setInt(1, patientId);
            billStatement.setInt(2, doctorId);
            billStatement.setInt(3, appointmentId);
            billStatement.setDouble(4, fee);
            
            billStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error creating appointment bill: " + e.getMessage());
        }
    }

    public void viewAllAppointments() {
        System.out.println("\n=== ALL APPOINTMENTS ===");
        try {
            String query = "SELECT a.id, p.name as patient_name, d.name as doctor_name, " +
                         "a.appointment_date, a.appointment_time, a.status " +
                         "FROM appointments a " +
                         "JOIN patients p ON a.patient_id = p.id " +
                         "JOIN doctors d ON a.doctor_id = d.id " +
                         "ORDER BY a.appointment_date, a.appointment_time";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("+-----+----------------------+----------------------+----------------+----------+----------+");
            System.out.println("| ID  | Patient              | Doctor               | Date           | Time     | Status   |");
            System.out.println("+-----+----------------------+----------------------+----------------+----------+----------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String patientName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                String date = resultSet.getString("appointment_date");
                String time = resultSet.getString("appointment_time");
                String status = resultSet.getString("status");

                System.out.printf("| %-3d | %-20s | %-20s | %-14s | %-8s | %-8s |\n", 
                                id, patientName, doctorName, date, time, status);
            }
            System.out.println("+-----+----------------------+----------------------+----------------+----------+----------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void viewAppointmentDetails() {
        int appointmentId = InputValidator.getIntInput(scanner, "Enter appointment ID: ");
        try {
            String query = "SELECT a.*, p.name as patient_name, d.name as doctor_name, " +
                          "d.specialization, d.consultation_fee " +
                          "FROM appointments a " +
                          "JOIN patients p ON a.patient_id = p.id " +
                          "JOIN doctors d ON a.doctor_id = d.id " +
                          "WHERE a.id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, appointmentId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("\n=== APPOINTMENT DETAILS ===");
                System.out.println("Appointment ID: " + resultSet.getInt("id"));
                System.out.println("Patient: " + resultSet.getString("patient_name"));
                System.out.println("Doctor: " + resultSet.getString("doctor_name"));
                System.out.println("Specialization: " + resultSet.getString("specialization"));
                System.out.println("Date: " + resultSet.getString("appointment_date"));
                System.out.println("Time: " + resultSet.getString("appointment_time"));
                System.out.println("Status: " + resultSet.getString("status"));
                System.out.println("Consultation Fee: $" + resultSet.getDouble("consultation_fee"));
                System.out.println("Notes: " + (resultSet.getString("notes") == null ? "None" : resultSet.getString("notes")));
            } else {
                System.out.println("Appointment not found with ID: " + appointmentId);
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void updateAppointment() {
        int appointmentId = InputValidator.getIntInput(scanner, "Enter appointment ID to update: ");
        
        try {
            // First check if appointment exists
            if (!appointmentExists(appointmentId)) {
                System.out.println("Appointment not found with ID: " + appointmentId);
                return;
            }

            System.out.println("\n=== UPDATE APPOINTMENT ===");
            System.out.println("Leave field blank to keep current value");
            
            // Get current appointment details
            String currentQuery = "SELECT * FROM appointments WHERE id = ?";
            PreparedStatement currentStatement = connection.prepareStatement(currentQuery);
            currentStatement.setInt(1, appointmentId);
            ResultSet currentResult = currentStatement.executeQuery();
            
            if (!currentResult.next()) {
                System.out.println("Error retrieving appointment details.");
                return;
            }
            
            int currentPatientId = currentResult.getInt("patient_id");
            int currentDoctorId = currentResult.getInt("doctor_id");
            String currentDate = currentResult.getString("appointment_date");
            String currentTime = currentResult.getString("appointment_time");
            String currentNotes = currentResult.getString("notes");
            
            // Display current patient and doctor
            System.out.println("Current Patient: " + patientManager.getPatientName(currentPatientId));
            System.out.println("Current Doctor: " + doctorManager.getDoctorName(currentDoctorId));
            
            // Get new values
            String newDate = InputValidator.getDateInput(scanner, "Enter new date (YYYY-MM-DD): ", true);
            String newTime = InputValidator.getTimeInput(scanner, "Enter new time (HH:MM): ", true);
            String newNotes = InputValidator.getStringInput(scanner, "Enter new notes: ", true);
            
            // Use current values if new ones are not provided
            String dateToUse = newDate.isEmpty() ? currentDate : newDate;
            String timeToUse = newTime.isEmpty() ? currentTime : newTime;
            String notesToUse = newNotes.isEmpty() ? currentNotes : newNotes;
            
            // Check doctor availability if date/time is being changed
            if ((!newDate.isEmpty() || !newTime.isEmpty()) && 
                !doctorManager.isDoctorAvailable(currentDoctorId, dateToUse, timeToUse)) {
                System.out.println("Doctor is not available at the requested date/time.");
                return;
            }
            
            // Update appointment
            String updateQuery = "UPDATE appointments SET appointment_date = ?, appointment_time = ?, notes = ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, dateToUse);
            updateStatement.setString(2, timeToUse);
            updateStatement.setString(3, notesToUse);
            updateStatement.setInt(4, appointmentId);
            
            int affectedRows = updateStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Appointment updated successfully!");
            } else {
                System.out.println("Failed to update appointment.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void cancelAppointment() {
        int appointmentId = InputValidator.getIntInput(scanner, "Enter appointment ID to cancel: ");
        
        try {
            // Confirm cancellation
            String confirm = InputValidator.getStringInput(scanner, 
                "Are you sure you want to cancel this appointment? (yes/no): ");
            
            if (!confirm.equalsIgnoreCase("yes")) {
                System.out.println("Cancellation cancelled.");
                return;
            }

            String query = "UPDATE appointments SET status = 'Cancelled' WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, appointmentId);

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Appointment cancelled successfully!");
                
                // Also update any associated bill
                updateBillForCancelledAppointment(appointmentId);
            } else {
                System.out.println("No appointment found with ID: " + appointmentId);
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private void updateBillForCancelledAppointment(int appointmentId) {
        try {
            String query = "UPDATE bills SET status = 'Cancelled' WHERE appointment_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, appointmentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error updating bill status: " + e.getMessage());
        }
    }

    public void searchAppointments() {
        System.out.println("\n=== SEARCH APPOINTMENTS ===");
        System.out.println("1. Search by Patient");
        System.out.println("2. Search by Doctor");
        System.out.println("3. Search by Date");
        System.out.println("4. Search by Status");
        System.out.println("5. Back to Appointment Menu");
        
        int choice = InputValidator.getIntInput(scanner, "Enter your choice: ", 1, 5);
        
        switch (choice) {
            case 1:
                searchByPatient();
                break;
            case 2:
                searchByDoctor();
                break;
            case 3:
                searchByDate();
                break;
            case 4:
                searchByStatus();
                break;
            case 5:
                return;
        }
    }

    private void searchByPatient() {
        patientManager.viewAllPatients();
        int patientId = InputValidator.getIntInput(scanner, "Enter patient ID to search appointments: ");
        
        try {
            String query = "SELECT a.id, p.name as patient_name, d.name as doctor_name, " +
                         "a.appointment_date, a.appointment_time, a.status " +
                         "FROM appointments a " +
                         "JOIN patients p ON a.patient_id = p.id " +
                         "JOIN doctors d ON a.doctor_id = d.id " +
                         "WHERE a.patient_id = ? " +
                         "ORDER BY a.appointment_date, a.appointment_time";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n=== APPOINTMENTS FOR PATIENT: " + patientManager.getPatientName(patientId) + " ===");
            System.out.println("+-----+----------------------+----------------------+----------------+----------+----------+");
            System.out.println("| ID  | Patient              | Doctor               | Date           | Time     | Status   |");
            System.out.println("+-----+----------------------+----------------------+----------------+----------+----------+");

            boolean found = false;
            while (resultSet.next()) {
                found = true;
                int id = resultSet.getInt("id");
                String patientName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                String date = resultSet.getString("appointment_date");
                String time = resultSet.getString("appointment_time");
                String status = resultSet.getString("status");

                System.out.printf("| %-3d | %-20s | %-20s | %-14s | %-8s | %-8s |\n", 
                                id, patientName, doctorName, date, time, status);
            }
            
            if (!found) {
                System.out.println("| No appointments found for this patient. |");
            }
            System.out.println("+-----+----------------------+----------------------+----------------+----------+----------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private void searchByDoctor() {
        doctorManager.viewAllDoctors();
        int doctorId = InputValidator.getIntInput(scanner, "Enter doctor ID to search appointments: ");
        
        try {
            String query = "SELECT a.id, p.name as patient_name, d.name as doctor_name, " +
                         "a.appointment_date, a.appointment_time, a.status " +
                         "FROM appointments a " +
                         "JOIN patients p ON a.patient_id = p.id " +
                         "JOIN doctors d ON a.doctor_id = d.id " +
                         "WHERE a.doctor_id = ? " +
                         "ORDER BY a.appointment_date, a.appointment_time";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n=== APPOINTMENTS FOR DOCTOR: " + doctorManager.getDoctorName(doctorId) + " ===");
            System.out.println("+-----+----------------------+----------------------+----------------+----------+----------+");
            System.out.println("| ID  | Patient              | Doctor               | Date           | Time     | Status   |");
            System.out.println("+-----+----------------------+----------------------+----------------+----------+----------+");

            boolean found = false;
            while (resultSet.next()) {
                found = true;
                int id = resultSet.getInt("id");
                String patientName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                String date = resultSet.getString("appointment_date");
                String time = resultSet.getString("appointment_time");
                String status = resultSet.getString("status");

                System.out.printf("| %-3d | %-20s | %-20s | %-14s | %-8s | %-8s |\n", 
                                id, patientName, doctorName, date, time, status);
            }
            
            if (!found) {
                System.out.println("| No appointments found for this doctor. |");
            }
            System.out.println("+-----+----------------------+----------------------+----------------+----------+----------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private void searchByDate() {
        String date = InputValidator.getDateInput(scanner, "Enter date to search appointments (YYYY-MM-DD): ");
        
        try {
            String query = "SELECT a.id, p.name as patient_name, d.name as doctor_name, " +
                         "a.appointment_date, a.appointment_time, a.status " +
                         "FROM appointments a " +
                         "JOIN patients p ON a.patient_id = p.id " +
                         "JOIN doctors d ON a.doctor_id = d.id " +
                         "WHERE a.appointment_date = ? " +
                         "ORDER BY a.appointment_time";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, date);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n=== APPOINTMENTS ON DATE: " + date + " ===");
            System.out.println("+-----+----------------------+----------------------+----------------+----------+----------+");
            System.out.println("| ID  | Patient              | Doctor               | Date           | Time     | Status   |");
            System.out.println("+-----+----------------------+----------------------+----------------+----------+----------+");

            boolean found = false;
            while (resultSet.next()) {
                found = true;
                int id = resultSet.getInt("id");
                String patientName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                String appointmentDate = resultSet.getString("appointment_date");
                String time = resultSet.getString("appointment_time");
                String status = resultSet.getString("status");

                System.out.printf("| %-3d | %-20s | %-20s | %-14s | %-8s | %-8s |\n", 
                                id, patientName, doctorName, appointmentDate, time, status);
            }
            
            if (!found) {
                System.out.println("| No appointments found on this date. |");
            }
            System.out.println("+-----+----------------------+----------------------+----------------+----------+----------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private void searchByStatus() {
        System.out.println("Select status to search:");
        System.out.println("1. Scheduled");
        System.out.println("2. Completed");
        System.out.println("3. Cancelled");
        int statusChoice = InputValidator.getIntInput(scanner, "Enter your choice: ", 1, 3);
        
        String status;
        switch (statusChoice) {
            case 1:
                status = "Scheduled";
                break;
            case 2:
                status = "Completed";
                break;
            case 3:
                status = "Cancelled";
                break;
            default:
                status = "Scheduled";
        }
        
        try {
            String query = "SELECT a.id, p.name as patient_name, d.name as doctor_name, " +
                         "a.appointment_date, a.appointment_time, a.status " +
                         "FROM appointments a " +
                         "JOIN patients p ON a.patient_id = p.id " +
                         "JOIN doctors d ON a.doctor_id = d.id " +
                         "WHERE a.status = ? " +
                         "ORDER BY a.appointment_date, a.appointment_time";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n=== " + status.toUpperCase() + " APPOINTMENTS ===");
            System.out.println("+-----+----------------------+----------------------+----------------+----------+----------+");
            System.out.println("| ID  | Patient              | Doctor               | Date           | Time     | Status   |");
            System.out.println("+-----+----------------------+----------------------+----------------+----------+----------+");

            boolean found = false;
            while (resultSet.next()) {
                found = true;
                int id = resultSet.getInt("id");
                String patientName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                String date = resultSet.getString("appointment_date");
                String time = resultSet.getString("appointment_time");
                String appointmentStatus = resultSet.getString("status");

                System.out.printf("| %-3d | %-20s | %-20s | %-14s | %-8s | %-8s |\n", 
                                id, patientName, doctorName, date, time, appointmentStatus);
            }
            
            if (!found) {
                System.out.println("| No " + status.toLowerCase() + " appointments found. |");
            }
            System.out.println("+-----+----------------------+----------------------+----------------+----------+----------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public boolean appointmentExists(int appointmentId) {
        try {
            String query = "SELECT id FROM appointments WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, appointmentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }
}