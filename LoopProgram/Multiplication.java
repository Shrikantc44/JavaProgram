import java.util.Scanner;

public class Multiplication {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number:");
        int num = sc.nextInt();

        for (int i = 1; i <= 10; i++) {
            int res = num * i;
            System.out.println(num + " x " + i + " = " + res);
        }

        sc.close();
    }
}
