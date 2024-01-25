import java.util.Scanner;
public class Duke_Level1 {
    public static void main(String[] args) {
        System.out.println("Hello! I'm SamuelBot");
        System.out.println("What can I do for you?");
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        while(! str.equals("bye")) {
            System.out.println(str);
            str = s.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
