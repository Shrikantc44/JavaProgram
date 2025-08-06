// Example of using thenComparing() to create Comparator which is capable of sorting by multiple fields.

import java.util.ArrayList;          // ArrayList ke liye
import java.util.Arrays;             // Arrays utility ke liye
import java.util.List;               // List interface ke liye
import java.util.*;                  // General utilities
import java.util.function.Function;  // Function interface ke liye (optional yahan)
import java.util.stream.Collectors;  // Stream collect operations ke liye

// Main class
public class StreamSortOnMultipleFields {

    public static void main(String[] args) {

        // getUnsortedEmployeeList() se unsorted employee data mil raha hai
        ArrayList<Employee> employees = getUnsortedEmployeeList();

        // MyNameComp class ka comparator object banaya gaya hai jo sorting logic dega
        Comparator<Employee> m2 = new MyNameComp();

        // Stream ke through sorting ki ja rahi hai and sorted list ko collect karke store kiya ja raha hai
        List<Employee> sortedEmployees = employees.stream()
                                                  .sorted(m2)  // Comparator ke according sort
                                                  .collect(Collectors.toList()); // List me collect

        // Sorted list print kar rahe hain
        System.out.println(sortedEmployees);
    }

    // Employee list return karne wali method
    private static ArrayList<Employee> getUnsortedEmployeeList() {
        ArrayList<Employee> list = new ArrayList<>();

        // List me alag alag Employee objects add kar rahe hain
        list.add(new Employee(21, "Lokesh", "Gupta"));
        list.add(new Employee(11, "Alex", "Gussin"));
        list.add(new Employee(41, "Brian", "Kapoor"));
        list.add(new Employee(51, "Neon", "Piper"));
        list.add(new Employee(31, "David", "Beckham"));
        list.add(new Employee(71, "Alex", "Beckham"));
        list.add(new Employee(61, "Brian", "Suxena"));

        return list; // unsorted list return ho gayi
    }
}

// Employee class with fields and methods
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

    // Getter for id
    public long getId() {
        return id;
    }

    // Getter for first name
    public String getFirstName() {
        return name;
    }

    // Getter for last name
    public String getLastName() {
        return lastname;
    }

    // toString method for readable output
    public String toString() {
        return "Id=" + id + " #Name=" + name + " #Lastname=" + lastname;
    }
}

// Custom Comparator class jo pehle name ke basis pe sort karega,
// agar name same hai to last name ke basis pe
class MyNameComp implements Comparator<Employee> {

    public int compare(Employee e1, Employee e2) {
        // Pehle first name compare karenge
        int nameCompare = e1.getFirstName().compareTo(e2.getFirstName());

        // Agar first name same hai to last name compare karenge
        if (nameCompare == 0) {
            return e1.getLastName().compareTo(e2.getLastName());
        } else {
            return nameCompare;
        }
    }
}
