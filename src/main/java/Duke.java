import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm GHBot");
        System.out.println("What can I do for you?");

        while (true) {
            Scanner sc = new Scanner(System.in);
            String str = sc.nextLine();
            if (str.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            System.out.println(str);
        }
    }
}
