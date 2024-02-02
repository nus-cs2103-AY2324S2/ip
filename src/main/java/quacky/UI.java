package quacky;

import java.util.Scanner;
/**
 * Handles user interactions for the Quacky application. This class is responsible for
 * input/output operations, including reading commands from the user and displaying messages.
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

    public void say(String response) {
        System.out.println(format(response));
    }

    public void showGreeting() {
        this.say("Quack! how u doing, Im Quacky How can I help you?");
    }

    public void showFarewell() {
        this.isRunning = false;
        this.say("Quack Quack");
    }

    public void showList(TaskList tasks) {
        this.say(tasks.toString());
    }

    public void showMarkDone(String task) {
        this.say("Quack! I marked this task as done \n\t" + task);
    }

    public void showUnmarkDone(String task) {
        this.say("Quack! I marked this task as not done \n\t" + task);
    }

    public void showDeleteTask(int taskNumber, String task) {
        String message = "Quack! I removed this task:  \n\t" + task
                + "\nNow you have " + taskNumber + " tasks in the list.";
        this.say(message);
    }

    public void showAddTask(int taskNumber, String task) {
        String message = "Got it. I've added this task:\n\t" + task +
                "\nNow you have " + taskNumber + " tasks in the list.";
        this.say(message);
    }

    void showErrorMessage(Exception e) {
        this.say(e.getMessage());
    }

}
