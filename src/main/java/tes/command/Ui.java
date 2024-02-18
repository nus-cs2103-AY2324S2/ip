package tes.command;

import java.util.Scanner;

import tes.taskmanager.Storage;
import tes.taskmanager.TaskList;

/**
 * Represents a class to deal with input and output of the chatbot system.
 */
public class Ui {
    /** Line seperator */
    private static final String LINE = "    _______________________________________________________________\n";
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
     * Greets the user when initializing the chatbot.
     */
    public String greet() {
        return "    Tes here.\n"
                + "    Huh? What you want from me?";
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
    public String close() {
        return "    Annoying brat (-.-)";
    }

    /**
     * Adds a to-do task to the task list.
     *
     * @param command tes.taskmanager.Task description of the new task.
     */
    public String addToDo(String command) {
        this.taskList.storeToDo(command);
        return "    Got it. I've added this task:\n      "
                + this.taskList.getTaskDescription(this.taskList.getSize() - 1)
                + "\n    Now you have "
                + this.taskList.getSize()
                + " in the list.";
    }

    /**
     * Adds a task with deadline to the task list.
     *
     * @param command tes.taskmanager.Task description of the new task.
     * @param by tes.taskmanager.Deadline of the task.
     */
    public String addDeadline(String command, String by) {
        this.taskList.storeDeadline(command, by);
        return "    Got it. I've added this task:\n      "
                + this.taskList.getTaskDescription(this.taskList.getSize() - 1)
                + "\n    Now you have "
                + this.taskList.getSize()
                + " in the list.";
    }

    /**
     * Adds a task with a designated period to the task list.
     * @param command tes.taskmanager.Task description of the new task.
     * @param from Starting time of the task.
     * @param to Ending time of the task.
     */
    public String addEvent(String command, String from, String to) {
        this.taskList.storeEvent(command, from, to);
        return "    Got it. I've added this task:\n      "
                + this.taskList.getTaskDescription(this.taskList.getSize() - 1)
                + "\n    Now you have "
                + this.taskList.getSize()
                + " in the list.";
    }

    /**
     * Lists the tasks stored.
     */
    public String listTask() {
        StringBuilder tasks = new StringBuilder("    Here are the tasks in your list:\n");
        for (int i = 1; i <= this.taskList.getSize(); i++) {
            tasks.append("    ")
                    .append(i)
                    .append(". ")
                    .append(this.taskList.getTaskDescription(i - 1))
                    .append("\n"); // Appends a new line character
        }
        return tasks.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of the task to be marked.
     */
    public String mark(int index) {
        this.taskList.mark(index);
        return "    Nice! I've marked this task as done:\n      "
                + this.taskList.getTaskDescription(index);
    }

    /**
     * Unmarks a task.
     *
     * @param index Index of the task to be unmarked.
     */
    public String unmark(int index) {
        this.taskList.unmark(index);
        return "    OK, I've marked this task as not done yet:\n      "
                + this.taskList.getTaskDescription(index);
    }

    /**
     * Deletes a task in the task list.
     *
     * @param index Index of the task to be unmarked.
     */
    public String delete(int index) {
        String deletedTask ="    Noted. I've removed this task:\n      "
                + this.taskList.getTaskDescription(index - 1)
                + "\n    Now you have "
                + (this.taskList.getSize() - 1)
                + " in the list.";

        this.taskList.delete(index - 1);
        return deletedTask;
    }

    /**
     * Finds tasks in the list with a keyword.
     *
     * @param keyword Word used to find the task.
     */
    public String find(String keyword) {
        StringBuilder foundTasks = new StringBuilder("    Here are the matching tasks in your list:");
        int counter = 1;
        for (int i = 1; i <= this.taskList.getSize(); i++) {
            String taskDescription = this.taskList.getTaskDescription(i - 1);
            if (taskDescription.contains(keyword)) {
                foundTasks.append(counter)
                        .append(". ")
                        .append(taskDescription)
                        .append("\n");
                counter++;
            }
        }
        return foundTasks.toString();
    }

}
