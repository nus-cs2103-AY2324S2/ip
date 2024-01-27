package kaiyap;

import java.util.ArrayList;

public class Ui {

    TaskList taskList;

    /**
     * Sets the TaskList object for the UI to interact with.
     *
     * @param taskList The TaskList to be set for UI operations.
     */
    public void setTaskList(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Prints a greeting message to the user.
     * This method is typically called at the start of the application.
     */
    public void sayHello() {
        System.out.println("\t____________________________________________________________\n" +
                "\tHello! I'm KaiYap.\n" +
                "\tWhat can I do for you?\n" +
                "\t____________________________________________________________\n"
        );
    }

    /**
     * Prints a farewell message to the user.
     * This method is typically called at the end of the application.
     */
    public void sayBye() {
        System.out.println("\t____________________________________________________________\n" +
                "\tBye. Hope to see you again soon!\n" +
                "\t____________________________________________________________");
    }

    /**
     * Lists all the tasks currently in the task list.
     * This method prints each task with its index in a formatted manner.
     */
    public void listInputs() {
        System.out.println("\t____________________________________________________________\n\tHere are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints an error message.
     *
     * @param error The error message to be printed.
     */
    public void printError(String error) {
        System.out.println("\t____________________________________________________________\n" +
                error +
                "\n\t____________________________________________________________\n");
    }

    /**
     * Prints a list of tasks found.
     *
     * @param tasks the list of tasks that matched the search phrase.
     */
    public void printTasksFound(ArrayList<Task> tasks) {
        System.out.println(
                "\t____________________________________________________________\n"
                        + "\tHere are the matching tasks in your list:"
        );
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(
                "\t" + (i + 1) + ". " + tasks.get(i).toString()
            );
        }
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints the output after deleting a task.
     *
     * @param t the task to be deleted
     * @param size the size of the list after deletion
     */
    public void printTaskRemoved(Task t, int size) {
        System.out.println(
                "\t____________________________________________________________\n" +
                        "\tNoted. I've removed this task:\n" +
                        "\t\t" + t.toString() +
                        "\n\tYou now have " + size + (size == 1 ? " task" : " tasks") + " in the list.\n" +
                        "\t____________________________________________________________"
        );
    }
}
