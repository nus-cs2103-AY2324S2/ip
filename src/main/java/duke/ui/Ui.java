package duke.ui;

import duke.common.TaskList;
import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * The Ui class implements methods that display result to users with given format and read input from the user
 */
public class Ui {
    private static final String DIVIDER = "===================================================";
    private Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Reads the input from user
     */
    public void showWelcome() {
        System.out.println(DIVIDER);
        System.out.println("Hello I'm wind" + "\n"
                + "What can I do for you?");
        System.out.println(DIVIDER);
    }

    /**
     * Display loading exception message to the user
     */
    public void showLoadingException() {
        System.out.println(DIVIDER);
        System.out.println("Bro, something bad happens during loading, I can't load your tasks");
        System.out.println(DIVIDER);
    }

    /**
     * Shows error message to the user
     * @param message The error message
     */
    public void showError(String message) {
        System.out.println(DIVIDER);
        System.out.println(message);
        System.out.println(DIVIDER);
    }

    /**
     * Shows the tasks in the taskList in the users
     * @param taskList the TaskList containing all tasks that users have
     */
    public void showTaskList(TaskList taskList) {
        System.out.println(DIVIDER);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getListSize(); i++) {
            int number = i + 1;
            System.out.println(number + "." + taskList.getTask(i));
        }
        System.out.println(DIVIDER);
    }

    /**
     * Show the result of new task being added to the user
     * @param task The new task
     * @param taskList The TaskList containing all the tasks that users have
     */
    public void showNewTask(Task task, TaskList taskList) {
        System.out.println(DIVIDER);
        System.out.printf("Got it. I've added this task:\n" +
                "%s\n" +
                "Now you have %d tasks in the list%n", task, taskList.getListSize());
        System.out.println(DIVIDER);
    }

    /**
     * Shows result when a task is being mark as done
     * @param task The task that is being mark as done
     */
    public void showMaskAsDone(Task task) {
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:\n"
                + " " + task);
        System.out.println(DIVIDER);
    }

    /**
     * Shows result when a task is being mark as not done
     * @param task The task being mark as not done
     */
    public void showMarkAsNotDone(Task task) {
        System.out.println(DIVIDER);
        System.out.println(
                "OK, I've marked this task as not done yet:\n"
                        + " " + task);
        System.out.println(DIVIDER);
    }

    /**
     * Shows result when a task is deleted
     * @param task The task that is deleted
     * @param taskList The TaskList object containing all the tasks
     */
    public void showDeleteTask(Task task, TaskList taskList) {
        System.out.println(DIVIDER);
        System.out.println("Noted. I've removed this task:\n" +
                " " + task + "\n" +
                "Now you have " + taskList.getListSize() + " tasks in the list");
        System.out.println(DIVIDER);
    }

    /**
     * Shows the tasks that is due on a specific date
     * @param tasks The tasks that are due on that date
     * @param localDate The due date
     */
    public void showDueTaskList(List<Task> tasks, LocalDate localDate) {
        System.out.println(DIVIDER);
        if (tasks.isEmpty()) {
            System.out.printf("You have no task due on %s\n",
                    localDate.format(DateTimeFormatter.ofPattern("MM dd yy")));
        } else {
            System.out.printf("The following tasks are due on %s\n",
                    localDate.format(DateTimeFormatter.ofPattern("MM dd yy")));
            for(int i = 0; i< tasks.size(); i++) {
                System.out.println(i + 1 + "." + " " + tasks.get(i));
            }
        }
        System.out.println(DIVIDER);
    }

    /**
     * Shows good bye message
     */
    public void showGoodBye() {
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }
}
