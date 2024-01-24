import java.util.Scanner;  // Import the Scanner class

public class Duke {
    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Homie");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in); // Create scanner
        String command = scanner.nextLine();  // Read user command
        while (!command.equals("bye")) {
            System.out.println(command);  // Echo command
            command = scanner.nextLine();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
