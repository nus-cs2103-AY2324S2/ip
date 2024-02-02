package harper.utils;

import harper.exceptions.HarperException;

import harper.tasks.Task;

import java.util.Scanner;

/**
 * The Ui class interacts with user. It read user's input and show different messages to user.
 */
public class Ui {
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Greets the user.
     */
    public void greet() {
        System.out.println("\nHello! I am Harper.\n"
                + "What can I do for you?\n");
    }

    /**
     * Exit the chat.
     */
    public void exit() {
        System.out.println("\nHope to see you again soon! Peace out!\n");
        this.scanner.close();
    }

    /**
     * Reads user's input.
     *
     * @return commands.Command entered by user.
     */
    public String readCommand() {
        return this.scanner.nextLine().trim();
    }

    /**
     * Prints out the task list.
     *
     * @param tasksString task.Task list in string form.
     */
    public void printTasks(String tasksString) {
        if (tasksString.isBlank()) {
            System.out.println("\nNothing is in your list\n");
        } else {
            System.out.println("\nHere are the tasks in your list:\n"
                    + tasksString);
        }
    }

    /**
     * Prints out message for successful add.
     *
     * @param taskList task.Task list that being added.
     * @param task task.Task that being added.
     */
    public void printSuccessfulAdd(TaskList taskList, Task task) {
        int taskListSize = taskList.size();
        System.out.println("\nGot it. I've added this task into your list:\n"
                + task.toString() + "\n"
                + "Now you have " + taskListSize + (taskListSize > 1 ? " tasks " : " task ")
                + "in the list.\n");
    }

    /**
     * Prints out message for successful delete.
     *
     * @param taskList task.Task list that being deleted.
     * @param task task.Task that being deleted.
     */
    public void printSuccessfulDelete(TaskList taskList, Task task) {
        int taskListSize = taskList.size();
        System.out.println("\nOk! I've removed this task for you:\n"
                + task.toString() + "\n"
                + "Now you have " + taskListSize + (taskListSize > 1 ? " tasks " : " task ")
                + "in the list.\n");
    }

    /**
     * Prints out the error message.
     *
     * @param exception Exception that contains the error message.
     */
    public void showError(HarperException exception) {
        System.out.println(exception.getMessage());
    }

    /**
     * Prints out message for successful mark.
     *
     * @param task task.Task to be marked.
     * @param isMarked Indicates whether to mark as done or not done.
     */
    public void printSuccessfulMark(Task task, boolean isMarked) {
        if (isMarked) {
            System.out.println("\nNice! I've marked this task as done:\n"
                    + task.toString()
                    + "\n");
        } else {
            System.out.println("\nOK, I've marked this task as not done yet:\n"
                    + task.toString()
                    + "\n");
        }
    }
}
