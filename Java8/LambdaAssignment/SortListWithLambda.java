//Sorting a List Using Lambda 


import java.util.*;

public class SortListWithLambda {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "Lambda", "Kafka");

        // Lambda expression to sort the list
        list.sort((s1, s2) -> s1.compareTo(s2));

        // Print sorted list
        list.forEach(System.out::println);
    }
}

//Output

/*Java
  Kafka
  Lambda */
