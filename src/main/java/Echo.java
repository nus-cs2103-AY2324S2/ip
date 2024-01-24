import java.util.Scanner;

public class Echo {
    public static void main(String[] args) {
        greetUser();
        startConversation();
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Echo");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void startConversation() {
        Scanner scanner = new Scanner(System.in);
        String userCommand;

        do {
            System.out.print("\n");
            userCommand = scanner.nextLine();
            echoCommand(userCommand);
        } while (!userCommand.equalsIgnoreCase("bye"));
    }

    public static void echoCommand(String command) {
        System.out.println("____________________________________________________________");
        System.out.println(command);
        System.out.println("____________________________________________________________");
    }
}
