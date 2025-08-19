package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class BillingManager {
    private Connection connection;
    private Scanner scanner;
    private PatientManager patientManager;
    private DoctorManager doctorManager;

    public BillingManager(Connection connection, Scanner scanner, 
                         PatientManager patientManager, DoctorManager doctorManager) {
        this.connection = connection;
        this.scanner = scanner;
        this.patientManager = patientManager;
        this.doctorManager = doctorManager;
    }

    public void manageBilling() {
        while (true) {
            System.out.println("\n=== BILLING MANAGEMENT ===");
            System.out.println("1. View All Bills");
            System.out.println("2. View Bill Details");
            System.out.println("3. Create New Bill");
            System.out.println("4. Update Bill");
            System.out.println("5. Process Payment");
            System.out.println("6. Search Bills");
            System.out.println("7. Back to Main Menu");
            
            int choice = InputValidator.getIntInput(scanner, "Enter your choice: ", 1, 7);
            
            switch (choice) {
                case 1:
                    viewAllBills();
                    break;
                case 2:
                    viewBillDetails();
                    break;
                case 3:
                    createBill();
                    break;
                case 4:
                    updateBill();
                    break;
                case 5:
                    processPayment();
                    break;
                case 6:
                    searchBills();
                    break;
                case 7:
                    return;
            }
        }
    }

    public void viewAllBills() {
        System.out.println("\n=== ALL BILLS ===");
        try {
            String query = "SELECT b.id, p.name as patient_name, d.name as doctor_name, " +
                         "b.amount, b.date, b.status " +
                         "FROM bills b " +
                         "JOIN patients p ON b.patient_id = p.id " +
                         "JOIN doctors d ON b.doctor_id = d.id " +
                         "ORDER BY b.date DESC";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("+-----+----------------------+----------------------+------------+------------+------------+");
            System.out.println("| ID  | Patient              | Doctor               | Amount     | Date       | Status     |");
            System.out.println("+-----+----------------------+----------------------+------------+------------+------------+");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String patientName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                double amount = resultSet.getDouble("amount");
                String date = resultSet.getString("date");
                String status = resultSet.getString("status");

                System.out.printf("| %-3d | %-20s | %-20s | $%-9.2f | %-10s | %-10s |\n", 
                                id, patientName, doctorName, amount, date, status);
            }
            System.out.println("+-----+----------------------+----------------------+------------+------------+------------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void viewBillDetails() {
        int billId = InputValidator.getIntInput(scanner, "Enter bill ID: ");
        try {
            String query = "SELECT b.*, p.name as patient_name, d.name as doctor_name, " +
                         "a.appointment_date, a.appointment_time " +
                         "FROM bills b " +
                         "JOIN patients p ON b.patient_id = p.id " +
                         "JOIN doctors d ON b.doctor_id = d.id " +
                         "LEFT JOIN appointments a ON b.appointment_id = a.id " +
                         "WHERE b.id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, billId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                System.out.println("\n=== BILL DETAILS ===");
                System.out.println("Bill ID: " + resultSet.getInt("id"));
                System.out.println("Patient: " + resultSet.getString("patient_name"));
                System.out.println("Doctor: " + resultSet.getString("doctor_name"));
                
                if (resultSet.getInt("appointment_id") != 0) {
                    System.out.println("Appointment Date: " + resultSet.getString("appointment_date"));
                    System.out.println("Appointment Time: " + resultSet.getString("appointment_time"));
                } else {
                    System.out.println("(Not associated with an appointment)");
                }
                
                System.out.println("Amount: $" + resultSet.getDouble("amount"));
                System.out.println("Date: " + resultSet.getString("date"));
                System.out.println("Status: " + resultSet.getString("status"));
                System.out.println("Payment Method: " + 
                    (resultSet.getString("payment_method") == null ? "Not paid" : resultSet.getString("payment_method")));
            } else {
                System.out.println("Bill not found with ID: " + billId);
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void createBill() {
        System.out.println("\n=== CREATE NEW BILL ===");
        
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
        
        // Check if this is for an appointment
        Integer appointmentId = null;
        System.out.println("Is this bill for an appointment? (yes/no): ");
        String isForAppointment = scanner.next();
        
        if (isForAppointment.equalsIgnoreCase("yes")) {
            // Show patient's appointments
            viewPatientAppointments(patientId);
            appointmentId = InputValidator.getIntInput(scanner, "Enter appointment ID (or 0 for none): ", 0, Integer.MAX_VALUE);
            
            if (appointmentId == 0) {
                appointmentId = null;
            } else {
                // Verify the appointment belongs to this patient and doctor
                if (!verifyAppointment(appointmentId, patientId, doctorId)) {
                    System.out.println("Appointment doesn't match patient/doctor.");
                    return;
                }
            }
        }
        
        double amount = InputValidator.getDoubleInput(scanner, "Enter bill amount: ", 0, 100000);
        String notes = InputValidator.getStringInput(scanner, "Enter bill notes (optional): ", true);
        
        try {
            String query;
            if (appointmentId != null) {
                query = "INSERT INTO bills(patient_id, doctor_id, appointment_id, amount, date, status, payment_method) " +
                       "VALUES (?, ?, ?, ?, CURDATE(), 'Pending', NULL)";
            } else {
                query = "INSERT INTO bills(patient_id, doctor_id, amount, date, status, payment_method) " +
                       "VALUES (?, ?, ?, CURDATE(), 'Pending', NULL)";
            }
            
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            preparedStatement.setInt(2, doctorId);
            
            if (appointmentId != null) {
                preparedStatement.setInt(3, appointmentId);
                preparedStatement.setDouble(4, amount);
            } else {
                preparedStatement.setDouble(3, amount);
            }

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Bill created successfully!");
            } else {
                System.out.println("Failed to create bill.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private void viewPatientAppointments(int patientId) {
        try {
            String query = "SELECT a.id, d.name as doctor_name, a.appointment_date, a.appointment_time, a.status " +
                         "FROM appointments a " +
                         "JOIN doctors d ON a.doctor_id = d.id " +
                         "WHERE a.patient_id = ? " +
                         "ORDER BY a.appointment_date DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n=== PATIENT'S APPOINTMENTS ===");
            System.out.println("+-----+----------------------+----------------+----------+----------+");
            System.out.println("| ID  | Doctor               | Date           | Time     | Status   |");
            System.out.println("+-----+----------------------+----------------+----------+----------+");

            boolean found = false;
            while (resultSet.next()) {
                found = true;
                int id = resultSet.getInt("id");
                String doctorName = resultSet.getString("doctor_name");
                String date = resultSet.getString("appointment_date");
                String time = resultSet.getString("appointment_time");
                String status = resultSet.getString("status");

                System.out.printf("| %-3d | %-20s | %-14s | %-8s | %-8s |\n", 
                                id, doctorName, date, time, status);
            }
            
            if (!found) {
                System.out.println("| No appointments found for this patient. |");
            }
            System.out.println("+-----+----------------------+----------------+----------+----------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private boolean verifyAppointment(int appointmentId, int patientId, int doctorId) {
        try {
            String query = "SELECT id FROM appointments WHERE id = ? AND patient_id = ? AND doctor_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, appointmentId);
            preparedStatement.setInt(2, patientId);
            preparedStatement.setInt(3, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            return false;
        }
    }

    public void updateBill() {
        int billId = InputValidator.getIntInput(scanner, "Enter bill ID to update: ");
        
        try {
            // First check if bill exists and is not paid
            String checkQuery = "SELECT status FROM bills WHERE id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, billId);
            ResultSet checkResult = checkStatement.executeQuery();
            
            if (!checkResult.next()) {
                System.out.println("Bill not found with ID: " + billId);
                return;
            }
            
            String status = checkResult.getString("status");
            if (status.equalsIgnoreCase("Paid")) {
                System.out.println("Cannot update a paid bill.");
                return;
            }
            
            double newAmount = InputValidator.getDoubleInput(scanner, "Enter new amount: ", 0, 100000);
            
            String updateQuery = "UPDATE bills SET amount = ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setDouble(1, newAmount);
            updateStatement.setInt(2, billId);
            
            int affectedRows = updateStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Bill updated successfully!");
            } else {
                System.out.println("Failed to update bill.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void processPayment() {
        int billId = InputValidator.getIntInput(scanner, "Enter bill ID to process payment: ");
        
        try {
            // First check if bill exists and is not already paid
            String checkQuery = "SELECT status, amount FROM bills WHERE id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, billId);
            ResultSet checkResult = checkStatement.executeQuery();
            
            if (!checkResult.next()) {
                System.out.println("Bill not found with ID: " + billId);
                return;
            }
            
            String status = checkResult.getString("status");
            if (status.equalsIgnoreCase("Paid")) {
                System.out.println("This bill has already been paid.");
                return;
            }
            
            double amount = checkResult.getDouble("amount");
            System.out.println("Bill amount: $" + amount);
            
            System.out.println("Select payment method:");
            System.out.println("1. Cash");
            System.out.println("2. Credit Card");
            System.out.println("3. Debit Card");
            System.out.println("4. Insurance");
            System.out.println("5. Bank Transfer");
            int methodChoice = InputValidator.getIntInput(scanner, "Enter your choice: ", 1, 5);
            
            String paymentMethod;
            switch (methodChoice) {
                case 1:
                    paymentMethod = "Cash";
                    break;
                case 2:
                    paymentMethod = "Credit Card";
                    break;
                case 3:
                    paymentMethod = "Debit Card";
                    break;
                case 4:
                    paymentMethod = "Insurance";
                    break;
                case 5:
                    paymentMethod = "Bank Transfer";
                    break;
                default:
                    paymentMethod = "Cash";
            }
            
            String updateQuery = "UPDATE bills SET status = 'Paid', payment_method = ? WHERE id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, paymentMethod);
            updateStatement.setInt(2, billId);
            
            int affectedRows = updateStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Payment processed successfully!");
                
                // If this bill is for an appointment, mark appointment as completed
                markAppointmentAsCompletedIfNeeded(billId);
            } else {
                System.out.println("Failed to process payment.");
            }
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private void markAppointmentAsCompletedIfNeeded(int billId) {
        try {
            // Check if this bill is associated with an appointment
            String checkQuery = "SELECT appointment_id FROM bills WHERE id = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkQuery);
            checkStatement.setInt(1, billId);
            ResultSet checkResult = checkStatement.executeQuery();
            
            if (checkResult.next()) {
                int appointmentId = checkResult.getInt("appointment_id");
                if (appointmentId != 0) {
                    // Update appointment status to completed
                    String updateQuery = "UPDATE appointments SET status = 'Completed' WHERE id = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setInt(1, appointmentId);
                    updateStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.err.println("Error updating appointment status: " + e.getMessage());
        }
    }

    public void searchBills() {
        System.out.println("\n=== SEARCH BILLS ===");
        System.out.println("1. Search by Patient");
        System.out.println("2. Search by Doctor");
        System.out.println("3. Search by Status");
        System.out.println("4. Search by Date Range");
        System.out.println("5. Back to Billing Menu");
        
        int choice = InputValidator.getIntInput(scanner, "Enter your choice: ", 1, 5);
        
        switch (choice) {
            case 1:
                searchByPatient();
                break;
            case 2:
                searchByDoctor();
                break;
            case 3:
                searchByStatus();
                break;
            case 4:
                searchByDateRange();
                break;
            case 5:
                return;
        }
    }

    private void searchByPatient() {
        patientManager.viewAllPatients();
        int patientId = InputValidator.getIntInput(scanner, "Enter patient ID to search bills: ");
        
        try {
            String query = "SELECT b.id, p.name as patient_name, d.name as doctor_name, " +
                         "b.amount, b.date, b.status " +
                         "FROM bills b " +
                         "JOIN patients p ON b.patient_id = p.id " +
                         "JOIN doctors d ON b.doctor_id = d.id " +
                         "WHERE b.patient_id = ? " +
                         "ORDER BY b.date DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n=== BILLS FOR PATIENT: " + patientManager.getPatientName(patientId) + " ===");
            System.out.println("+-----+----------------------+----------------------+------------+------------+------------+");
            System.out.println("| ID  | Patient              | Doctor               | Amount     | Date       | Status     |");
            System.out.println("+-----+----------------------+----------------------+------------+------------+------------+");

            boolean found = false;
            while (resultSet.next()) {
                found = true;
                int id = resultSet.getInt("id");
                String patientName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                double amount = resultSet.getDouble("amount");
                String date = resultSet.getString("date");
                String status = resultSet.getString("status");

                System.out.printf("| %-3d | %-20s | %-20s | $%-9.2f | %-10s | %-10s |\n", 
                                id, patientName, doctorName, amount, date, status);
            }
            
            if (!found) {
                System.out.println("| No bills found for this patient. |");
            }
            System.out.println("+-----+----------------------+----------------------+------------+------------+------------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private void searchByDoctor() {
        doctorManager.viewAllDoctors();
        int doctorId = InputValidator.getIntInput(scanner, "Enter doctor ID to search bills: ");
        
        try {
            String query = "SELECT b.id, p.name as patient_name, d.name as doctor_name, " +
                         "b.amount, b.date, b.status " +
                         "FROM bills b " +
                         "JOIN patients p ON b.patient_id = p.id " +
                         "JOIN doctors d ON b.doctor_id = d.id " +
                         "WHERE b.doctor_id = ? " +
                         "ORDER BY b.date DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n=== BILLS FOR DOCTOR: " + doctorManager.getDoctorName(doctorId) + " ===");
            System.out.println("+-----+----------------------+----------------------+------------+------------+------------+");
            System.out.println("| ID  | Patient              | Doctor               | Amount     | Date       | Status     |");
            System.out.println("+-----+----------------------+----------------------+------------+------------+------------+");

            boolean found = false;
            while (resultSet.next()) {
                found = true;
                int id = resultSet.getInt("id");
                String patientName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                double amount = resultSet.getDouble("amount");
                String date = resultSet.getString("date");
                String status = resultSet.getString("status");

                System.out.printf("| %-3d | %-20s | %-20s | $%-9.2f | %-10s | %-10s |\n", 
                                id, patientName, doctorName, amount, date, status);
            }
            
            if (!found) {
                System.out.println("| No bills found for this doctor. |");
            }
            System.out.println("+-----+----------------------+----------------------+------------+------------+------------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private void searchByStatus() {
        System.out.println("Select status to search:");
        System.out.println("1. Pending");
        System.out.println("2. Paid");
        System.out.println("3. Cancelled");
        int statusChoice = InputValidator.getIntInput(scanner, "Enter your choice: ", 1, 3);
        
        String status;
        switch (statusChoice) {
            case 1:
                status = "Pending";
                break;
            case 2:
                status = "Paid";
                break;
            case 3:
                status = "Cancelled";
                break;
            default:
                status = "Pending";
        }
        
        try {
            String query = "SELECT b.id, p.name as patient_name, d.name as doctor_name, " +
                         "b.amount, b.date, b.status " +
                         "FROM bills b " +
                         "JOIN patients p ON b.patient_id = p.id " +
                         "JOIN doctors d ON b.doctor_id = d.id " +
                         "WHERE b.status = ? " +
                         "ORDER BY b.date DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, status);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n=== " + status.toUpperCase() + " BILLS ===");
            System.out.println("+-----+----------------------+----------------------+------------+------------+------------+");
            System.out.println("| ID  | Patient              | Doctor               | Amount     | Date       | Status     |");
            System.out.println("+-----+----------------------+----------------------+------------+------------+------------+");

            boolean found = false;
            while (resultSet.next()) {
                found = true;
                int id = resultSet.getInt("id");
                String patientName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                double amount = resultSet.getDouble("amount");
                String date = resultSet.getString("date");
                String billStatus = resultSet.getString("status");

                System.out.printf("| %-3d | %-20s | %-20s | $%-9.2f | %-10s | %-10s |\n", 
                                id, patientName, doctorName, amount, date, billStatus);
            }
            
            if (!found) {
                System.out.println("| No " + status.toLowerCase() + " bills found. |");
            }
            System.out.println("+-----+----------------------+----------------------+------------+------------+------------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private void searchByDateRange() {
        String startDate = InputValidator.getDateInput(scanner, "Enter start date (YYYY-MM-DD): ");
        String endDate = InputValidator.getDateInput(scanner, "Enter end date (YYYY-MM-DD): ");
        
        try {
            String query = "SELECT b.id, p.name as patient_name, d.name as doctor_name, " +
                         "b.amount, b.date, b.status " +
                         "FROM bills b " +
                         "JOIN patients p ON b.patient_id = p.id " +
                         "JOIN doctors d ON b.doctor_id = d.id " +
                         "WHERE b.date BETWEEN ? AND ? " +
                         "ORDER BY b.date DESC";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, startDate);
            preparedStatement.setString(2, endDate);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("\n=== BILLS BETWEEN " + startDate + " AND " + endDate + " ===");
            System.out.println("+-----+----------------------+----------------------+------------+------------+------------+");
            System.out.println("| ID  | Patient              | Doctor               | Amount     | Date       | Status     |");
            System.out.println("+-----+----------------------+----------------------+------------+------------+------------+");

            boolean found = false;
            while (resultSet.next()) {
                found = true;
                int id = resultSet.getInt("id");
                String patientName = resultSet.getString("patient_name");
                String doctorName = resultSet.getString("doctor_name");
                double amount = resultSet.getDouble("amount");
                String date = resultSet.getString("date");
                String status = resultSet.getString("status");

                System.out.printf("| %-3d | %-20s | %-20s | $%-9.2f | %-10s | %-10s |\n", 
                                id, patientName, doctorName, amount, date, status);
            }
            
            if (!found) {
                System.out.println("| No bills found in this date range. |");
            }
            System.out.println("+-----+----------------------+----------------------+------------+------------+------------+");
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    public void generateFinancialReport() {
        System.out.println("\n=== FINANCIAL REPORT ===");
        
        try {
            // Total bills
            String totalQuery = "SELECT COUNT(*) as count, SUM(amount) as total FROM bills";
            Statement totalStatement = connection.createStatement();
            ResultSet totalResult = totalStatement.executeQuery(totalQuery);
            
            if (totalResult.next()) {
                int count = totalResult.getInt("count");
                double total = totalResult.getDouble("total");
                System.out.println("Total Bills: " + count);
                System.out.println("Total Amount: $" + total);
            }
            
            // Paid bills
            String paidQuery = "SELECT COUNT(*) as count, SUM(amount) as total FROM bills WHERE status = 'Paid'";
            Statement paidStatement = connection.createStatement();
            ResultSet paidResult = paidStatement.executeQuery(paidQuery);
            
            if (paidResult.next()) {
                int count = paidResult.getInt("count");
                double total = paidResult.getDouble("total");
                System.out.println("\nPaid Bills: " + count);
                System.out.println("Paid Amount: $" + total);
            }
            
            // Pending bills
            String pendingQuery = "SELECT COUNT(*) as count, SUM(amount) as total FROM bills WHERE status = 'Pending'";
            Statement pendingStatement = connection.createStatement();
            ResultSet pendingResult = pendingStatement.executeQuery(pendingQuery);
            
            if (pendingResult.next()) {
                int count = pendingResult.getInt("count");
                double total = pendingResult.getDouble("total");
                System.out.println("\nPending Bills: " + count);
                System.out.println("Pending Amount: $" + total);
            }
            
            // Payment methods
            System.out.println("\n=== PAYMENT METHODS ===");
            String methodQuery = "SELECT payment_method, COUNT(*) as count, SUM(amount) as total " +
                               "FROM bills WHERE status = 'Paid' GROUP BY payment_method";
            Statement methodStatement = connection.createStatement();
            ResultSet methodResult = methodStatement.executeQuery(methodQuery);
            
            System.out.println("+----------------+------------+------------+");
            System.out.println("| Method         | Count      | Total      |");
            System.out.println("+----------------+------------+------------+");
            
            while (methodResult.next()) {
                String method = methodResult.getString("payment_method");
                int count = methodResult.getInt("count");
                double total = methodResult.getDouble("total");
                
                System.out.printf("| %-14s | %-10d | $%-9.2f |\n", method, count, total);
            }
            System.out.println("+----------------+------------+------------+");
            
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}