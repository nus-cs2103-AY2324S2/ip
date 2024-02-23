package quacky;

import java.util.Scanner;
/**
 * Handles user interactions for the Quacky application. This class is responsible for
 * input/output operations, including reading commands fromDate the user and displaying messages.
 */
public class UI {
    public boolean isRunning;
    private final Scanner scanner = new Scanner(System.in);

    public UI() {
        this.isRunning = true;
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Formats the given text with a standardized header and footer for display.
     *
     * @param text The text to be formatted and displayed.
     * @return The formatted text string.
     */

    private String format(String text) {
        String[] lines = text.split("\n");
        StringBuilder sb = new StringBuilder("\t____________________________________________________________\n");

        for (String line : lines) {
            sb.append("\t").append(line).append("\n");
        }

        sb.append("\t____________________________________________________________");
        return sb.toString();
    }

    public String say(String response) {
        System.out.println(format(response));
        return response;
    }

    public String showGreeting() {
        return this.say("Quack! how u doing, Im Quacky How can I help you?");
    }

    public String showFarewell() {
        this.isRunning = false;
        return this.say("Quack Quack");
    }

    public String showList(TaskList tasks) {
        String taskList = tasks.toString();

        if (taskList.isEmpty()) {
            String emptyList = "Quack. I have no tasks";
            return this.say(emptyList);
        }
        //the taskList is either empty or not empty
        return this.say(taskList);
    }

    public String showMarkDone(String task) {
        return this.say("Quack! I marked this task as done \n\t" + task);
    }

    public String showUnmarkDone(String task) {
        return this.say("Quack! I marked this task as not done \n\t" + task);
    }

    public String showDeleteTask(int taskNumber, String task) {
        String message = "Quack! I removed this task:  \n\t" + task
                + "\nNow you have " + taskNumber + " tasks in the list.";
        return this.say(message);
    }

    public String showAddTask(int taskNumber, String task) {
        String message = "Got it. I've added this task:\n\t" + task +
                "\nNow you have " + taskNumber + " tasks in the list.";
        return this.say(message);
    }

    public String showErrorMessage(Exception e) {
        return this.say(e.getMessage());
    }

}
