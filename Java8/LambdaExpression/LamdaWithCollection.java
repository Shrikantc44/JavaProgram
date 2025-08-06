import java.util.*;
class LamdaWithCollection
{
    public static void main(String... sl)
    {
        String[] str = {"lalu", "rabari", "meesa", "nitish"};

        List<String> family = Arrays.asList(str);

        // Old looping
        for (String s : family) {
            System.out.print(s + " ");
        }

        System.out.println("\n");

        // Using lambda expression and functional operations
        family.forEach((s) -> System.out.print(s + " "));
        System.out.println("\n");

        // Using double colon operator in Java 8
        family.forEach(System.out::println);
    }
}
