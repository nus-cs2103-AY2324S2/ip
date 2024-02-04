/*
 * Package: Echo.Ui
 * Module/Project Name: Echo
 * File: Ui.java
 *
 * Description:
 * This class provides user interface functionality, including greeting the user, starting and ending conversations,
 * and interacting with the Parser and TaskManager.
 *
 */

package Echo.Ui;
import Echo.TaskManager;
import Echo.Parser.Parser;

public class Ui {
    /**
     * Displays a greeting message to the user.
     */
    public static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Echo.Echo");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Initiates the conversation by invoking the parser to handle user input.
     *
     * @param taskManager The TaskManager instance to manage tasks.
     */
    public static void startConversation(TaskManager taskManager) {
        Parser.parse(taskManager);
    }

    /**
     * Displays a farewell message to the user when ending the conversation.
     */
    public static void endConversation() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
