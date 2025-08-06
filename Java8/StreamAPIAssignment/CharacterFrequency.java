// 14. Count the Occurrences of Each Character in a String

import java.util.Map;
import java.util.stream.Collectors;

public class CharacterFrequency {
    public static void main(String[] args) {
        String s1 = "sssssHiiiili";

        Map<Character, Long> collect = s1.chars()
            .mapToObj(c -> (char) c)
            .collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        System.out.println("Character Frequencies:");
        System.out.println(collect);
    }
}

/*  
s1.chars() → string ko character codes (IntStream) mein convert karta hai.

.mapToObj(c -> (char) c) → har character ko wapas Character object bana deta hai.

Collectors.groupingBy(..., counting()) → har character ke according group banata hai aur unki count (frequency) nikalta hai.

Map<Character, Long> → yeh map har character ko uski frequency ke saath store karta hai.

System.out.println(collect) → result print karta hai.

🧾 Output:

java
Copy
Edit
{s=5, H=1, i=4, l=1}
*/