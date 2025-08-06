import java.util.stream.*;
import java.util.*;

class OptionalEx1 {
    public static void main(String... s) {
        Optional<Integer> canBeEmpty1 = Optional.of(5);
        System.out.println(canBeEmpty1.isPresent());  // returns true
        System.out.println(canBeEmpty1.get());

        Optional<Integer> canBeEmpty2 = Optional.empty();
        System.out.println(canBeEmpty2.isPresent());
    }
}
