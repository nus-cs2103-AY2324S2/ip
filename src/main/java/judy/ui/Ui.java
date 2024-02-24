package judy.ui;

import java.util.Scanner;

import judy.task.Task;
import judy.task.TaskList;

/**
 * The Ui class handles interactions with the user.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a Ui object with a Scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns a command entered by the user.
     *
     * @return The user-entered command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user.
     */
    public String showWelcome() {
        return Messages.GREET_MESSAGE;
    }

    /**
     * Displays a line separator.
     */
    public void showLine() {
        System.out.println(Messages.LINE);
    }

    /**
     * Displays a message indicating the successful addition of a task.
     *
     * @param task The task that was added.
     * @param size The updated size of the task list.
     */
    public String showAddTask(Task task, int size) {
        return (Messages.ADD_TASK_MESSAGE
                + task.toString()
                + Messages.printTaskSize(size));
    }

    /**
     * Displays a message indicating the successful deletion of a task.
     *
     * @param task The task that was deleted.
     * @param size The updated size of the task list.
     */
    public String showDeleteTask(Task task, int size) {
        return Messages.DELETE_TASK_MESSAGE + task.toString() + Messages.printTaskSize(size);
    }

    /**
     * Displays a message indicating the successful marking of a task as done.
     *
     * @param task The task that was marked as done.
     */
    public String showMarkTask(Task task) {
        String s = Messages.MARK_TASK_MESSSAGE + task.toString();
        return s;
    }

    /**
     * Displays a message indicating the successful marking of a task as undone.
     *
     * @param task The task that was marked as undone.
     */
    public String showUnmarkTask(Task task) {
        return Messages.UNMARK_TASK_MESSAGE + task.toString();
    }

    /**
     * Displays the list of tasks or an appropriate message if the task list is empty.
     *
     * @param taskList The TaskList containing the tasks to be displayed.
     */
    public String showTaskList(TaskList taskList) {
        if (taskList.isEmpty()) {
            return Messages.EMPTY_TASKLIST_MESSAGE;
        } else {
            String s = Messages.LIST_TASKS_MESSAGE;
            for (int i = 0; i < taskList.getSize(); i++) {
                int seq = i + 1;
                Task t = taskList.get(i);
                s += ("\n  " + seq + ". " + t.toString());
            }
            return s;
        }
    }

    /**
     * Displays the list of tasks that contained matching keywords
     * or an appropriate message if the task list is empty.
     *
     * @param taskList The TaskList containing the tasks with matching keyword.
     */
    public String showFindTasks(TaskList taskList) {
        if (taskList.isEmpty()) {
            return Messages.EMPTY_MATCHING_TASKS_MESSAGE;
        } else {
            String s = Messages.LIST_MATCHING_TASKS_MESSAGE;
            for (int i = 0; i < taskList.getSize(); i++) {
                int seq = i + 1;
                Task t = taskList.get(i);
                s += ("\n  " + seq + ". " + t.toString());
            }
            return s;
        }
    }

    /**
     * Displays a help message with instructions on using the application.
     */
    public String showHelp() {
        return Messages.helpMessage();
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Displays a goodbye message to the user.
     */
    public String showGoodbye() {
        return Messages.GOODBYE_MESSAGE;
    }

}
