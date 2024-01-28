import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm EchoBot.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        String userInput;
        while(true) {
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }

            System.out.println("____________________________________________________________");
            System.out.println(userInput);
            System.out.println("____________________________________________________________");
        }

        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
