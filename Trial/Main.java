class A {
    A() { // Constructor banaya gaya hai class A ke liye
        System.out.println("A");
    }
}

class B {
    B() { // Constructor banaya gaya hai class B ke liye
        System.out.println("B");
    }
}

public class Main {
    public static void main(String[] args) {
        A obj1 = new A(); // Class A ka object banaya ja raha hai, constructor chalega
        B obj2 = new B(); // Class B ka object banaya ja raha hai, constructor chalega
		System.out.println("I am the king ");
    }
}
