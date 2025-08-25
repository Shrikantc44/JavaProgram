import java.io.*;

// Node class
class Node {
    Object data;
    Node next;
}

// Iterator interface                     
interface MyIterator 
{
    boolean hasNext();     //hasNext() – check karta hai kya aur node bacha hai ya na
    Object next();         //next() – agla node return karta hai.
}

// Custom LinkedList with Iterator
class MyLinkedList implements MyIterator 
{
    Node start = null;
    Node hsnxt, nxt;

    public MyIterator myiterator()            //Iterator initialize karta hai. Starting point set karta hai for iteration.
	{
        nxt = hsnxt = this.start;
        return this;
    }

    public boolean hasNext() 
	{
        return (nxt != null);
    }

    public Object next()
	{
        Object o = nxt.data;
        nxt = nxt.next;
        return o;
    }

    public boolean isEmpty()
	{
        return start == null;       //Agar start == null hai to list empty hai.
    }

    public void addFirst(Object o) 
	{
        Node d = new Node();        // New node list ke beginning me insert karta hai.
        d.data = o;
        d.next = start;
        start = d;
    }

    public void addLast(Object o)    //End me node add karta hai. Agar list empty ho to new node hi start ban jata hai.
	{
		//// creates a new node
        Node d = new Node();
        d.data = o;
        d.next = null;

        if (start == null) {
            start = d;
        } else {
            Node var = start;
            while (var.next != null) 
			{
                var = var.next;
            }
            var.next = d;
        }
    }

    public Object getFirst()
	{
        return start != null ? start.data : null;
    }

    public Object getLast()
	{
        if (start == null) return null;                 //First and last element ke data return karte hain.
        Node var = start;
        while (var.next != null) {
            var = var.next;
        }
        return var.data;
    }

    public Object removeFirst() 
	{
        if (start != null) {
            Object o = start.data;
            start = start.next;
            return o;
        }
        return null;
    }

    public Object removeLast() 
	{
        if (start == null) return null;

        if (start.next == null)
			{
            Object o = start.data;
            start = null;
            return o;
        }

        Node var = start;
        while (var.next.next != null)
			{
            var = var.next;
        }
        Object o = var.next.data;
        var.next = null;
        return o;
    }
}

public class MyListAsign 
{
    MyLinkedList elmnt = new MyLinkedList();

    public static void main(String... args) throws IOException 
	{
        MyListAsign obj = new MyListAsign();
        BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
        int ch, ch1;
        String str;

        while (true) 
		{
            System.out.println("Menu of the operation for DQ");
            System.out.println("1. ADD\n2. Delete\n3. Display\n4. Exit");
            System.out.print("Enter your choice: ");
            ch = Integer.parseInt(sc.readLine());

            switch (ch) 
			{
                case 1:
                    while (true) 
					{
                        obj.clrscr();
                        System.out.println("Select the position for adding the next element");
                        System.out.println("1. Front-end\n2. Rear-end\n3. Back to main menu");
                        System.out.print("Enter your choice: ");
                        ch1 = Integer.parseInt(sc.readLine());
                        switch (ch1) {
                            case 1:
                                System.out.print("Enter your data to insert: ");
                                str = sc.readLine();
                                obj.elmnt.addFirst(str);
                                break;
                            case 2:
                                System.out.print("Enter your data to insert: ");
                                str = sc.readLine();
                                obj.elmnt.addLast(str);
                                break;
                            case 3:
                                break;
                            default:
                                System.out.println("You have entered the wrong choice.");
                        }
                        if (ch1 == 3)
                            break;
                    }
                    break;

                case 2:
                    while (true)
						{
                        obj.clrscr();
                        if (!obj.elmnt.isEmpty()) 
						{
                            System.out.println("Select the position to delete the element");
                            System.out.println("1. Front-end\n2. Rear-end\n3. Back");
                            System.out.print("Enter your choice: ");
                            ch1 = Integer.parseInt(sc.readLine());
                            switch (ch1) {
                                case 1:
                                    obj.elmnt.removeFirst();
                                    System.out.println("First element removed.");
                                    break;
                                case 2:
                                    obj.elmnt.removeLast();
                                    System.out.println("Last element removed.");
                                    break;
                                case 3:
                                    break;
                                default:
                                    System.out.println("You entered the wrong choice.");
                            }
                        }
						else
							{
                            System.out.println("There is no element present. First add elements.");
                            break;
                        }
                        if (ch1 == 3)
                            break;
                        obj.pause();
                    }
                    break;

                case 3:
                    if (obj.elmnt.isEmpty())
						{
                        System.out.println("Nothing to display.");
                    } 
					else 
					{
                        System.out.println("Contents of the list:");
                        MyIterator it = obj.elmnt.myiterator();
                        while (it.hasNext()) {
                            System.out.println(it.next());
                        }
                    }
                    break;

                case 4:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("You have entered the wrong choice.");
            }

            System.out.println("Wait, getting back...");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
            obj.clrscr();
        }
    }

    void clrscr()
   {
    for (int i = 0; i < 50; i++) System.out.println();
   }

    void pause() 
	{
        System.out.println("Press Enter to continue...");
        try {
            System.in.read();
        } catch (IOException e) {}
    }
}
