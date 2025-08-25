public class LinkedList1 {
    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;
    public static int size = 0;  // ✅ size variable defined

    // addFirst
    public void addFirst(int data) {
        Node newNode = new Node(data);
        size++;

        if (head == null) {
            head = tail = newNode;
            return;
        }

        newNode.next = head;
        head = newNode;
    }

    // addLast
    public void addLast(int data) {
        Node newNode = new Node(data);
        size++;

        if (head == null) {
            head = tail = newNode;
            return;
        }

        tail.next = newNode;
        tail = newNode;
    }

    // Print the list
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // Find middle node using slow & fast pointer
    public Node findMid(Node head) {
        if (head == null) return null;

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;         // 1 step  jump karega
            fast = fast.next.next;   // 2 steps  jump karega
        }

        return slow;  // middle node
    }

    public static void main(String[] args) {
        LinkedList1 ll = new LinkedList1();

        ll.addFirst(2);
        ll.addFirst(1);   // LinkedList: 1 -> 2
        ll.addLast(3);    // LinkedList: 1 -> 2 -> 3
        ll.addLast(4);    // LinkedList: 1 -> 2 -> 3 -> 4

        System.out.print("Linked List: ");
        ll.printList();

        Node middle = ll.findMid(head);  // ✅ Corrected method call
        if (middle != null) {
            System.out.println("Middle element is: " + middle.data);
        } else {
            System.out.println("Linked list is empty.");
        }
    }
}
