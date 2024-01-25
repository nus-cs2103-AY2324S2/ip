import java.util.Scanner;

public class Echo {
    private String[] store = new String[100];
    private int indexPt = 0;
    public static void main(String[] args) {
        Echo echo = new Echo();
        greetUser();
        echo.startConversation();
        endConversation();
    }

    public static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Echo");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void startConversation() {
        Scanner scanner = new Scanner(System.in);
        String userCommand;

        do {
            System.out.print("\n");
            userCommand = scanner.nextLine();
            if (userCommand.equals("list")) {
                listCommand();
            } else {
                storeCommand(userCommand);
            }
            //echoCommand(userCommand);
        } while (!userCommand.equalsIgnoreCase("bye"));
    }

    public static void endConversation() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void echoCommand(String command) {
        System.out.println("____________________________________________________________");
        System.out.println(command);
        System.out.println("____________________________________________________________");
    }

    public void storeCommand(String command) {
        if (!command.equals("bye")) {
            store[indexPt] = command;
            indexPt++;
            System.out.println("____________________________________________________________");
            System.out.println("added: " + command);
            System.out.println("____________________________________________________________");
        }
    }

    public void listCommand() {
        System.out.println("____________________________________________________________");
        if (indexPt == 0) {
            System.out.println("No items in the list");
        } else {
            for (int i = 0; i < indexPt; i++) {
                System.out.println((i+1) + ". " + store[i]);
            }
        }
        System.out.println("____________________________________________________________");
    }
}
