// Date class ko import kar rahe hain jo java.util package me hai
import java.util.Date;

public class MyAfterDateTest {

    public static void main(String a[]) {

        // current date ka object banate hain
        Date current = new Date();

        // ek din baad ki date ka time in milliseconds me nikalte hain
        // 1000*60*60*24 = 1 din ke milliseconds
        long nextDay = System.currentTimeMillis() + 1000 * 60 * 60 * 24;

        // nextDay time ke basis par ek Date object banate hain
        Date next = new Date(nextDay);

        // ek din pehle ki date ka time in milliseconds me nikalte hain
        long prevDay = System.currentTimeMillis() - 1000 * 60 * 60 * 24;

        // prevDay time ke basis par ek Date object banate hain
        Date prev = new Date(prevDay);

        // previous day date print karte hain
        System.out.println(prev);

        // next day date print karte hain
        System.out.println(next);

        // check karte hain kya next wali date current se baad ki hai?
        if (next.after(current)) {
            System.out.println("The date is future day");
        } else {
            System.out.println("The date is older than current day");
        }

        // check karte hain kya previous wali date current se pehle ki hai?
        if (prev.before(current)) {
            System.out.println("The date is older than current day");
        }
    }
}
