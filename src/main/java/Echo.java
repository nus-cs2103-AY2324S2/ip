import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Echo {
    private TaskManager taskManager;

    public Echo() {
        this.taskManager = new TaskManager();
    }

    public static void main(String[] args) {
        Echo echo = new Echo();
        echo.greetUser();
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
            taskManager.executeCommand(userCommand);
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
}
