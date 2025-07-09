class Test {
    void show() {
        System.out.println("1");
    }
}

class Xyz extends Test {
    void show() {
        System.out.println("2");
    }
}

public class Main {
    public static void main(String[] args) {
        Test t = new Test();
        t.show();  // Output: 1

        Xyz x = new Xyz();
        x.show();  // Output: 2
    }
}
