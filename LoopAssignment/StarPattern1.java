/*

    *
   **
  ***
 ****
*****
 ****
  ***
   **
    *
	
	*/

class StarPattern1 {
    public static void main(String[] args) {
        int n = 5;

        // Upper half ke liye
        for (int i = 1; i <= n; i++) {
            // Space ke liye
            for (int s = 1; s <= n - i; s++) {
                System.out.print(" ");
            }
            // Print stars
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println(); 
        }

       
        for (int i = n - 1; i >= 1; i--) {
          
            for (int s = 1; s <= n - i; s++) {
                System.out.print(" ");
            }
          
            for (int j = 1; j <= i; j++) {
                System.out.print("*");
            }
            System.out.println(); 
        }
    }
}
