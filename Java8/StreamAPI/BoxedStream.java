import java.util.stream.*;  // Stream-related classes import kiye
import java.util.*;         // List, Optional, Collectors ke liye

// Class definition
class BoxedStream
{
    public static void main(String... s)
    {
        // ✅ IntStream banaya with elements 1 to 5, phir use Integer objects mein convert kiya (boxing)
        List<Integer> ints = IntStream.of(1, 2, 3, 4, 5)
                                      .boxed() // int -> Integer (autoboxing)
                                      .collect(Collectors.toList()); // Stream ko List mein convert kiya

        System.out.println(ints);  // Output: [1, 2, 3, 4, 5]

        // ✅ Max value nikalne ke liye same IntStream ka use kiya gaya
        Optional<Integer> max = IntStream.of(1, 2, 3, 4, 5)
                                         .boxed()  // Autobox int to Integer
                                         .max(Integer::compareTo);  // max value nikalna using comparator

        System.out.println(max);  // Output: Optional[5]
    }
}
