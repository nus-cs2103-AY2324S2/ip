package headcube;
/**
 * The Ui class handles the user interface for the HeadCube application.
 * It is responsible for displaying messages to the user.
 */
public class Ui {
    /**
     * Constructor of Ui object
     */
    public Ui() {
    }

    /**
     * Displays greeting message.
     *
     * @return The greeting string.
     */
    public String greet() {
        return "Hello! I'm HeadCube\n" + "What can I do for you?\n";
    }

    /**
     * Displays exit message.
     *
     * @return The goodbye string.
     */
    public static String exit() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Displays error message.
     *
     * @param message The error message to be displayed.
     * @return The error message.
     */
    public String error(String message) {
        return message;
    }

    /**
     * Displays saved message.
     *
     * @return The save message.
     */
    public String saveMessage() {
        return "Finished saving";
    }

    public String duplicateMessage() {
        return "The task is already in the list.";
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param taskList The list of tasks to be displayed.
     * @return A string lists all the tasks.
     */
    public String list(TaskList taskList) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append((i + 1)).append(". ").append(taskList.get(i)).append("\n");
        }
        return sb.toString();
    }
    /**
     * Generates and returns a string representation of tasks found based on a find query.
     * If no tasks match the query, a message indicating that no matching tasks were found is returned.
     * Otherwise, a list of matching tasks is compiled into a string and returned.
     *
     * @param tasks The TaskList containing tasks that matched the search criteria.
     * @return A string detailing the matching tasks. If no tasks match, a message indicating
     *         that no matching tasks were found is included.
     */
    public String showFoundTasks(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        if (tasks.isEmpty()) {
            sb.append("No matching tasks found.");
        } else {
            sb.append("Here are the matching tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                sb.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
            }
        }
        return sb.toString();
    }
}
