package secretaryw;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

/**
 * Parses user input for the SecretaryW application.
 */
public class Parser {
    private Scanner scanner;
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructs a new Parser object.
     */
    public Parser(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Retrieves the next command entered by the user.
     *
     * @return Array of strings representing the parsed command.
     */

    // In Parser.java

    public String handleCommand(String[] command) {
        try {
            switch (command[0]) {
                case "bye":
                    closeScanner();
                    System.exit(0); // Terminate the program after saving tasks
                    break;
                case "list":
                    return ui.showTasks(taskList.getTasks());
                case "mark":
                    return markTaskAsDone(command[1]);
                case "unmark":
                    return markTaskAsUndone(command[1]);
                case "delete":
                    return deleteTask(command[1]);
                case "find":
                    String keyword = command[1];
                    ArrayList<Task> matchingTasks = taskList.findTasks(keyword);
                    return ui.showMatchingTasks(matchingTasks);
                case "todo":
                    return addTodoTask(command[1]);
                case "deadline":
                    return addDeadlineTask(command[1]);
                case "event":
                    return addEventTask(command[1]);
                default:
                    return ui.showMessage("I'm sorry, but I don't know what that means :-(");
            }
        } catch (WException e) {
            return ui.showMessage("OOPS!!! " + e.getMessage());
        }
        return "";
    }


    /**
     * Marks a task as done based on the specified index.
     *
     * @param argument The index of the task to mark as done.
     * @throws WException If the index is out of bounds.
     */
    private String markTaskAsDone(String argument) throws WException {
        int index = Integer.parseInt(argument.trim()) - 1;
        checkIndexBounds(index);
        taskList.getTasks().get(index).markAsDone();
        return ui.showMessage("Nice! I've marked this task as done:\n  " +
                taskList.getTasks().get(index).getStatusIcon() + " " +
                taskList.getTasks().get(index).getDescription());
    }

    /**
     * Deletes a task based on the specified index.
     *
     * @param argument The index of the task to delete.
     * @throws WException If the index is out of bounds.
     */
    private String markTaskAsUndone(String argument) throws WException {
        int index = Integer.parseInt(argument.trim()) - 1;
        checkIndexBounds(index);
        taskList.getTasks().get(index).markAsUndone();
        return ui.showMessage("OK, I've marked this task as not done yet:\n  " +
                taskList.getTasks().get(index).getStatusIcon() + " " +
                taskList.getTasks().get(index).getDescription());
    }

    /**
     * Deletes a task based on the specified index.
     *
     * @param argument The index of the task to delete.
     * @throws WException If the index is out of bounds.
     */
    private String deleteTask(String argument) throws WException {
        int index = Integer.parseInt(argument.trim()) - 1;
        checkIndexBounds(index);
        Task removedTask = taskList.getTasks().remove(index);
        return ui.showMessage("Noted. I've removed this task:\n  " + removedTask);
    }

    /**
     * Adds a to do task with the specified description.
     *
     * @param argument The description of the to do task.
     * @throws WException If the description is empty.
     */
    private String addTodoTask(String argument) throws WException {
        if (argument.isEmpty()) {
            throw new WException("The description of a todo cannot be empty");
        }
        taskList.addTask(new Task(TaskType.TODO, argument));
        return ui.showTaskAdded(taskList.getTasks().get(taskList.size() - 1), taskList.size());
    }

    /**
     * Adds a deadline task with the specified description and deadline.
     *
     * @param argument The description and deadline of the deadline task.
     * @throws WException If the format of the deadline is incorrect.
     */
    private String addDeadlineTask(String argument) throws WException {
        String[] parts = argument.split("/by");
        if (parts.length != 2) {
            throw new WException("Wrong format. Please retype");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        checkDeadline(by);
        taskList.addTask(new Task(TaskType.DEADLINE, description, by));
        return ui.showTaskAdded(taskList.getTasks().get(taskList.size() - 1), taskList.size());
    }

    /**
     * Adds an event task with the specified description and time period.
     *
     * @param argument The description and time period of the event task.
     * @throws WException If the format of the time period is incorrect.
     */
    private String addEventTask(String argument) throws WException {
        String[] parts = argument.split("/from");
        if (parts.length != 2) {
            throw new WException("Wrong format. Please retype");
        }
        String description = parts[0].trim();
        String[] time = parts[1].split("/to");
        if (time.length != 2) {
            throw new WException("Wrong format. Please retype");
        }
        String from = time[0].trim();
        String to = time[1].trim();
        checkEvent(from, to);
        taskList.addTask(new Task(TaskType.EVENT, description, from, to));
        return ui.showTaskAdded(taskList.getTasks().get(taskList.size() - 1), taskList.size());
    }

    /**
     * Checks if the specified index is within the bounds of the task list.
     *
     * @param index The index to check.
     * @throws WException If the index is out of bounds.
     */
    private void checkIndexBounds(int index) throws WException {
        if (index < 0 || index >= taskList.size()) {
            throw new WException("Index is out of bounds!");
        }
    }

    /**
     * Checks if the specified deadline string has the correct date format.
     *
     * @param by The deadline string to check.
     * @throws WException If the date format is incorrect.
     */
    private void checkDeadline(String by) throws WException {
        try {
            LocalDate.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            throw new WException("Wrong date format. Please use dd/mm/yyyy");
        }
    }

    /**
     * Checks if the specified event start and end date strings have the correct date format.
     *
     * @param from The start date string of the event.
     * @param to   The end date string of the event.
     * @throws WException If the date format is incorrect.
     */
    private void checkEvent(String from, String to) throws WException {
        try {
            LocalDate.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy"));
            LocalDate.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            throw new WException("Wrong date format. Please use dd/mm/yyyy");
        }
    }

    /**
     * Custom exception class used in the SecretaryW application to represent errors specific to the application's functionality.
     */
    static class WException extends Exception {
        public WException(String msg) {
            super(msg);
        }
    }
    /**
     * Closes the scanner.
     */
    public void closeScanner() {
        scanner.close();
    }
}
