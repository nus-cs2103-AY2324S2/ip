package Echo.Ui;

import Echo.Parser.Parser;
import Echo.TaskManager;

public class Ui {
    public static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Echo.Echo");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void startConversation(TaskManager taskManager) {
        Parser.parse(taskManager);
    }

    public static void endConversation() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
