import java.io.*;
import java.util.*;

public class ReadyMadeDeque {
    Deque<String> deque = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        ReadyMadeDeque obj = new ReadyMadeDeque();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));    //BufferedReader br: user se input lene ke liye।
        int ch, ch1;                                                                 //ch, ch1: user ke menu choices store karne ke liye.
        String str;               

        while (true) 
		{
            System.out.println("Menu for Deque Operations");
            System.out.println("1. Add (addFirst / addLast / offerFirst / offerLast / push)");
            System.out.println("2. Remove (removeFirst / removeLast / pollFirst / pollLast / pop)");
            System.out.println("3. Display Deque");
            System.out.println("4. Get First and Last Element (getFirst, getLast, peekFirst, peekLast)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String input = br.readLine().trim();
            if (input.isEmpty()) 
			{
                System.out.println("No input entered. Please enter a number.");
                continue;
            }

            try 
			{
                ch = Integer.parseInt(input);
            } catch (NumberFormatException e)
			{
                System.out.println("Invalid input. Please enter a valid number.");
                continue;
            }

            switch (ch)
			{
                case 1:                            //Add Operations
                    while (true) {
                        obj.clrscr();
                        System.out.println("Add Operations:");         
                        System.out.println("1. addFirst");
                        System.out.println("2. addLast");
                        System.out.println("3. offerFirst");
                        System.out.println("4. offerLast");
                        System.out.println("5. push");
                        System.out.println("6. Back to Main Menu");
                        System.out.print("Enter your choice: ");

                        String input1 = br.readLine().trim();           //User se input lekar use number me convert kr rhe hai
                        if (input1.isEmpty()) continue;

                        try 
						{
                            ch1 = Integer.parseInt(input1);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input.");
                            continue;
                        }

                        if (ch1 >= 1 && ch1 <= 5) {
                            System.out.print("Enter data: ");
                            str = br.readLine();
                        }
						else if (ch1 == 6)
							break;
                        else 
						{
                            System.out.println("Invalid choice!");
                            continue;
                        }

                        switch (ch1)
						{
                            case 1:
                                obj.deque.addFirst(str);
                                break;
                            case 2:
                                obj.deque.addLast(str);
                                break;
                            case 3:
                                if (obj.deque.offerFirst(str))
                                    System.out.println("Element added at front using offerFirst()");
                                else 
                                    System.out.println("Failed to add using offerFirst()");
                                break;
                            case 4:
                                if (obj.deque.offerLast(str))
                                    System.out.println("Element added at rear using offerLast()");
                                else
                                    System.out.println("Failed to add using offerLast()");
                                break;
                            case 5:
                                obj.deque.push(str);
                                System.out.println("Element pushed at front using push()");
                                break;
                        }

                        obj.pause();
                    }
                    break;

                case 2:                           //Remove Operations
                    while (true)
						{
                        obj.clrscr();
                        if (!obj.deque.isEmpty())
							{
                            System.out.println("Remove Operations:");
                            System.out.println("1. removeFirst");
                            System.out.println("2. removeLast");
                            System.out.println("3. pollFirst");
                            System.out.println("4. pollLast");
                            System.out.println("5. pop");
                            System.out.println("6. Back to Main Menu");
                            System.out.print("Enter your choice: ");

                            String input2 = br.readLine().trim();
                            if (input2.isEmpty()) continue;

                            try
							{
                                ch1 = Integer.parseInt(input2);
                            } catch (NumberFormatException e)
							{
                                System.out.println("Invalid input.");
                                continue;
                            }

                            switch (ch1)
							{
                                case 1:
                                    System.out.println("Removed using removeFirst: " + obj.deque.removeFirst());
                                    break;
                                case 2:
                                    System.out.println("Removed using removeLast: " + obj.deque.removeLast());
                                    break;
                                case 3:
                                    System.out.println("Removed using pollFirst: " + obj.deque.pollFirst());
                                    break;
                                case 4:
                                    System.out.println("Removed using pollLast: " + obj.deque.pollLast());
                                    break;
                                case 5:
                                    System.out.println("Removed using pop: " + obj.deque.pop());
                                    break;
                                case 6:
                                    break;
                                default:
                                    System.out.println("Invalid choice!");
                            }

                            if (ch1 == 6) break;
                        }
						else
							{
                            System.out.println("Deque is empty. Cannot remove.");
                            break;
                        }

                        obj.pause();
                    }
                    break;

                case 3:                          //Display Deque
                    obj.clrscr();
                    if (obj.deque.isEmpty())
						{
                        System.out.println("Deque is empty.");
                    }
					else
						{
                        System.out.println("Contents of Deque:");
                        for (String item : obj.deque) {
                            System.out.println(item);
                        }
                    }
                    obj.pause();
                    break;

                case 4:                          //Get First and Last Elements
                    obj.clrscr();
                    if (obj.deque.isEmpty()) 
					{
                        System.out.println("Deque is empty.");
                    } else 
					{
                        System.out.println("peekFirst(): " + obj.deque.peekFirst());
                        System.out.println("peekLast(): " + obj.deque.peekLast());
                        try
						{
                            System.out.println("getFirst(): " + obj.deque.getFirst());
                            System.out.println("getLast(): " + obj.deque.getLast());
                        } catch (NoSuchElementException e) {
                            System.out.println("Deque is empty (Exception).");
                        }
                    }
                    obj.pause();
                    break;

                case 5:                       //Exit
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;

                default:                      //Invalid Choice
                    System.out.println("Invalid choice!");
            }

            System.out.println("Returning to main menu...");
            try {
                Thread.sleep(1500);
            }
			catch (InterruptedException e) {}
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
