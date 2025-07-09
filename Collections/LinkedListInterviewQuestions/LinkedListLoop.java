// How to check if linked list contains loop in java? 

public class LinkedListLoop {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;

    // Method to detect if a loop exists
    public static boolean isCycle() {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;           // 1 step
            fast = fast.next.next;      // 2 steps

            if (slow == fast) {
                return true;            // loop detected
            }
        }

        return false;                   // no loop
    }
	
	public static void main(String[] args)
	{
		head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
		head.next.next.next = head;
		
		System.out.println(isCycle());
	}
}
		

