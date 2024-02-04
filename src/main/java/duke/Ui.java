package duke;

/**
 * The Ui class is responsible for handling all user interface elements
 * of the application, such as greeting users, displaying messages, and showing errors.
 * It provides methods to print various types of messages to the console.
 */
public class Ui {
    /**
     * Displays a greeting message to the user.
     * This includes an ASCII art representation of a snowman and a welcome message.
     */
    public static String greet() {
        return " Hello! I'm SnowBoy\n" + " What can I do for you?";
    }

    /**
     * Displays a loading error message.
     * This is typically called when the application fails to load an existing task list
     * and needs to create a new one.
     */
    public void showLoadingError() {
        System.out.println(" No existing list detected. Creating new list...");
    }

    /**
     * Displays an exit message to the user.
     * This message is shown when the user decides to exit the application.
     */
    public static String exit() {
        return " Bye. Hope to see you again soon!";
    }

    /**
     * Processes the user command and directs it to the appropriate method.
     * Supports a variety of commands such as adding, deleting, and listing tasks.
     *
     * @param tl The TaskList to perform operations on.
     * @param cmd The user input command to be processed.
     * @throws DukeException If the command is invalid or incorrectly used.
     */
    public static String checkCmd(TaskList tl, String cmd) throws DukeException {
        String[] commandArr = cmd.split(" ");
        switch (commandArr[0]) {
        case "list":
            return Parser.printLst(tl);
        case "find":
            return Parser.findTask(tl, commandArr);
        case "mark":
            return Parser.markTask(tl, commandArr);
        case "unmark":
            return Parser.unmarkTask(tl, commandArr);
        case "todo":
            return Parser.addTodo(tl, commandArr, cmd);
        case "deadline":
            return Parser.addDeadline(tl, commandArr, cmd);
        case "event":
            return Parser.addEvent(tl, commandArr, cmd);
        case "delete":
            return Parser.deleteTask(tl, commandArr);
        default:
            throw new DukeException(String.format(" Sorry, %s is not a valid command :(", cmd));
        }
    }
}
