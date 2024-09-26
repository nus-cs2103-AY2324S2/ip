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

package echo.ui;
import echo.Echo;
import echo.TaskManager;

public class Ui {
    private static Echo echoInstance;

    /**
     * Displays a greeting message to the user.
     */
    /*public static void greetUser() {
        echoInstance.displayBotResponse("Hello! I'm Echo.Echo\nWhat can I do for you?");
    }*/

    /**
     * Initiates the conversation by invoking the parser to handle user input.
     *
     * @param taskManager The TaskManager instance to manage tasks.
     */
    public static void startConversation(String userCommand, TaskManager taskManager) {
        if (!userCommand.equalsIgnoreCase("bye")) {
            //echoInstance.echoCommand(userCommand);
            taskManager.executeCommand(userCommand);
        } else {
            echoInstance.endConversation();
        }
    }

    /**
     * Displays a farewell message to the user when ending the conversation.
     */
    /*public static void endConversation() {
        echoInstance.echoCommand("Bye. Hope to see you again soon!");
    }*/

    /**
     * Set the Echo instance for communication.
     *
     * @param echo The Echo instance.
     */
    public static void setEcho(Echo echo) {
        echoInstance = echo;
    }

}
