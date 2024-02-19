package judy.ui;

import judy.task.Task;
import judy.task.TaskList;

import java.util.Scanner;

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
    public void showWelcome() {
        System.out.println(Messages.GREET_MESSAGE);
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
    public void showAddTask(Task task, int size) {
        System.out.println( Messages.ADD_TASK_MESSAGE +
                task.toString() +
                Messages.printTaskSize(size));
    }

    /**
     * Displays a message indicating the successful deletion of a task.
     *
     * @param task The task that was deleted.
     * @param size The updated size of the task list.
     */
    public void showDeleteTask(Task task, int size) {
        System.out.println(Messages.DELETE_TASK_MESSAGE + task.toString() + Messages.printTaskSize(size));
    }

    /**
     * Displays a message indicating the successful marking of a task as done.
     *
     * @param task The task that was marked as done.
     */
    public void showMarkTask(Task task) {
        String s = Messages.MARK_TASK_MESSSAGE + task.toString();
        System.out.println(s);
    }

    /**
     * Displays a message indicating the successful marking of a task as undone.
     *
     * @param task The task that was marked as undone.
     */
    public void showUnmarkTask(Task task) {
        System.out.println(Messages.UNMARK_TASK_MESSAGE + task.toString());
    }

    /**
     * Displays the list of tasks or an appropriate message if the task list is empty.
     *
     * @param taskList The TaskList containing the tasks to be displayed.
     */
    public void showTaskList(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println(Messages.EMPTY_TASKLIST_MESSAGE);
        } else {
            System.out.println(Messages.LIST_TASKS_MESSAGE);
            for(int i = 0; i < taskList.getSize(); i++) {
                int seq = i+1;
                Task t = taskList.get(i);
                System.out.println ("  " + seq + ". " + t.toString());
            }
        }
    }

    /**
     * Displays a help message with instructions on using the application.
     */
    public void showHelp() {
        System.out.println(Messages.helpMessage());
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    public void showError(String errorMessage) {
         System.out.println(errorMessage);
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showGoodbye() {
        System.out.println(Messages.GOODBYE_MESSAGE);
    }

}
