package seedu.bobby;

/**
 * <h1> UI </h1>
 * This UI class deals with interaction with the user, like the introduction of the program
 * and the statements printed when the user ends the program.
 *
 * @author Yap Xuan Xuan
 * @version 0.1
 */
public class UI {

    /**
     * Returns a string of the introduction to Bobby to be printed out in GUI
     * @return String of introduction of Bobby
     */
    public static String intro() {
        return "Hello, I'm Bobby!\n" + "What can I do for you today? :)\n";
    }

    /**
     * Throws a BobbyException when the user input did not state the description of the
     * todo task, deadline or event command that is tailored to the different cases.
     *
     * @param tasktype Type of task command that the user tried to call
     * @throws BobbyException which is printed out to the user
     */
    public static void emptyDesc(String tasktype) throws BobbyException {
        if (tasktype.equals("todo")) {
            throw new BobbyException("Oops! Please state the description of your todo task.");
        } else if (tasktype.equals("deadline")) {
            throw new BobbyException("Oops! Please state the description of your task and the deadline.");
        } else if (tasktype.equals("event")) {
            throw new BobbyException("Oops! Please state the description of your event " +
                    "and provide the start and end timing.");
        }
    }

    /**
     * Prints out a bye message when the user ends the program by running the command bye
     */
    public static String bye() {
        return "Bye! Have a great day ahead :>\n";
    }

}
