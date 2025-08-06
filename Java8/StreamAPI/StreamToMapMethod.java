// If the stream elements have a unique map key field, we can use Collectors.toMap()
// to collect elements into a Map<key, value> format easily.

// For example: converting a List of Employee objects to a Map where employee IDs are unique keys.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamToMapMethod {
    public static void main(String[] args) {

        // Creating a list of Employee objects
        List<Employee> employeeList = new ArrayList<>(Arrays.asList(
            new Employee(1, "A", "100"),
            new Employee(2, "A", "200"),
            new Employee(3, "B", "300"),
            new Employee(4, "B", "400"),
            new Employee(5, "C", "500"),
            new Employee(6, "C", "600")
        ));

        // Convert List to Map using employee ID as the key
        Map<Long, Employee> employeeMap = employeeList.stream()
            .collect(Collectors.toMap(Employee::getId, Function.identity()));

        // Printing the map entries
        employeeMap.forEach((k, v) -> System.out.println("Key = " + k + ", Value = " + v));
    }
}

// Employee class with id, name, and lastname
class Employee {
    long id;
    String name;
    String lastname;

    // Constructor
    Employee(long id, String name, String lastname) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }

    // Getters
    public long getId() {
        return id;
    }

    public String getFirstName() {
        return name;
    }

    public String getLastName() {
        return lastname;
    }

    // toString method for readable output
    public String toString() {
        return "Id=" + id + ", Name=" + name + ", Lastname=" + lastname;
    }
}
