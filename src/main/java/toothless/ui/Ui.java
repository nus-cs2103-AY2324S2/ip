package toothless.ui;

import java.util.ArrayList;
import java.util.Scanner;

import toothless.task.Task;

/**
 * Class for dealing with interactions with the user.
 */
public class Ui {
    private Scanner scanner;

    /**
     * A public constructor for the Ui class.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads command from the user.
     *
     * @return String of the command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the given message.
     *
     * @param message String to be printed.
     */
    public void printMessage(String message) {
        System.out.println("\t" + message);
    }

    /**
     * Prints a line to separate messages.
     */
    public void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints the welcome message.
     */
    public void printWelcome() {
        printLine();
        String introMessage = " /\\_/\\\n"
                + "\t( o.o )\n"
                + "\t > ^ <\n"
                + "\tNya-ice to meet you! I'm Toothless :D\n"
                + "\tWhat can I do for you?";
        printMessage(introMessage);
        printLine();
    }

    /**
     * <<<<<<< HEAD
     * Prints new task message.
     *
     * @param newTask      New Task to be printed.
     * @param taskListSize Size of tasklist.
     */
    public void printNewTask(Task newTask, int taskListSize) {
        String message =
                String.format("\tGot it. I've added this task:\n\t\t%s\n\tNya-ow you have %d tasks in the list.",
                        newTask, taskListSize);
        System.out.println(message);
    }

    /**
     * Prints deleted task message.
     *
     * @param deletedTask  Deleted Task to be printed.
     * @param taskListSize Size of tasklist.
     */
    public void printDeletedTask(Task deletedTask, int taskListSize) {
        String message =
                String.format("\tNoted. I've remeowved this task:\n\t\t%s\n\tNya-ow you have %d tasks in the list.",
                        deletedTask, taskListSize);
        System.out.println(message);
    }

    /**
     * Prints marked task message.
     *
     * @param markedTask Marked Task to be printed.
     */
    public void printMarkedTask(Task markedTask) {
        System.out.println("\tAmeowzing! I've marked this task as done:\n\t" + markedTask);
    }

    /**
     * Prints unmarked task message.
     *
     * @param unmarkedTask Unmarked Task to be printed.
     */
    public void printUnmarkedTask(Task unmarkedTask) {
        System.out.println("\tOK, I've marked this task as not done yet:\n\t" + unmarkedTask);
    }

    /**
     * Prints the TaskList in order.
     *
     * @param taskArrayList The ArrayList of Tasks to be printed.
     */
    public void printList(ArrayList<Task> taskArrayList, String noTasksMessage, String introMessage) {
        if (taskArrayList.size() == 0) {
            System.out.println(noTasksMessage);
        } else {
            String listString = introMessage;
            for (int i = 1; i <= taskArrayList.size(); i++) {
                listString += "\t";
                listString += i + ". " + taskArrayList.get(i - 1);
                if (i < taskArrayList.size()) {
                    listString += "\n";
                }
            }
            System.out.println(listString);
        }
    }
}
