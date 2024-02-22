package seedu.klara;

import java.util.ArrayList;

import seedu.klara.task.Task;

/**
 * Represents the <code>UI</code> that users see in response to
 * their potential input commands e.g., <code>Hello</code>
 */
public class Ui {

    /**
     * Represents printed output when a KlaraException is thrown.
     * @param klaraException
     * @return String to be printed
     */
    public String printError(KlaraException klaraException) {
        return klaraException.getMessage();
    }

    /**
     * Represents printed output when Klara starts.
     * @return String to be printed
     */
    public String openingMessage() {
        return "Hello! I'm Klara\nWhat can I do for you?";
    }

    /**
     * Represents printed output when Klara ends.
     * @return String to be printed
     */
    public String closingMessage() {
        // Logging off upon "bye" command
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Represents printed output when a <code>Task</code> is marked as done.
     * @param task Represents the <code>Task</code> object marked
     * @return String to be printed
     */
    public String showTaskDone(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Represents printed output when a <code>Task</code> is marked as undone.
     * @param task Represents the <code>Task</code> object unmarked
     * @return String to be printed
     */
    public String showTaskUndone(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Represents printed output when a <code>Task</code> is added.
     * @param task Represents the <code>Task</code> object added
     * @param size Represents size of Task list to be printed
     * @return String to be printed
     */
    public String showTaskAdded(Task task, int size) {
        assert size >= 0 : "Size of list cannot be negative";
        StringBuilder sb = new StringBuilder("Got it. I've added this task:");
        sb.append("\n" + " " + task);
        sb.append("\nNow you have " + size + " tasks in the list.");
        return sb.toString();
    }

    /**
     * Represents printed output when a <code>Task</code> is deleted.
     * @param task Represents the <code>Task</code> object deleted
     * @param size Represents size of Task list to be printed
     * @return String to be printed
     */
    public String showTaskDeleted(Task task, int size) {
        assert size >= 0 : "Size of list cannot be negative";
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + size + " tasks in the list.";
    }

    /**
     * Represents printed output when <code>list</code> command is given
     * Loop through the list to print out each <code>Task</code> and its
     * description
     * @param list Represents the list to be printed to system output.
     * @return String to be printed
     */
    public String printList(ArrayList<Task> list) {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            output.append("\n" + (i + 1) + "." + list.get(i).toString());
        }
        return output.toString();
    }

    /**
     * Represents printed output when <code>find</code> command is given
     * Loop through the list to print out each <code>Task</code> and its
     * description
     * @param list Represents the list to be printed to system output.
     * @param searchTerm Represents the search term to be used
     * @return String to be printed
     */
    public String searchThenPrintList(ArrayList<Task> list, String searchTerm) {
        assert searchTerm != null : "Search term cannot be null";
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).containsSearchTerm(searchTerm)) {
                output.append("\n" + (i + 1) + "." + list.get(i).toString());
            }
        }
        return output.toString();
    }
}
