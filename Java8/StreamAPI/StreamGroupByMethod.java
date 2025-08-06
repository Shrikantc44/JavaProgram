// Agar stream elements ke keys duplicate ho jayein, to hum Collectors.groupingBy() ka use kar sakte hain
// Yeh Map<key, List<value>> form me data store karta hai

// Example: Employee objects ko unke names ke according group karna

import java.util.ArrayList;               // ArrayList ke liye
import java.util.Arrays;                  // Arrays.asList ke liye
import java.util.List;                    // List interface ke liye
import java.util.Map;                     // Map interface ke liye
import java.util.function.Function;       // Function interface (optional, used internally)
import java.util.stream.Collectors;       // Stream ke Collectors utility ke liye

public class StreamGroupByMethod {

    public static void main(String[] args) {

        // Employee objects ki list banayi ja rahi hai
        List<Employee> employeeList = new ArrayList<>(Arrays.asList(
            new Employee(1, "A", 100),
            new Employee(2, "A", 200),
            new Employee(3, "B", 300),
            new Employee(4, "A", 400),
            new Employee(5, "C", 500)
        ));

        // Grouping by name: same name wale employees ko ek hi list me group kar diya jayega
        Map<String, List<Employee>> groupByName = employeeList.stream()
            .collect(Collectors.groupingBy(emp -> emp.getName()));  // Group by name

        // Result print karna
        System.out.println(groupByName);
    }
}

// Employee class with 3 fields: id, name, salary
class Employee {
    private int id;
    private String name;
    private int salary;

    // Constructor
    public Employee(int id, String name, int salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // toString() method for readable output
    public String toString() {
        return "{ID=" + id + ", Name=" + name + ", Salary=" + salary + "}";
    }
}
