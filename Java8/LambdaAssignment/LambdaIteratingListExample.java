import java.util.Arrays;
import java.util.List;

public class LambdaIteratingListExample {
    public static void main(String[] args) {
        

        // Iterating List using Lambda
        List<String> list = Arrays.asList("Java", "Spring", "Lambda");
        list.forEach(item -> System.out.println(item));
    }
}
