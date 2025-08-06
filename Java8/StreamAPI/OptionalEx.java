import java.util.stream.*;  // (Not required here, but included)
import java.util.*;         // Optional class ke liye import

// Class definition
class OptionalEx
{
    public static void main(String... s)
    {
        // ✅ Optional object banaya gaya jisme 5 value hai
        Optional<Integer> canBeEmpty1 = Optional.of(5);

        // isPresent() check karega ki koi value present hai ya nahi
        System.out.println(canBeEmpty1.isPresent());  // Output: true

        // get() method se actual value access kar sakte hain
        System.out.println(canBeEmpty1.get());        // Output: 5

        // ❌ Optional object jisme koi value nahi hai (empty)
        Optional<Integer> canBeEmpty2 = Optional.empty();

        // isPresent() yahan false return karega kyunki value nahi hai
        System.out.println(canBeEmpty2.isPresent());  // Output: false
    }
}
