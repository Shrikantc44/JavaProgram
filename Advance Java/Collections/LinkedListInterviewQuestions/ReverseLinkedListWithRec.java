// How to reverse a Linked list in java  using recursion? 

public class ReverseLinkedListWithRec {
    // Node class
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node head;
    public static Node tail;

    // Recursive reverse function
    public Node reverseRecursive(Node current) {
        // Base case: if list is empty or only one node
        if (current == null || current.next == null) {
            return current;
        }

        // Recursive call to reverse the rest of the list
        Node newHead = reverseRecursive(current.next);

        // Reverse the current link
        current.next.next = current;  // Next node now points back to current
        current.next = null;          // Current node now becomes the last one

        return newHead;               // Return new head after full reversal
    }

    // Wrapper function to call recursive function
    public void reverse() {
        tail = head;           // Old head becomes new tail
        head = reverseRecursive(head);  // Update head to new head
    }

    // Function to add node at the end
    public void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
            return;
        }
        tail.next = newNode;
        tail = newNode;
    }

    // Print the linked list
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // Main function
    public static void main(String[] args) {
        ReverseLinkedList ll = new ReverseLinkedList();

        ll.addLast(10);
        ll.addLast(20);
        ll.addLast(30);
        ll.addLast(40);

        System.out.println("Original Linked List:");
        ll.printList();

        ll.reverse();  // Reverse using recursion

        System.out.println("Reversed Linked List (Recursive):");
        ll.printList();
    }
}
