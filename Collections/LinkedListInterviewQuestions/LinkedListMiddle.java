class Node 
{
	int data;
	Node next;
	
	Node(int data)
	{
		this.data = data;
		this.next = null;
	}
}

public class LinkedListMiddle
{
	// Function to find the middle element of the linked list
	
	public static Node findMiddle(Node head)
	{
		if(head == null)  //Agar list khali hogi to
		{
			return null;
		}
		
		// Slow aur fast pointer ko hm head se hi start karte hai
		
		Node slow = head;
		Node fast = head;
		
		while(fast != null && fast.next != null)   // jb tk fast ya fast.next null nhi hota
		{
			slow = slow.next;        // 1 step jump karega
			fast = fast.next.next;   // 2 step jump karega
		}
		
		
		return slow;  // jb loop khtm hoga to slow middle pe hoga
		
	}
	
	public static void main(String[] args)
	{
		Node head = new Node(1);
		head.next = new Node(2);
		head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
		
		// Middle dhoondhte hain
        Node middle = findMiddle(head);

        if (middle != null) {
            System.out.println("Middle element is: " + middle.data);
        } else {
            System.out.println("List is empty.");
        }
		
		
	}
}
			