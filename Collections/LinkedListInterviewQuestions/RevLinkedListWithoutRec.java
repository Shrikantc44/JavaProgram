// How reverse a singly linked list without recursion in java


public class RevLinkedListWithoutRec {
    
    // Node class definition
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static Node head;
    public static Node tail;

    // Function to reverse the linked list using iterative method
    public void reverse() {
        Node prev = null;        // Initially previous node is null
        Node curr = head;        // Current node starts from head
        Node next;

        // Iterate till the end of the list
        while (curr != null) {
            next = curr.next;     // Step 1: Save next node
            curr.next = prev;     // Step 2: Reverse current node's pointer
            prev = curr;          // Step 3: Move prev to current node
            curr = next;          // Step 4: Move current to next node
        }

        // After the loop, prev becomes the new head
        tail = head;              // Update tail (old head becomes new tail)
        head = prev;              // Update head to point to the new head
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

    // Function to print the list
    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // Main method
    public static void main(String[] args) {
        ReverseLinkedList ll = new ReverseLinkedList();

        ll.addLast(10);
        ll.addLast(20);
        ll.addLast(30);
        ll.addLast(40);

        System.out.println("Original Linked List:");
        ll.printList();

        ll.reverse();  // Reverse using iterative approach

        System.out.println("Reversed Linked List:");
        ll.printList();
    }
}
