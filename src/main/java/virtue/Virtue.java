package virtue;

import java.io.IOException;

public class Virtue {
    /** The storage that handles file reading and writing. */
    private final Storage storage;

    /** The task list to be used by the chatbot. */
    private VirtueTaskList tasks;

    /** True if chatbot is commanded to exit and false otherwise. */
    public boolean isExit;

    /** Creates a new Virtue class instance. */
    public Virtue() {
        storage = new Storage();
        isExit = false;
    }

    /**
     * Greets the user.
     *
     * @return The greeting message.
     */
    public String greet() {
        return "Hello! I'm Virtue.\nWhat can I do for you?";
    }

    /**
     * Exits with a goodbye message.
     *
     * @return The goodbye message.
     */
    private String bye() {
        return "Bye. Hope to see you again soon!";
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
            return e.getMessage();
        }

        if (currentCommand.isBye()) {
            isExit = true;
            return bye();
        }

        try {
            tasks.applyCommand(currentCommand);
            String message = currentCommand.getResultMessage();

            assert message != null : "message should not be null";

            storage.saveToFile(tasks);
            return message;
        } catch (IOException e) {
            return "OOPS! An error occurred while taking the inputs: ";
        }
    }
}
