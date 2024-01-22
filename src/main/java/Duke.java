import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm MazeDeneroBot");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        while (true) {
            String command = scan.nextLine();
            if (command.equals("bye")) {
                break;
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(command);
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
