package drake;
import java.util.ArrayList;

import drake.contact.Contact;
import drake.task.Task;
import drake.task.TaskList;

/**
 * Handles UI operations for the chatbot.
 */
public class Ui {
    private static final String DASHED_LINE = "___________________________\n";
    /**
     * Returns a welcome message to the user.
     */
    public static String showWelcome() {
        return DASHED_LINE + " What's up everyone. I'm Drake.\n" + " How can I help?\n" + DASHED_LINE;
    }

    /**
     * Returns a goodbye message to the user.
     */
    public String showGoodbye() {
        return DASHED_LINE + " See you later, alligator! \n" + DASHED_LINE;
    }
    /**
     * Returns an error message.
     *
     * @param message The error message to be returned.
     */
    public String showError(String message) {
        assert message != null && !message.isEmpty() : "Error message cannot be null or empty.";
        return DASHED_LINE + message + "\n" + DASHED_LINE;
    }

    /**
     * Returns the list of tasks as a string.
     *
     * @param tasks The TaskList containing the tasks to display.
     */
    public String showTaskList(TaskList tasks) {
        assert tasks != null : "TaskList object cannot be null.";
        StringBuilder sb = new StringBuilder();
        sb.append(DASHED_LINE);
        sb.append("You asked for the tasks in your list? Here:\n");
        ArrayList<Task> taskList = tasks.getTasks();
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(i + 1).append(". ").append(taskList.get(i)).append("\n");
        }
        sb.append(DASHED_LINE);
        return sb.toString();
    }

    /**
     * Returns a message confirming the addition of a task, the task itself, and the new total number of tasks.
     *
     * @param task The task that has been added.
     * @param size The new total number of tasks in the list.
     */
    public String showAddTask(Task task, int size) {
        assert task != null : "Task object cannot be null.";
        return DASHED_LINE + "Got it. I've added this task:\n" + task + "\n" + "Now you have " + size
                + " tasks in the list.\n" + DASHED_LINE;
    }

    /**
     * Returns a message confirming that a task has been marked as done.
     *
     * @param task The task that has been marked.
     */
    public String showMarkTask(Task task) {
        return DASHED_LINE + "Cool. Declaring this task as marked:\n" + task + "\n" + DASHED_LINE;
    }
    /**
     * Returns a message confirming that a task has been marked as not done.
     *
     * @param task The task that has been unmarked.
     */
    public String showUnmarkTask(Task task) {
        return DASHED_LINE + "OK, I've marked this task as not done yet:\n" + task + "\n" + DASHED_LINE;
    }
    /**
     * Returns a message confirming the deletion of a task, the task itself, and the new total number of tasks.
     *
     * @param task The task that has been deleted.
     * @param size The new total number of tasks in the list.
     */
    public String showDeleteTask(Task task, int size) {
        return DASHED_LINE + "Noted. I've removed this task:\n" + task + "\n" + "Now you have " + size
                + " tasks in the list.\n" + DASHED_LINE;
    }

    /**
     * Returns a message showing tasks that match the input.
     *
     * @param matchingTasks The tasks that match the input.
     */
    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        StringBuilder sb = new StringBuilder();
        sb.append(DASHED_LINE);
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            sb.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
        }
        sb.append(DASHED_LINE);
        return sb.toString();
    }

    public String showContactAdd(Contact contact, int size) {
        assert contact != null : "Task object cannot be null.";
        return DASHED_LINE + "Got it. I've added this contact:\n" + contact + "\n" + "Now you have " + size
                + " contacts in your Contact List.\n" + DASHED_LINE;
    }

    public String showContacts(ArrayList<Contact> contacts) {
        StringBuilder sb = new StringBuilder();
        sb.append(DASHED_LINE);
        sb.append("You asked for the contacts in your list? Here:\n");
        for (int i = 0; i < contacts.size(); i++) {
            sb.append(i + 1).append(". ").append(contacts.get(i)).append("\n");
        }
        sb.append(DASHED_LINE);
        return sb.toString();
    }
}
