import java.util.ArrayList;
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
        ArrayList<String> userInputLog = new ArrayList<>();
        boolean isAcceptingInput = true;


        while (isAcceptingInput) {
            // Print out a prompt for user input
            System.out.print("$ ");
            String input = scanner.nextLine();

            System.out.println("____________________________________________________________");
            switch (input) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    isAcceptingInput = false;
                    break;
                case "list":
                    for (int i = 0; i < userInputLog.size(); i++) {
                        System.out.println(i + 1 + ". " + userInputLog.get(i));
                    }
                    break;
                default:
                    userInputLog.add(input);
                    System.out.println(input);
                    break;
            }
            System.out.println("____________________________________________________________");
        }

    }

}
