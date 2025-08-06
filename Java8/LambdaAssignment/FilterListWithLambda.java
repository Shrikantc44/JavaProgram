//Filtering a List with Lambda

import java.util.*;
import java.util.stream.Collectors;

public class FilterListWithLambda {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "JavaScript", "Python");
        
        List<String> filteredList = list.stream()
            .filter(s -> s.startsWith("J"))
            .collect(Collectors.toList());
        
        filteredList.forEach(System.out::println);
    }
}


// Output: Java
//         JavaScript