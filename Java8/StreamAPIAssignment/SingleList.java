//Q10: Convert a list of lists into a single list

import java.util.*;
import java.util.stream.*;

public class SingleList {
    public static void main(String[] args) {
        List<List<String>> nestedList = List.of(
            List.of("a", "b"),
            List.of("c", "d"),
            List.of("e", "f")
        );

        List<String> flatList = nestedList.stream()
            .flatMap(Collection::stream)
            .collect(Collectors.toList());

        System.out.println(flatList);
        // Output: [a, b, c, d, e, f]
    }
}
