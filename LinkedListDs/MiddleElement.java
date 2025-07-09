// How to find middle element of a singly linked list in one pass ?



public class MiddleElement {
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
    public static int size = 0;        // size ko find karne ke liye  By defualt value 0 hoti h  

    // addFirst
    public void addFirst(int data) 
	{
		// Step 1: Create new node
        Node newNode = new Node(data);
        size++;

        if (head == null) {
            head = tail = newNode;
            return;
        }
        // Step 2: newNode.next = head
        newNode.next = head;
		
		// Step 3: head = newNode
        head = newNode;
    }

    // addLast
    public void addLast(int data)
	{
		// Step 1: Create new node
        Node newNode = new Node(data);
        size++;

        if (head == null) {
            head = tail = newNode;
            return;
        }
          
		  // Step 2: tail.next = newNode
        tail.next = newNode;
		
		 // Step 3: tail = newNode
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

        Node middle = ll.findMid(head);  
        if (middle != null) 
		{
            System.out.println("Middle element is: " + middle.data);
        }
		else
			{
            System.out.println("Linked list is empty.");
        }
    }
}
