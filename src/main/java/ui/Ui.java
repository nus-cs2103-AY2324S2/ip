package ui;

import java.util.Scanner;
import task.TaskList;
import task.Task;
import exception.DukeException;

public class Ui {
    Scanner reader;

    public Ui() {
        this.reader = new Scanner(System.in);
    }

    private static final String LINE = "    ___________________________________________________________\n";

    public void showLine() {
        System.out.println(LINE);
    }

    /**
     * Displays a greeting message.
     * 
     * @param botName Name of the bot.
     */
    public void showWelcomeMessage(String botName) {
        System.out.println(String.format(
                "%s     Hello! I'm %s \n     What can I do for you? \n%s", LINE, botName, LINE));
    }

    /**
     * Displays a farewell message.
     */
    public void showExitMessage() {
        System.out.println(String.format(
                "%s     Bye. Hope to see you again soon! \n%s", LINE, LINE));
    }

    public void showErrorMessage(String errorMessage) {
        showLine();
        System.out.println("     " + errorMessage);
        showLine();
    }

    /**
     * Displays a message of the deleted task and the number of task in the list
     * 
     * @param task
     * @param listOfTasks
     */
    public void deleteTask(Task task, TaskList taskList) {
        this.showLine();
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + task);
        int len = taskList.getSize();
        System.out.println(String.format("     Now you have %d tasks in the list.", len));
        this.showLine();
    }

    /**
     * Displays a repeated message of the input by the user and number of task in
     * list.
     * 
     * @param task        Task input
     * @param listOfTasks List of all tasks
     */
    public void showRepeatFunction(Task task, TaskList taskList) {
        this.showLine();
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + task);
        int len = taskList.getSize();
        System.out.println(String.format("     Now you have %d tasks in the list.", len));
        this.showLine();
    }

    /**
     * Marks task as done.
     * 
     * @param task
     */
    public void showMark(Task task) {
        showLine();
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + task.toString());
        showLine();
    }

    /**
     * Marks task as not done.
     * 
     * @param task
     */
    public void showUnmark(Task task) {
        showLine();
        System.out.println("     OK, I've marked this task as not done yet:");
        System.out.println("       " + task.toString());
        showLine();
    }

    /**
     * Displays the list of Strings.
     * 
     * @param listOfStrings list of Strings.
     */
    public void printList(TaskList taskList) {
        showLine();
        StringBuilder finalString = new StringBuilder();
        finalString.append("     Here are the tasks in your list:\n");
        int counter = 1;
        for (Task c : taskList.getList()) {
            finalString.append(String.format("     %d. %s\n", counter, c));
            counter++;
        }
        System.out.println(finalString.toString());
        showLine();
    }

    public void showLoadingError(DukeException e) {
        showLine();
        System.out.println(e.toString() + "\n");
        showLine();
    }

    public String readLine() throws DukeException {
        return reader.nextLine();
    }
}
