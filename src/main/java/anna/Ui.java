package anna;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
* The Ui class manages the user interface for the Anna chatbot application.
*
* Provides methods for interacting with the user via stdin and stdout, displaying
* messages, and processing user commands.
*
* @see TaskList
*/
public class Ui {

    private static final String GREET_FORMAT =
        "Hello! I'm %s! What can I do for you?";

    private static final String NAME = ">uwu<";

    private List<String> buffer;
    private Consumer<String> reply;

    /**
    * Constructs a Ui object, initializing the scanner for user input.
    */
    public Ui() {
        buffer = new ArrayList<String>();
        reply = s -> buffer.add(s);
    }

    void greet() {
        reply.accept(String.format(GREET_FORMAT, NAME));
    }

    void error(String msg) {
        reply.accept(String.format("OOPS!! %s", msg));
    }

    /**
    * Handles user commands and performs actions on the task list.
    *
    * @param tasks     The task list on which the command actions are performed.
    * @param command   The command string indicating the action to be performed.
    * @param arguments An array of strings representing the arguments for the command.
    * @return {@code true} if the chatbot should continue processing commands,
    *         {@code false} if it should exit.
    * @throws AnnaException
    */
    boolean handleCommand(TaskList tasks, Command command)
            throws AnnaException {
        return command.execute(reply, tasks);
    }

    /**
     * Returns all buffered replies and clear buffer
     * @return
     */
    public String flushBuffer() {
        String ret = String.join("\n", buffer);
        buffer.removeIf(t -> true);
        return ret;
    }
}
