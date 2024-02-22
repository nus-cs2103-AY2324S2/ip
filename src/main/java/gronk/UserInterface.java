package gronk;

import exception.gronk.EmptyListException;

/**
 * UserInterface class.
 * Helper class that provides text formatting for output messages.
 */
public class UserInterface {
    /**
     * Welcome message to be played when Gronk starts.
     */
    public static final String WELCOME = "Hi, I'm Gronk!\n"
            + "What's up today?";

    /**
     * Closing message to be closed when the user quits Gronk.
     */
    public static final String GOODBYE = "System closing. Goodbye!";

    /**
     * Line variable for message formatting purposes.
     */
    public static final String LINE = "  ----------------------------------------";

    /**
     * Empty constructor for the UserInterface class.
     */
    public UserInterface() {}

    /**
     * Returns the GOODBYE message.
     */
    public String returnGoodbye() {
        return GOODBYE;
    }

    /**
     * Returns the HELLO message.
     */
    public String returnHello() {
        return WELCOME;
    }

    /**
     * Prints a message in a prettified format, by
     * adding a line on top and below of the message.
     *
     * @param message The String to be printed.
     */
    public void printMessage(String message) {
        String prettyMessage = LINE + "\n" + message + "\n" + LINE;
        System.out.println(prettyMessage);
    }

    /**
     * Returns a string which is a prettified format listing
     * down all the Task objects in taskList.
     *
     * @param taskList A TaskList containing Task objects.
     * @return String listing all the Task objects in taskList.
     */
    public String returnAllTasks(TaskList taskList) {
        try {
            if (taskList.getSize() == 0) {
                throw new EmptyListException();
            }
            String message = "";
            for (int j = 0; j < taskList.getSize(); j++) {
                message += "" + Integer.toString(j + 1) + ". " + taskList.getTask(j).toString() + "\n";
            }
            return message.substring(0, message.length() - 1);
        } catch (EmptyListException e) {
            return e.toString();
        }
    }
}
