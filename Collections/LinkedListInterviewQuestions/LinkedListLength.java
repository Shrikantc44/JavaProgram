// How to find the length of a singly linked list ?

public class LinkedListLength
{
	// Node Class
	static class Node {
		int data;
		Node next;
		
		Node(int data)
		{
			this.data = data;
			this.next = null;
		}
	}
	
	//Head of the list
	public static Node head;
	
	//Method to add node at end 
	
	public void addLast(int data)
	{
		Node newNode = new Node(data);
		if(head == null) {
			head = newNode;
			return;
		}
		
		Node temp = head;
		while(temp.next!=null)
		{
			temp = temp.next;
		}
		temp.next  = newNode;
	}
		
	//method to calculate length of list 
	
	public int findLength()
	{
		int count=0;
		Node temp = head;
		while(temp != null){
		
			count++;
			temp =temp.next;
		}
		return count;
	}
	
	//Main method 
	
	public static void main(String[] args)
	{
		LinkedListLength list = new LinkedListLength();
		
		// Adding elements to list
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
		
		 // Printing length
        System.out.println("Length of the Linked List: " + list.findLength());
    }
}

			
