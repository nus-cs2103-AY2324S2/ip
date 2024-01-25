import java.util.Scanner;
public class GPT {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String name = " GPT";
        String secLine = "What can I do for you?\n\n";

        System.out.println("Hello! I'm" + name);
        System.out.println(secLine);
        String s = scn.nextLine();
        while(!s.equals("bye")) {
            System.out.println(s);
            s = scn.nextLine();
        }
        System.out.println( "Bye. Hope to see you soon");
    }
}
