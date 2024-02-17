package seedu.duke;

import java.util.ArrayList;

import seedu.duke.task.Task;

/**
 * Represents the <code>UI</code> that users see in response to
 * their potential input commands e.g., <code>Hello</code>
 */
public class Ui {

    /**
     * Represents printed output when a DukeException is thrown.
     * @param dukeException
     */
    public String printError(DukeException dukeException) {
        return dukeException.getMessage();
    }

    /**
     * Represents printed output when Duke starts.
     */
    public String openingMessage() {
        return "Hello! I'm Klara\nWhat can I do for you?";
    }

    /**
     * Represents printed output when Duke ends.
     */
    public String closingMessage() {
        // Logging off upon "bye" command
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Represents printed output when a <code>Task</code> is marked as done.
     * @param task Represents the <code>Task</code> object marked
     */
    public void showTaskDone(Task task) {
        printBreak();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        printBreak();
    }

    /**
     * Represents printed output when a <code>Task</code> is marked as undone.
     * @param task Represents the <code>Task</code> object unmarked
     */
    public void showTaskUndone(Task task) {
        printBreak();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        printBreak();
    }

    /**
     * Represents printed output when a <code>Task</code> is added.
     * @param task Represents the <code>Task</code> object added
     * @param size Represents size of Task list to be printed
     */
    public String showTaskAdded(Task task, int size) {
        StringBuilder sb = new StringBuilder("Got it. I've added this task:");
        sb.append("\n" + " " + task);
        sb.append("\nNow you have " + size + " tasks in the list.");
        return sb.toString();
    }

    /**
     * Represents printed output when a <code>Task</code> is deleted.
     * @param task Represents the <code>Task</code> object deleted
     * @param size Represents size of Task list to be printed
     */
    public void showTaskDeleted(Task task, int size) {
        printBreak();
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + size + " tasks in the list.");
        printBreak();
    }

    /**
     * Default spacer to print to tidy output.
     */
    public void printBreak() {
        String line = "____________________________________________________________";
        System.out.println(line);
    }

    /**
     * Represents printed output when <code>list</code> command is given
     * Loop through the list to print out each <code>Task</code> and its
     * description
     * @param list Represents the list to be printed to system output.
     */
    public void printList(ArrayList<Task> list) {
        printBreak();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getStatus()) {
                System.out.println(i + 1 + "." + list.get(i));
            } else {
                System.out.println(i + 1 + "." + list.get(i));
            }
        }
        printBreak();
    }
}
