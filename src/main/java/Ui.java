import java.util.Scanner;

public class Ui {
    // Constants
    private static final String CHATBOT_NAME = "Chitty";
    private static final String GREETING_MESSAGE = String.format("Hello! I'm %s\nWhat can I do for you?\n", CHATBOT_NAME);
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!\n";
    private static final String ADD_TASK = "Got it. I've added this task:\n";
    private static final String TASK_LENGTH = "Now you have %d tasks in the list.\n";
    private static final String MARK_TASK = "Nice! I've marked this task as done:\n";
    private static final String UNMARK_TASK = "OK, I've marked this task as not done yet:\n";
    private static final String DELETE_TASK = "Noted. I've removed this task:\n";
    private static final String LIST_TASKS = "Here are the tasks in your list:\n";
    private static final String INVALID_INPUT = "Invalid input, please double check your input values!\n";
    private static final String INVALID_COMMAND = "Invalid command, please only use the following commands:\n" +
            "todo, deadline, event, list, mark, unmark, delete, bye \n";
    private static final String SPACING = "----------------------------------------------------------\n";

    private final Scanner scanner;

    Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readInput() {
        return scanner.hasNextLine() ? scanner.nextLine() : "";
    }

    /**
     * Displays a greeting message to the user.
     */
    public void displayGreetingMessage() {
        System.out.println(SPACING + GREETING_MESSAGE + SPACING);
    }

    /**
     * Displays a goodbye message to the user and terminates the application.
     */
    public void displayGoodByeMessage() {
        System.out.println(SPACING + GOODBYE_MESSAGE + SPACING);
    }

    public void displayAddedTask(Task task, int length) {
        System.out.println(SPACING + ADD_TASK + task + "\n" +
                String.format(TASK_LENGTH, length) + SPACING);
    }

    public void displayMarkedTask(Task task) {
        System.out.println(SPACING + MARK_TASK + task + "\n" + SPACING);
    }

    public void displayUnmarkedTask(Task task) {
        System.out.println(SPACING + UNMARK_TASK + task + "\n" + SPACING);
    }

    public void displayDeletedTask(Task task, int length) {
        System.out.println(SPACING + DELETE_TASK + task + "\n" +
                String.format(TASK_LENGTH, length) + SPACING);
    }
    /**
     * Lists all tasks in the task list.
     */
    public void listTasks(TaskList taskList) {
        System.out.println(SPACING + LIST_TASKS + taskList + SPACING);
    }

    /**
     * Displays a message indicating that the user input is invalid.
     */
    public void displayInvalidInputMessage() {
        System.out.println(SPACING + INVALID_INPUT + SPACING);
    }

    /**
     * Displays a specific message indicating why the user input is invalid.
     *
     * @param message The specific message about invalid input.
     */
    public void displayInvalidInputMessage(String message) {
        System.out.println(SPACING + message + SPACING);
    }

    /**
     * Displays a message indicating that the user command is invalid.
     */
    public void displayInvalidCommandMessage() {
        System.out.println(SPACING + INVALID_COMMAND + SPACING);
    }
}
