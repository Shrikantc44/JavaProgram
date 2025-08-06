//Using a Custom Functional Interface


import java.lang.FunctionalInterface;

@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}

public class CustomFunctionalInterfaceExample {
    public static void main(String[] args) {
        // Lambda expression for addition
        Calculator add = (a, b) -> a + b;
        
        // Lambda expression for multiplication
        Calculator multiply = (a, b) -> a * b;
        
        System.out.println("Addition: " + add.calculate(5, 3));
        System.out.println("Multiplication: " + multiply.calculate(5, 3));
    }
}

/* 

Output:
Addition: 8
Multiplication: 15
*/