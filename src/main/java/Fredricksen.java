import java.util.Scanner;
public class Fredricksen {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Fredricksen");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        while (true) {
            System.out.println("");
            String s = in.next();
            if (s.toLowerCase().equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }
            System.out.println("____________________________________________________________");
            System.out.println(s);
            System.out.println("____________________________________________________________");
        }
    }
}
