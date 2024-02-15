package secretaryw;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Main class for the SecretaryW application.
 */
public class SecretaryW {
    private static final String FILE_PATH = "./data/SecretaryW.txt";
    private static final String line = "-----------------------------------------------------------------\n";
    private Parser parser;
    private Storage storage;
    private Ui ui;
    private TaskList taskList;

    /**
     * Constructs a new SecretaryW object.
     */
    public SecretaryW() {
        this.parser = new Parser();
        this.storage = new Storage(FILE_PATH);
        this.ui = new Ui();
        try {
            ArrayList<Task> tasks = storage.loadTasksFromFile();
            this.taskList = new TaskList(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
            this.taskList = new TaskList();
        }
    }

    /**
     * Starts the SecretaryW application.
     */
    public void run() {
        ui.showGreeting();

        while (true) {
            try {
                String[] command = parser.getNextCommand();
                switch (command[0]) {
                    case "bye":
                        ui.showFarewell();
                        storage.saveTasksToFile(taskList.getTasks());
                        parser.closeScanner();
                        return;
                    case "list":
                        ui.showTasks(taskList.getTasks());
                        break;
                    case "mark":
                        markTaskAsDone(command[1]);
                        break;
                    case "unmark":
                        markTaskAsUndone(command[1]);
                        break;
                    case "delete":
                        deleteTask(command[1]);
                        break;
                    case "todo":
                        addTodoTask(command[1]);
                        break;
                    case "deadline":
                        addDeadlineTask(command[1]);
                        break;
                    case "event":
                        addEventTask(command[1]);
                        break;
                    default:
                        throw new WException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (WException | IOException e) {
                ui.showMessage("OOPS!!! " + e.getMessage());
            }
        }
    }

    /**
     * Marks a task as done based on the specified index.
     *
     * @param argument The index of the task to mark as done.
     * @throws WException If the index is out of bounds.
     */
    private void markTaskAsDone(String argument) throws WException {
        int index = Integer.parseInt(argument.trim()) - 1;
        checkIndexBounds(index);
        taskList.getTasks().get(index).markAsDone();
        ui.showMessage("Nice! I've marked this task as done:\n  " +
                taskList.getTasks().get(index).getStatusIcon() + " " +
                taskList.getTasks().get(index).getDescription());
    }

    /**
     * Deletes a task based on the specified index.
     *
     * @param argument The index of the task to delete.
     * @throws WException If the index is out of bounds.
     */
    private void markTaskAsUndone(String argument) throws WException {
        int index = Integer.parseInt(argument.trim()) - 1;
        checkIndexBounds(index);
        taskList.getTasks().get(index).markAsUndone();
        ui.showMessage("OK, I've marked this task as not done yet:\n  " +
                taskList.getTasks().get(index).getStatusIcon() + " " +
                taskList.getTasks().get(index).getDescription());
    }

    /**
     * Deletes a task based on the specified index.
     *
     * @param argument The index of the task to delete.
     * @throws WException If the index is out of bounds.
     */
    private void deleteTask(String argument) throws WException {
        int index = Integer.parseInt(argument.trim()) - 1;
        checkIndexBounds(index);
        Task removedTask = taskList.getTasks().remove(index);
        ui.showMessage("Noted. I've removed this task:\n  " + removedTask);
    }

    /**
     * Adds a todo task with the specified description.
     *
     * @param argument The description of the todo task.
     * @throws WException If the description is empty.
     */
    private void addTodoTask(String argument) throws WException {
        if (argument.isEmpty()) {
            throw new WException("The description of a todo cannot be empty");
        }
        taskList.addTask(new Task(TaskType.TODO, argument));
        ui.showTaskAdded(taskList.getTasks().get(taskList.size() - 1), taskList.size());
    }

    /**
     * Adds a deadline task with the specified description and deadline.
     *
     * @param argument The description and deadline of the deadline task.
     * @throws WException If the format of the deadline is incorrect.
     */
    private void addDeadlineTask(String argument) throws WException {
        String[] parts = argument.split("/by");
        if (parts.length != 2) {
            throw new WException("Wrong format. Please retype");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        checkDeadline(by);
        taskList.addTask(new Task(TaskType.DEADLINE, description, by));
        ui.showTaskAdded(taskList.getTasks().get(taskList.size() - 1), taskList.size());
    }

    /**
     * Adds an event task with the specified description and time period.
     *
     * @param argument The description and time period of the event task.
     * @throws WException If the format of the time period is incorrect.
     */
    private void addEventTask(String argument) throws WException {
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
        ui.showTaskAdded(taskList.getTasks().get(taskList.size() - 1), taskList.size());
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
    public static void main(String[] args) {
        new SecretaryW().run();
    }
}
