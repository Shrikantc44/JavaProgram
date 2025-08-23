/* public static String copyValueOf(char[] data)
   or
   public static String copyValueOf(char[] data,int offset,int count)
*/

public class CopyValueOf {
    public static void main(String args[]) {
        // Correct char array (single space ' ')
        char[] Str1 = {'h','e','l','l','o',' ','w','o','r','l','d'};
        String Str2 = "";
        
        // Copy full array
        Str2 = String.copyValueOf(Str1);
        System.out.println("Returned String: " + Str2);
        
        // Copy part of array (from index 2, count 6 chars)
        Str2 = String.copyValueOf(Str1, 2, 6);
        System.out.println("Returned String: " + Str2);
    }
}
