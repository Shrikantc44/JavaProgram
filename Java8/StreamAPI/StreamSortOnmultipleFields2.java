//Example of using thenComparing() to create Comparator which is capable of sorting by multiple fields.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamSortOnmultipleFields2
{
    public static void main(String[] args)
    {
        ArrayList<Employee> emp = getUnsortedEmployeeList();

        Comparator<Employee> compareById = Comparator.comparing(Employee::getId);
        System.out.println(compareById.getClass().getName());

        List<Employee> sortedEmployees = emp.stream().sorted(compareById).collect(Collectors.toList());
        System.out.println(sortedEmployees);
    }

    private static ArrayList<Employee> getUnsortedEmployeeList()
    {
        ArrayList<Employee> list = new ArrayList<>();
        list.add(new Employee(21, "Lokesh", "Gupta"));
        list.add(new Employee(11, "Alex", "Gussin"));
        list.add(new Employee(41, "Brian", "Kapoor"));
        list.add(new Employee(51, "Neon", "Piper"));
        list.add(new Employee(31, "David", "Beckham"));
        list.add(new Employee(71, "Alex", "Beckham"));
        list.add(new Employee(61, "Brian", "Suxena"));
        return list;
    }
}

class Employee
{
    long id;
    String name;
    String lastname;

    Employee(long id, String name, String lastname)
    {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
    }

    public long getId()
    {
        return id;
    }

    public String getFirstName()
    {
        return name;
    }

    public String getLastName()
    {
        return lastname;
    }

    public String toString()
    {
        return "Id=" + id + " " + "name=" + name + " " + "Lastname=" + lastname;
    }
}
