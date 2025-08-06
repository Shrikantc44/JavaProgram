/* flatMap() is used to flatten a Stream of collections to a Stream of 
objects. During the flattening operation, the objects from all the collections in the 
original Stream are combined into a single collection

Stream<Collection<Item>> --> flatMap() --> Stream<Item>*/

import java.util.stream.*;
import java.util.*;

class FlatMapMethod
{
    public static void main(String... s)
    {
        List<List<Integer>> listOfLists = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(4, 5),
            Arrays.asList(6, 7, 8)
        );

        List<Integer> flattenedList = listOfLists.stream()
            .flatMap(list -> list.stream())  // Flattening step
            .collect(Collectors.toList());
        System.out.println(flattenedList);
    }
}
