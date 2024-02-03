package toothless;

import java.util.Scanner;
import toothless.tasks.*;

/**
 * This class is responsible for managing the user interface for the Toothless application.
 * It handles displaying messages to the user, including welcome and farewell messages, tasks,
 * and task lists, as well as reading user input.
 */
public class Ui {

    private String splitLine = "____________________________________________________________";
    private String chatBotName = "Toothless";
    private String greetingString = "Hi! "+ chatBotName +" is " + chatBotName + "!\n"
            + "What can " + chatBotName + " do for human?\n" + splitLine;
    private String exitString = "Bye. Hope to see you again soon!";

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        System.out.println(greetingString);
    }

    /**
     * Displays the exit message to the user.
     */
    public void showFarewell() {
        System.out.println(exitString);
    }

    /**
     * Displays the line separator to the user.
     */
    public void showLine() {
        System.out.println(splitLine);
    }

    /**
     * Displays a single task to the user.
     * @param task The task to be displayed.
     */
    public void showTask(Task task) {
        System.out.println(" ["+ task.getTaskIcon()+"]["+ task.getStatusIcon() + "] " + task);
    }

    /**
     * Displays a single task with its index to the user.
     * @param task The task to be displayed.
     * @param index The index of the task to be displayed.
     */
    public void showTask(Task task, int index) {
        System.out.format("%d. ["+ task.getTaskIcon()+"]["+ task.getStatusIcon() + "] " + task + "\n", index + 1);
    }

    /**
     * Displays a loading message indicating that previous tasks are being loaded from the file.
     */
    public void showLoadingTasks() {
        System.out.println("Loading previous recorded tasks...\n" + splitLine);
    }

    /**
     * Displays all incomplete tasks to the user.
     * @param tasks The TaskList containing the tasks to be displayed.
     */
    public void showIncompleteTask(TaskList tasks) {
        System.out.println("You have these unmarked tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.getTask(i);
            if (!t.isDone()) {
                this.showTask(t);
            }
        }
        this.showLine();
    }

    /**
     * Reads a command from the user.
     * @return The command entered by the user.
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
