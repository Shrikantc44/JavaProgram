package HospitalManagementSystem;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/hospitalm";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "shrik";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    public static void initializeDatabase() {
        try (Connection connection = getConnection();
             Statement stmt = connection.createStatement()) {
            
            // Create patients table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS patients (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "age INT NOT NULL, " +
                    "gender VARCHAR(10) NOT NULL, " +
                    "contact VARCHAR(15), " +
                    "address TEXT, " +
                    "medical_history TEXT, " +
                    "registration_date DATE NOT NULL)");
            
            // Create doctors table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS doctors (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "specialization VARCHAR(100) NOT NULL, " +
                    "contact VARCHAR(15), " +
                    "email VARCHAR(100), " +
                    "consultation_fee DECIMAL(10,2), " +
                    "available_days VARCHAR(50), " +
                    "available_time VARCHAR(50))");
            
            // Create appointments table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS appointments (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "patient_id INT NOT NULL, " +
                    "doctor_id INT NOT NULL, " +
                    "appointment_date DATE NOT NULL, " +
                    "appointment_time TIME NOT NULL, " +
                    "status VARCHAR(20) DEFAULT 'Scheduled', " +
                    "notes TEXT, " +
                    "FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE)");
            
            // Create bills table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS bills (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "patient_id INT NOT NULL, " +
                    "doctor_id INT NOT NULL, " +
                    "appointment_id INT, " +
                    "amount DECIMAL(10,2) NOT NULL, " +
                    "date DATE NOT NULL, " +
                    "status VARCHAR(20) DEFAULT 'Pending', " +
                    "payment_method VARCHAR(50), " +
                    "FOREIGN KEY (patient_id) REFERENCES patients(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY (appointment_id) REFERENCES appointments(id) ON DELETE SET NULL)");
            
        } catch (SQLException e) {
            System.err.println("Database initialization failed: " + e.getMessage());
        }
    }
}