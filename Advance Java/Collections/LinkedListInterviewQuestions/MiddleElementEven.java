public class MiddleElementEven {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node head;

    // Add node at the end
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    // Find middle (both middle elements for even size)
    public void findMiddleBothCases() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Node slow = head;
        Node fast = head;
        Node prevSlow = null;

        while (fast != null && fast.next != null) {
            prevSlow = slow;       // Store previous of slow
            slow = slow.next;
            fast = fast.next.next;
        }

        // Check if even number of nodes
        if (fast == null) {
            // Even number of nodes -> print both prevSlow and slow
            System.out.println("Even number of nodes.");
            System.out.println("First Middle Element: " + prevSlow.data);
            System.out.println("Second Middle Element: " + slow.data);
        } else {
            // Odd number of nodes -> print only slow
            System.out.println("Odd number of nodes.");
            System.out.println("Middle Element: " + slow.data);
        }
    }

    public static void main(String[] args) {
        MiddleElementEven list = new MiddleElementEven();

        // Uncomment to test with even number of nodes
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.addLast(50);
        list.addLast(60);

        // Uncomment to test with odd number of nodes
        // list.addLast(70);

        list.findMiddleBothCases();
    }
}
