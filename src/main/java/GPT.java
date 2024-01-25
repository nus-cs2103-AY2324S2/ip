import java.util.Scanner;
import java.util.*;
public class GPT {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String name = " GPT";
        String secLine = "What can I do for you?\n\n";

        System.out.println("Hello! I'm" + name);
        System.out.println(secLine);
        String s = scn.nextLine();
        ArrayList<String> sl = new ArrayList<>();
        while(!s.equals("bye")) {
            if (s.equals("list")) {
                for (int i=1; i<=sl.size(); i++) {
                    System.out.println( i + ". " + sl.get(i-1));
                }

            } else {
                System.out.println("added " + s);
                sl.add(s);

            }
            s = scn.nextLine();

        }


        System.out.println( "Bye. Hope to see you soon");
    }
}
