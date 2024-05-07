package sky;

import java.util.ArrayList;
import java.util.Scanner;

import task.Task;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private final String LINE = "\t____________________________________________________________";
    private Scanner sc = new Scanner(System.in);

    /**
     * Reads the next line of input from the user.
     * @return The next line of input from the user.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Shows a line to the user.
     * @return The line to be shown to the user.
     */
    public String showLine() {
        System.out.println(LINE);
        return LINE;
    }

    /**
     * Shows the greetings to the user.
     * @return The greetings to be shown to the user.
     */
    public String sayGreetings() {
        StringBuilder output = new StringBuilder();
        output.append("\tHello! I'm SKY\n");
        output.append("\tWhat can I do for you?\n");
        this.showLine();
        System.out.println(output);
        this.showLine();
        return output.toString();
    }

    /**
     * Shows the goodbye message to the user.
     * @return The goodbye message to be shown to the user.
     */
    public String sayBye() {
        String output = "\tBye. Hope to see you again soon!";
        System.out.println(output);
        this.showLine();
        return output;
    }

    /**
     * Shows an error message to the user.
     * @param e Exception to show the error message for.
     * @return The error message to be shown to the user.
     */
    public String showErrorMessage(Exception e) {
        String output = "\t" + e.getMessage();
        System.out.println(output);
        this.showLine();
        return output;
    }

    /**
     * Shows the added task to the user.
     * @param task Task to show the user.
     * @param size Size of the task list.
     * @return The added task to be shown to the user.
     */
    public String showAddTask(Task task, int size) {
        StringBuilder output = new StringBuilder();
        output.append("\tGot it. I've added this task:\n");
        output.append("\t  " + task + "\n");
        output.append("\tNow you have " + size + " tasks in the list.");
        System.out.println(output);
        this.showLine();
        return output.toString();
    }

    /**
     * Shows the list of tasks to the user.
     * @param list List of tasks to show the user.
     * @return The list of tasks to be shown to the user.
     */
    public String showList(ArrayList<Task> list) {
        StringBuilder output = new StringBuilder();
        output.append("\tHere are the tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            output.append("\t" + (i + 1) + ". " + list.get(i) + "\n");
        }
        System.out.println(output);
        this.showLine();
        return output.toString();
    }

    /**
     * Shows the marked task to the user.
     * @param task Task to show the user.
     * @return The marked task to be shown to the user.
     */
    public String showMarkTask(Task task) {
        String output = "\tNice! I've marked this task as done:\n\t  " + task;
        System.out.println(output);
        this.showLine();
        return output;
    }

    /**
     * Shows the unmarked task to the user.
     * @param task Task to show the user.
     * @return The unmarked task to be shown to the user.
     */
    public String showUnmarkTask(Task task) {
        String output = "\tOK, I've marked this task as not done yet:\n\t  " + task;
        System.out.println(output);
        this.showLine();
        return output;
    }

    /**
     * Shows the deleted task to the user.
     * @param task Task to show the user.
     * @param size Size of the task list.
     * @return The deleted task to be shown to the user.
     */
    public String showDeleteTask(Task task, int size) {
        StringBuilder output = new StringBuilder();
        output.append("\tNoted. I've removed this task:\n");
        output.append("\t  " + task + "\n");
        output.append("\tNow you have " + size + " tasks in the list.");
        System.out.println(output);
        this.showLine();
        return output.toString();
    }

    /**
     * Shows the corrupted data message to the user.
     * @return The corrupted data message to be shown to the user.
     */
    public String showCorruptedData() {
        String output = "\tData file is corrupted. Starting with an empty list.";
        System.out.println(output);
        this.showLine();
        return output;
    }

    /**
     * Shows the tasks that match the keyword
     * @param list the list of tasks that match the keyword
     * @return the tasks that match the keyword
     */
    public String showFindTasks(ArrayList<Task> list) {
        StringBuilder output = new StringBuilder();
        output.append("\tHere are the matching tasks in your list:\n");
        for (int i = 0; i < list.size(); i++) {
            output.append("\t" + (i + 1) + ". " + list.get(i) + "\n");
        }
        System.out.println(output);
        this.showLine();
        return output.toString();
    }

    /**
     * Shows the archive message to the user.
     * @param archiveName Name of the archive file.
     * @return The archive message to be shown to the user.
     */
    public String showArchiveMessage(String archiveName) {
        String output = "\tTasks have been archived to " + archiveName;
        System.out.println(output);
        this.showLine();
        return output;
    }
}
