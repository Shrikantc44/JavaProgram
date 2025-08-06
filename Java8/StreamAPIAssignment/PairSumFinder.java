import java.util.*;

public class PairSumFinder {
    public static void main(String[] args) {
        List<Integer> nums = List.of(1, 2, 3, 7, 5, 8, 6, 4);
        int target = 10;

        Set<String> uniquePairs = new HashSet<>();

        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(i) + nums.get(j) == target) {
                    int a = nums.get(i);
                    int b = nums.get(j);
                    // Sort to avoid duplicate pairs like (3,7) and (7,3)
                    String pair = a < b ? a + "," + b : b + "," + a;
                    uniquePairs.add(pair);
                }
            }
        }

        System.out.println("Pairs with sum " + target + ":");
        uniquePairs.forEach(p -> {
            String[] split = p.split(",");
            System.out.println("(" + split[0] + ", " + split[1] + ")");
        });
    }
}
