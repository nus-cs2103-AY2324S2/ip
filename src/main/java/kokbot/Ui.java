package kokbot;

import kokbot.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    /**
     * Name of the bot
     */
    protected String botName;

    /**
     * Scanner to read user input
     */
    protected Scanner scanner;

    /**
     * Constructor for Ui
     * @param botName Name of the bot
     */
    public Ui(String botName) {
        this.botName = botName;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads the next line of user input
     * @return Next line of user input
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Shows the message if error when loading file
     */
    public String showLoadingError() {
        String errorMessage = "Error loading file. Creating new file...";

        return errorMessage;
    }

    /**
     * Shows welcome message
     */
    public String showWelcome() {
        String welcomeMessage = String.format(
                "Hello! I'm %s%n What can I do for you?%n",
                 botName
        );
        return welcomeMessage;
    }

    /**
     * Shows a string representation of the list of tasks
     */
    public String showTaskList(String[] tasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" Here are the tasks in your list:\n");

        for (int i = 0; i < tasks.length; i++) {
            stringBuilder.append(String.format("%s%n", tasks[i]));
        }

        return stringBuilder.toString();
    }

    /**
     * Shows a string representation of the task marked as done
     * @param task Task being marked as done
     */
    public String showTaskMarked(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" Nice! I've marked this task as done:\n");
        stringBuilder.append(String.format("   %s%n", task));

        return stringBuilder.toString();
    }


    /**
     * Shows a string representation of the task marked as undone
     * @param task Task being marked as undone
     */
    public String showTaskUnmarked(Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" OK, I've marked this task as not done yet:\n");
        stringBuilder.append(String.format("   %s%n", task));

        return stringBuilder.toString();
    }

    /**
     * Shows a string representation of the task added
     * @param task Task being added
     * @param len Length of the list of tasks
     */
    public String showTaskAdded(Task task, int len) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Got it. I've added this task:\n");
        stringBuilder.append("  ").append(task).append('\n');
        stringBuilder.append("Now you have ").append(len).append(" tasks in the list.");

        return stringBuilder.toString();
    }


    /**
     * Shows a string representation of the task deleted
     * @param task Task being deleted
     * @param len Length of the list of tasks
     */
    public String showTaskDeleted(Task task, int len) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" Noted. I've removed this task:\n");
        stringBuilder.append(String.format("   %s%n", task));
        stringBuilder.append(String.format(" Now you have %d tasks in the list.%n", len));

        return stringBuilder.toString();
    }


    /**
     * Bids goodbye to the user
     */
    public String showGoodbye() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" Bye. Hope to see you again soon!\n");

        return stringBuilder.toString();
    }


    public String showMatchingTasks(ArrayList<String> matchingTasks) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" Here are the matching tasks in your list:\n");

        for (String task : matchingTasks) {
            stringBuilder.append(String.format("   %s%n", task));
        }

        return stringBuilder.toString();
    }
}
