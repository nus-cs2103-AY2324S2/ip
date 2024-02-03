package Duke;

import Duke.Tasks.Task;
import java.util.Scanner;

/**
 * Represent Ui class to interact with user.
 */
public class Ui {
    protected Scanner sc;

    /**
     * Constructor for the Ui class.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Gets input entered by user.
     *
     * @return String Input entered by user.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }

    /**
     * Prints greeting message to the user.
     */
    public void sayHello() {
        String greetMessage = String.format(
                "____________________________________________________________\n" +
                        " Hello! I'm Corgi!\n" +
                        " What can I do for you?\n" +
                        "____________________________________________________________\n");
        System.out.println(greetMessage);
    }

    /**
     * Prints goodbye message to the user.
     */
    public void sayGoodbye() {
        String exitMessage = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(exitMessage);
    }

    /**
     * Prints add task message to the user.
     *
     * @param sizeOfTaskList Size of TaskList.
     * @param addedTask Task to be added to TaskList.
     */
    public void printAddMessage(int sizeOfTaskList, Task addedTask) {
        String message = String.format(
                "____________________________________________________________\n" +
                        " Got it. I've added this task:\n" +
                        "  [%s][%s] %s\n" +
                        " Now you have %d tasks in the list.\n" +
                        "____________________________________________________________\n",
                addedTask.getTaskType(), addedTask.getStatusIcon(),
                addedTask.toString(), sizeOfTaskList);
        System.out.println(message);
    }

    /**
     * Prints delete task message to the user.
     *
     * @param sizeOfTaskList Size of TaskList.
     * @param deletedTask Task to be deleted.
     */
    public void printDeleteMessage(int sizeOfTaskList, Task deletedTask) {
        String deleteMessage = String.format(
                "____________________________________________________________\n" +
                        " Noted. I've removed this task:\n" +
                        "  [%s][%s] %s\n" +
                        " Now you have %d tasks in the list.\n" +
                        "____________________________________________________________\n",
                deletedTask.getTaskType(), deletedTask.getStatusIcon(),
                deletedTask.toString(), sizeOfTaskList);
        System.out.println(deleteMessage);
    }

    /**
     * Prints print list message to the user.
     *
     * @param taskList TaskList to be printed.
     */
    public void printList(TaskList taskList) {
        System.out.println("____________________________________________________________\n" +
                " Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            String currentTask = String.format("%d.[%s][%s] %s",
                    i + 1, taskList.getTask(i).getTaskType(),
                    taskList.getTask(i).getStatusIcon(), taskList.getTask(i).toString());
            System.out.println(currentTask);
        }
        System.out.println("____________________________________________________________\n");
    }

    /**
     * Prints mark task as done message to the user.
     *
     * @param taskToBeMarked Task to be marked.
     * @param taskNumber Index of the task to be marked.
     */
    public void printMarkDoneMessage(Task taskToBeMarked, int taskNumber) {
        String message = String.format(
                "____________________________________________________________\n" +
                        " Nice! I've marked this task as done:\n" +
                        "  [%s][%s] %s\n" +
                        "____________________________________________________________\n",
                taskToBeMarked.getTaskType(), taskToBeMarked.getStatusIcon(),
                taskToBeMarked.getDescription());
        System.out.println(message);
    }

    /**
     * Prints mark task as not done message to the user.
     *
     * @param taskToBeUnmarked Task to be unmarked.
     * @param taskNumber Index of the task to be unmarked.
     */
    public void printMarkUndoneMessage(Task taskToBeUnmarked, int taskNumber) {
        String message = String.format(
                "____________________________________________________________\n" +
                        " OK, I've marked this task as not done yet:\n" +
                        "  [%s][%s] %s\n" +
                        "____________________________________________________________\n",
                taskToBeUnmarked.getTaskType(), taskToBeUnmarked.getStatusIcon(),
                taskToBeUnmarked.getDescription());
        System.out.println(message);
    }

    /**
     * Prints error message to the user.
     *
     * @param message Error message for user.
     */
    public void printErrorMessage(String message) {
        String errorMessage = "____________________________________________________________\n" +
                message + "\n____________________________________________________________\n";
        System.out.println(errorMessage);
    }

    /**
     * Prints date time error message to the user.
     *
     * @param message Error message for user.
     */
    public void printDateErrorMessage(String message) {
        String errorMessage = "____________________________________________________________\n" +
                message + "\n____________________________________________________________\n";
        System.out.println(errorMessage);
    }

}

