import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        String name = "Fluffy";

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        // Create a scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // While loop for echoing input until user input is "bye"
        while (true) {
            // Print out a prompt for user input
            System.out.print("Enter command: ");
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(input);
                System.out.println("____________________________________________________________");
            }
        }

        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
