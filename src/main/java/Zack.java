import java.util.Scanner;

public class Zack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Greeting message
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Zack");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________\n");


        // Listen for commands and exits when user types "bye"
        while (true) {
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            // Echo the command
            System.out.println("____________________________________________________________");
            System.out.println(input);
            System.out.println("____________________________________________________________\n");
        }
    }

}
