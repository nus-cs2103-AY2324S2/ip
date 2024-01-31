package utilities;

import java.util.Scanner;

import task.TaskList;
import task.Task;

public class Ui {
    /**
     * Method to read what the user inputs.
     * @return The next line of input by the user.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm TodoPal!");
        System.out.println("What can I do for you?");
    }

    /**
     * Lists out all existing tasks in the task list.
     * @param taskList The task list to be listed out.
     */
    public void showList(TaskList taskList) {
        if (taskList.isEmpty()) {
            System.out.println("Your task list is empty.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < taskList.length(); i++) {
                System.out.println((i + 1) + "." + taskList.getTask(i).toString());
            }
        }
    }

    /**
     * Displays the length of the task list.
     * @param taskList The task list to be determined.
     */
    public void showTaskListLength(TaskList taskList) {
        System.out.println("Now you have " + taskList.length() + " task(s) in the list.");
    }

    /**
     * Displays the task that has been deleted.
     * @param removedTask The task that has been deleted.
     */
    public void showDelete(Task removedTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask.toString());
    }

    /**
     * Displays the task that has been marked.
     * @param task The task that has been marked.
     * @param isMarked A variable to determine whether a task is to be marked or unmarked.
     */
    public void showMarkTask(Task task, boolean isMarked) {
        System.out.println("Nice! I've marked this task as " + (isMarked ? "done:" : "not done yet:"));
        System.out.println(task.toString());
    }

    /**
     * Displays the error message that to user encounters.
     * @param message The error message.
     */
    public void showError(String message) {
        System.out.println(message);
    }
}
