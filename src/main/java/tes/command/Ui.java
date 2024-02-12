package tes.command;

import java.util.Scanner;

import tes.taskmanager.Storage;
import tes.taskmanager.TaskList;

/**
 * Represents a class to deal with input and output of the chatbot system.
 */
public class Ui {
    /** Line seperator */
    private static final String LINE = "    _______________________________________________________________";
    private Scanner scanner; // Scanner for input
    private TaskList taskList; // tes.taskmanager.Task List to store tasks
    private Storage store;

    /**
     * Constructs a User Interface to deal with input and output.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.store = new Storage();
        this.taskList = new TaskList(this.store.loadFromFile());
    }

    /**
     * Prints a separator line.
     */
    public static void printLine() {
        System.out.println(LINE);
    }

    /**
     * Greets the user when initializing the chatbot.
     */
    public void greet() {
        printLine();
        System.out.println(
                "    Tes here.\n"
                + "    huh? What you want from me?"
        );
        printLine();
    }

    /**
     * Scans the next input.
     *
     * @return New input.
     */
    public String nextCommand() {
        return scanner.nextLine();
    }

    /**
     * Says goodbye when closing the chatbot.
     */
    public void close() {
        printLine();
        System.out.println(
                "    Annoying brat (-.-)"
        );
        printLine();
    }

    /**
     * Adds a to-do task to the task list.
     *
     * @param command tes.taskmanager.Task description of the new task.
     */
    public void addToDo(String command) {
        this.taskList.storeToDo(command);
        printLine();
        System.out.println("    Got it. I've added this task:\n      "
                + this.taskList.getTaskDescription(this.taskList.getSize() - 1)
                + "\n    Now you have "
                + this.taskList.getSize()
                + " in the list.");
        printLine();
    }

    /**
     * Adds a task with deadline to the task list.
     *
     * @param command tes.taskmanager.Task description of the new task.
     * @param by tes.taskmanager.Deadline of the task.
     */
    public void addDeadline(String command, String by) {
        this.taskList.storeDeadline(command, by);
        printLine();
        System.out.println("    Got it. I've added this task:\n      "
                + this.taskList.getTaskDescription(this.taskList.getSize() - 1)
                + "\n    Now you have "
                + this.taskList.getSize()
                + " in the list.");
        printLine();
    }

    /**
     * Adds a task with a designated period to the task list.
     * @param command tes.taskmanager.Task description of the new task.
     * @param from Starting time of the task.
     * @param to Ending time of the task.
     */
    public void addEvent(String command, String from, String to) {
        this.taskList.storeEvent(command, from, to);
        printLine();
        System.out.println("    Got it. I've added this task:\n      "
                + this.taskList.getTaskDescription(this.taskList.getSize() - 1)
                + "\n    Now you have "
                + this.taskList.getSize()
                + " in the list.");
        printLine();
    }

    /**
     * Lists the tasks stored.
     */
    public void listTask() {
        printLine();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= this.taskList.getSize(); i++) {
            System.out.println("    " + i + "."
                    + this.taskList.getTaskDescription(i - 1));
        }
        printLine();
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of the task to be marked.
     */
    public void mark(int index) {
        this.taskList.mark(index);
        System.out.println("    Nice! I've marked this task as done:\n      "
                + this.taskList.getTaskDescription(index));
        printLine();
    }

    /**
     * Unmarks a task.
     *
     * @param index Index of the task to be unmarked.
     */
    public void unmark(int index) {
        this.taskList.unmark(index);
        printLine();
        System.out.println("    OK, I've marked this task as not done yet:\n      "
                + this.taskList.getTaskDescription(index));
        printLine();
    }

    /**
     * Deletes a task in the task list.
     *
     * @param index Index of the task to be unmarked.
     */
    public void delete(int index) {
        printLine();
        System.out.println("    Noted. I've removed this task:\n      "
                + this.taskList.getTaskDescription(index - 1)
                + "\n    Now you have "
                + (this.taskList.getSize() - 1)
                + " in the list.");
        this.taskList.delete(index - 1);
        printLine();
    }

    /**
     * Finds tasks in the list with a keyword.
     *
     * @param keyword Word used to find the task.
     */
    public void find(String keyword) {
        printLine();
        System.out.println("    Here are the matching tasks in your list:");
        int counter = 1;
        for (int i = 1; i <= this.taskList.getSize(); i++) {
            String temp = this.taskList.getTaskDescription(i - 1);
            if (temp.contains(keyword)) {
                System.out.println("    " + counter + "."
                        + temp);
                counter++;
            }
        }
        printLine();
    }

}
