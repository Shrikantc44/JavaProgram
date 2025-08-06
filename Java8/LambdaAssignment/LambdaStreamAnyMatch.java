import java.util.Arrays;
import java.util.List;

public class LambdaStreamAnyMatch {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Java", "Spring", "Lambda");

        // Stream mein check karo kya koi element "Java" ke barabar hai
        boolean containsJava = list.stream()
                                  .anyMatch(s -> s.equals("Java"));

        System.out.println("Contains 'Java': " + containsJava);  // Output: true
    }
}

/*
Explanation:

list.stream() se list ka stream banta hai.

.anyMatch(s -> s.equals("Java")) lambda expression check karta hai agar koi element "Java" ke barabar hai to true return karega.

containsJava boolean variable mein ye result store hota hai.

Finally, print karte hain ki "Java" list mein hai ya nahi.
*/