package virtue;

import java.io.IOException;

public class Virtue {
    /** The storage that handles file reading and writing. */
    private final Storage storage;

    /** The task list to be used by the chatbot. */
    private VirtueTaskList tasks;

    /** A horizontal line. */
    private static final String HORIZONTAL_LINE = "_".repeat(60);

    /** True if chatbot is commanded to exit and false otherwise. */
    public boolean isExit;

    /** Creates a new Virtue class instance. */
    public Virtue() {
        storage = new Storage();
        isExit = false;
    }

    /**
     * Adds an indention to the string.
     *
     * @param str The string to be indented.
     * @return The indented string.
     */
    protected static String indent(String str) {
        return "    " + str;
    }

    /**
     * Sandwiches a string between two horizontal lines.
     *
     * @param str The string to be sandwiched.
     * @return The sandwiched string.
     */
    protected static String sandwich(String str) {
        return HORIZONTAL_LINE + "\n" + str + "\n" + HORIZONTAL_LINE;
    }

    /**
     * Greets the user.
     *
     * @return The greeting message.
     */
    public String greet() {
        return sandwich(indent("Hello! I'm Virtue \n    What can I do for you?"));
    }

    /**
     * Exits with a goodbye message.
     *
     * @return The goodbye message.
     */
    private String bye() {
        return sandwich(indent("Bye. Hope to see you again soon!"));
    }

    /** Runs the chatbot. */
    protected void initialize() {
        try {
            tasks = storage.loadTaskList();
        } catch (VirtueDateTimeException e) {
            System.out.println("OOPS! There is a date not in the correct format.");
        }
    }

    /**
     * Generates a response to user input.
     */
    public String getResponse(String input) {
        Command currentCommand;
        try {
            currentCommand = new Command(input);
        } catch (VirtueException e) {
            return sandwich(indent(" " + e.getMessage()));
        }

        if (currentCommand.isBye()) {
            isExit = true;
            return bye();
        } else {
            try {
                String message = tasks.executeCommand(currentCommand);
                storage.saveToFile(tasks);
                return message;
            } catch (IOException e) {
                return sandwich(indent(" OOPS! An error occurred while taking the inputs: " + e));
            }
        }
    }
}
