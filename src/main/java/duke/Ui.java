package duke;

/**
 * The Ui class is responsible for handling all user interface elements
 * of the application, such as greeting users, displaying messages, and showing errors.
 * It provides methods to print various types of messages to the console.
 */
public class Ui {

    /**
     * This enum represents a UI command.
     */
    public enum Command {
        LIST,
        FIND,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        HELP,
    }

    /**
     * Displays a greeting message to the user and shows the command for help.
     *
     */
    public static String greet() {
        return "Brr, what a cold day it is today. I'm SnowBoy, at your service!\n"
                + "To see the list of commands available, type 'help' below!";
    }

    /**
     * Displays a loading error message.
     * This is typically called when the application fails to load an existing task list
     * and needs to create a new one.
     */
    public void showLoadingError() {
        System.out.println(" Oh man.. no existing list detected. Creating new list...");
    }

    /**
     * Displays an exit message to the user.
     * This message is shown when the user decides to exit the application.
     */
    public static String exit() {
        return " Ahh, I'm melting away.. bye!";
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
        Command cmdType = Parser.parseCmdType(commandArr);
        switch (cmdType) {
        case LIST:
            return Parser.printLst(tl);
        case FIND:
            return Parser.findTask(tl, cmd, commandArr);
        case MARK:
            return Parser.markTask(tl, commandArr);
        case UNMARK:
            return Parser.unmarkTask(tl, commandArr);
        case TODO:
            return Parser.addTodo(tl, cmd, commandArr);
        case DEADLINE:
            return Parser.addDeadline(tl, cmd, commandArr);
        case EVENT:
            return Parser.addEvent(tl, cmd, commandArr);
        case DELETE:
            return Parser.deleteTask(tl, commandArr);
        case HELP:
            return Parser.showHelpPage();
        default:
            throw new DukeException(String.format(" Sorry, %s is not a valid command :(", cmd));
        }
    }
}
