package secretaryw;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Objects;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * Handles the command entered by the user.
     *
     * @return String Representing the parsed command.
     */
    public String handleCommand(String[] command) {
        if (checkCommand(command)) {
            return ui.showMessage("Invalid Command");
        }
        if (!Objects.equals(command[0], "bye") && !Objects.equals(command[0], "list")
                && !Objects.equals(command[0], "help") && (command.length == 1)) {
            return ui.showMessage("Index or Description cannot be empty");
        }

        switch (command[0]) {
        case "bye":
            closeScanner();
            System.exit(0);
            break;
        case "help":
            return ui.showHelpMessage();
        case "list":
            return ui.showTasks(taskList.getTasks());
        case "mark":
            return markTaskAsDone(command[1]);
        case "unmark":
            return markTaskAsUndone(command[1]);
        case "delete":
            return deleteTask(command[1]);
        case "find":
            return findTask(command[1]);
        case "todo":
            return addTodoTask(command[1]);
        case "deadline":
            return addDeadlineTask(command[1]);
        case "event":
            return addEventTask(command[1]);
        default:
            return ui.showMessage("I'm sorry, but I don't know what that means :-(");
        }
        return "";
    }


    /**
     * Marks a task as done based on the specified index.
     *
     * @param argument The index of the task to mark as done.
     */
    private String markTaskAsDone(String argument) {
        assert (argument != null);
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
     */
    private String markTaskAsUndone(String argument) {
        assert (argument != null);
        int index = Integer.parseInt(argument.trim()) - 1;
        if (checkIndexBounds(index)) {
            return ui.showMessage("Index out of bounds");
        }
        taskList.getTasks().get(index).markAsUndone();
        return ui.showMessage("OK, I've marked this task as not done yet:\n  " +
                taskList.getTasks().get(index).getStatusIcon() + " " +
                taskList.getTasks().get(index).getDescription());
    }

    /**
     * Deletes a task based on the specified index.
     *
     * @param argument The index of the task to delete.
     */
    private String deleteTask(String argument) {
        assert (argument != null);
        int index = Integer.parseInt(argument.trim()) - 1;
        if (checkIndexBounds(index)) {
            return ui.showMessage("Index out of bounds");
        }
        Task removedTask = taskList.getTasks().remove(index);
        return ui.showMessage("Noted. I've removed this task:\n  " + removedTask);
    }

    /**
     * Adds a to do task with the specified description.
     *
     * @param argument The description of the to do task.
     */
    private String addTodoTask(String argument) {
        assert (argument != null);
        taskList.addTask(new Task(TaskType.TODO, argument));
        return ui.showTaskAdded(taskList.getTasks().get(taskList.size() - 1), taskList.size());
    }

    /**
     * Adds a deadline task with the specified description and deadline.
     *
     * @param argument The description and deadline of the deadline task.
     */
    private String addDeadlineTask(String argument) {
        assert (argument != null);
        String[] parts = argument.split("/by");
        if (parts.length != 2) {
            return "Wrong format. Please retype according to help";
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        if (checkDeadline(by)) {
            return ui.showMessage("Wrong date format. Please use d/mm/yyyy");
        }
        taskList.addTask(new Task(TaskType.DEADLINE, description, by));
        return ui.showTaskAdded(taskList.getTasks().get(taskList.size() - 1), taskList.size());
    }

    /**
     * Adds an event task with the specified description and time period.
     *
     * @param argument The description and time period of the event task.
     */
    private String addEventTask(String argument) {
        assert (argument != null);
        String[] parts = argument.split("/from");
        if (parts.length != 2) {
            return ui.showMessage("Wrong format. Please retype according to help");
        }
        String description = parts[0].trim();
        String[] time = parts[1].split("/to");
        if (time.length != 2) {
            return ui.showMessage("Wrong format. Please retype according to help");
        }
        String from = time[0].trim();
        String to = time[1].trim();
        if (checkEvent(from, to)) {
            ui.showMessage("Wrong format. Please use d/mm/yyyy");
        }
        taskList.addTask(new Task(TaskType.EVENT, description, from, to));
        return ui.showTaskAdded(taskList.getTasks().get(taskList.size() - 1), taskList.size());
    }

    private String findTask(String argument) {
        ArrayList<Task> matchingTasks = taskList.findTasks(argument);
        return ui.showMatchingTasks(matchingTasks);
    }

    /**
     * Checks if the specified index is within the bounds of the task list.
     *
     * @param index The index to check.
     */
    private Boolean checkIndexBounds(int index) {
        if (index < 0 || index >= taskList.size()) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the specified deadline string has the correct date format.
     *
     * @param by The deadline string to check.
     */
    private boolean checkDeadline(String by) {
        assert (by != null);
        try {
            LocalDate.parse(by, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            return true;
            //return "Wrong date format. Please use dd/mm/yyyy";
        }
        return false;
    }

    /**
     * Checks if the specified event start and end date strings have the correct date format.
     *
     * @param from The start date string of the event.
     * @param to   The end date string of the event.
     */
    private boolean checkEvent(String from, String to) {
        assert (from != null);
        assert (to != null);
        try {
            LocalDate.parse(from, DateTimeFormatter.ofPattern("d/M/yyyy"));
            LocalDate.parse(to, DateTimeFormatter.ofPattern("d/M/yyyy"));
        } catch (DateTimeParseException e) {
            return true;
            //return "Wrong date format. Please use dd/mm/yyyy";
        }
        return false;
    }

    private boolean checkCommand(String[] command) {
        List<String> commands = Arrays.asList("bye", "list", "todo", "deadline", "event", "find", "mark", "unmark",
                "delete", "list", "help");
        if (commands.contains(command[0])) {
            return false;
        } else {
            return true;
        }
    }
    public void closeScanner() {
        scanner.close();
    }
}
