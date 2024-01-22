import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________");
        System.out.println("     Hello! I'm Cleo");
        System.out.println("     What can I do for you?");
        System.out.println("____________________________________________________________");
        while (true) {
            // Read user input
            String input = scanner.nextLine();

            // Check for the exit condition
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            // Echo the input back to the user
            System.out.println("____________________________________________________________");
            System.out.println("     " + input);
            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}
