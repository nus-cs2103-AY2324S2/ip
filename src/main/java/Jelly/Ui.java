package jelly;

/**
 * deals with interactions with the user
 */
public class Ui {

    private static final String LINE = "\n-------------------------------------------";

    private static final String WELCOME = "(ᵔ_ᵔ) Hello! I'm Jelly\nWhat can I do for you?";
    private static final String FAREWELL = "(•︿•) Bye. Hope to see you again soon!";

    /**
     * Currently empty constructor
     */
    public Ui() {

    }

    /**
     * prints a loading error on the screen
     * @param e The exception
     */
    public void printLoadingError(JellyException e) {

        System.out.println(e.getMessage());
    }

    /**
     * Prints a line on the screen
     */
    public void printLine() {

        System.out.println(LINE);
    }

    /**
     * Prints contents of welcome on the screen
     */
    public void printWelcomeMessage() {

        System.out.println(WELCOME);
    }

    /**
     * Prints contents of farewell on the screen
     */
    public void printFarewellMessage() {

        System.out.println(FAREWELL);
    }

    /**
     * Prints a message on the screen.
     * @param message message to be printed
     */
    public void printMessage(String message) {

        System.out.println(message);
    }

    /**
     * @param taskList TaskList to search from
     * @param keyword keyword of task to search
     */
    public void printSearchResults(TaskList taskList, String keyword) {

        TaskList result = taskList.find(keyword);

        System.out.println(result);
    }
}
