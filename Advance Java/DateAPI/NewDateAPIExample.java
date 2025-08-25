import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NewDateAPIExample {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();               // Current date
        LocalTime time = LocalTime.now();               // Current time
        LocalDateTime dateTime = LocalDateTime.now();   // Current date + time

        System.out.println("Date: " + date);            // e.g., 2025-07-19
        System.out.println("Time: " + time);            // e.g., 16:41:22
        System.out.println("DateTime: " + dateTime);    // e.g., 2025-07-19T16:41:22

        // Formatting the date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatted = dateTime.format(formatter);
        System.out.println("Formatted: " + formatted);  // e.g., 19-07-2025 16:41:22
    }
}
