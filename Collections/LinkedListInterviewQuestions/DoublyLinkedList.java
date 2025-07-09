public class DoublyLinkedList {
    // Node class
    class Node {
        int data;
        Node prev;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    // Head of the list
    Node head;

    // Method to add node at the end
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
        newNode.prev = temp;
    }

    // Method to remove a node by value
    public void remove(int key) {
        Node temp = head;

        // Empty list
        if (temp == null) return;

        // Remove head node
        if (temp.data == key) {
            head = temp.next;
            if (head != null) {
                head.prev = null;
            }
            return;
        }

        // Traverse the list
        while (temp != null && temp.data != key) {
            temp = temp.next;
        }

        // Key not found
        if (temp == null) return;

        // Remove middle or tail node
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }
        if (temp.prev != null) {
            temp.prev.next = temp.next;
        }
    }

    // Method to display the list
    public void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " <-> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // Main method to test
    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();

        // Add some nodes
        dll.addLast(10);
        dll.addLast(20);
        dll.addLast(30);
        dll.addLast(40);

        System.out.println("Original List:");
        dll.display();  // Output: 10 <-> 20 <-> 30 <-> 40 <-> null

        // Remove a node
        dll.remove(30);

        System.out.println("After removing 30:");
        dll.display();  // Output: 10 <-> 20 <-> 40 <-> null
    }
}
