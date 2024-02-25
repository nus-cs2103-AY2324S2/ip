package yippee;
import java.util.ArrayList;
import java.util.Scanner;

import yippee.exceptions.YippeeException;
import yippee.tasks.Task;

/**
 * Represents the Ui that replies the user after a command.
 */
public class Ui {
    private Scanner sc;

    /**
     * Instantiates Ui instance.
     * Assigns sc to a new scanner.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Prints any error encountered during execution.
     * @param e YippeeException encountered.
     */
    public void printError(YippeeException e) {
        System.out.printf("        %s\n", e.getMessage());
    }

    /**
     * Prints user input to the console.
     * @param text User input.
     */
    public void echoText(String text) {
        showLine();
        System.out.printf("      %s\n", text);
        showLine();
    }

    /**
     * Toggles to the next command user inputs.
     * @return The string representation of the user command.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints text for confirmation after user successfully adds task.
     * @param task Task user requested to add.
     * @param size Size of the resulting list of tasks after adding.
     */
    public void addTaskRespond(Task task, int size) {
        System.out.println("      Got it! I added:");
        System.out.printf("        %s\n", task.toString());
        System.out.printf("      You now have %d tasks in your list :D\n", size);
    }

    /**
     * Prints text for confirmation after user successfully deletes task.
     * @param task Task user requested to delete.
     * @param size Size of the resulting list of tasks after deleting.
     */
    public void deleteTaskRespond(Task task, int size) {
        System.out.println("      Successfully removed task!");
        System.out.printf("        %s\n", task.toString());
        System.out.printf("      You have %d tasks left in the list :D\n", size);
    }

    /**
     * Prints text for confirmation after user successfully marks task as complete.
     * @param task Task user requested to mark as complete.
     */
    public void markTaskRespond(Task task) {
        System.out.println("      Yippee! I have marked this task as done ;D");
        System.out.printf("        %s\n", task.toString());
    }

    /**
     * Prints text for confirmation after user successfully marks task as incomplete.
     * @param task Task user requested to mark as incomplete.
     */
    public void unmarkTaskRespond(Task task) {
        System.out.println("      Awww...I have marked this task as not done yet :(");
        System.out.printf("        %s\n", task.toString());
    }

    /**
     * Prints text for exiting the chatbot.
     */
    public void endCommands() {
        this.sc.close();
        System.out.println("      Bye! Hope to see you again soon wooo!");
        showLine();
    }

    /**
     * Prints content of list provided.
     * @param taskList List of Tasks to print.
     */
    public void printList(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getList();
        int count = 1;
        if (tasks.size() == 0) {
            System.out.println("      Nothing added to list yet!");
        }
        for (Task task : tasks) {
            System.out.printf("      %d. %s\n", count, task.toString());
            count++;
        }
        assert count >= 1;
    }
}
