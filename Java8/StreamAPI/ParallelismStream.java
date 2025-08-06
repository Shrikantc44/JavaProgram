// Fork/Join framework Java SE 7 mein add hua tha, jo parallel operations ko efficient banata hai.
// Hum parallelism enable karne ke liye simple stream() ke jagah parallelStream() ka use karte hain.

import java.util.stream.*;  // Stream operations ke liye import
import java.util.*;         // List aur ArrayList ke liye import

public class ParallelismStream  // Class declaration
{
    public static void main(String[] args)
    {
        // Ek Integer type ki list banayi gayi hai
        List<Integer> list = new ArrayList<>();

        // 1 se 9 tak numbers list me add kiye gaye
        for(int i = 1; i < 10; i++) {
            list.add(i);
        }

        // list ke upar parallel stream banayi gayi hai (multi-threading use karega)
        Stream<Integer> stream = list.parallelStream();

        // Filter lagaya gaya hai jisme sirf even numbers liye ja rahe hain
        // Result ko Integer array me convert kar diya gaya
        Integer[] evenNumbersArr = stream
                .filter(i -> i % 2 == 0)                          // Sirf even numbers select honge
                .toArray(Integer[]::new);                         // Unhe array me convert kiya gaya

        // Array ke elements print kiye ja rahe hain
        for(int z : evenNumbersArr)
            System.out.print(z + " ");  // Output: even numbers (order guarantee nahi hogi)
    }
}
