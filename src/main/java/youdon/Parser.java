package youdon;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parses user input and performs corresponding actions.
 * This class handles parsing user input commands and executing actions accordingly,
 * such as adding tasks, marking tasks as done, and deleting tasks.
 */
public class Parser {

    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;

    /**
     * Constructs a new instance of the Parser class with the specified UI, task list, and storage.
     *
     * @param ui      The user interface for displaying messages.
     * @param tasks   The list of tasks to perform actions on.
     * @param storage The storage handler for saving and loading tasks.
     */
    public Parser(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Splits the input command and task number and returns the specified part.
     *
     * @param input      The input string to split.
     * @param partNumber The part number to retrieve (1 or 2).
     * @return The specified part of the input.
     */
    private String splitCommandAndTaskNumber(String input, int partNumber) {
        String[] parts = input.split(" ", 2);
        return parts[partNumber - 1];
    }

    /**
     * Splits the task description and deadline and returns the specified part.
     *
     * @param input      The input string to split.
     * @param partNumber The part number to retrieve (1 or 2).
     * @return The specified part of the input.
     */
    private String splitTaskDescAndDeadline(String input, int partNumber) {
        String[] parts = input.split("/by ");
        return parts[partNumber - 1];
    }

    /**
     * Splits the task description, start, and end times and returns the specified part.
     *
     * @param input      The input string to split.
     * @param partNumber The part number to retrieve (1, 2, or 3).
     * @return The specified part of the input.
     */
    private String splitTaskDescAndStartAndEnd(String input, int partNumber) {
        String[] parts = input.split("/from | /to ");
        return parts[partNumber - 1];
    }

    /**
     * Processes the 'find' command to search for tasks containing a specific keyword.
     *
     * @param task The keyword to search for.
     * @return A message containing the list of tasks found.
     */
    private String processFindCommand(String task) {
        TaskList foundList = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.toString().contains(task)) {
                foundList.add(currTask);
            }
        }
        return ui.getTaskList(foundList);
    }

    /**
     * Processes the 'mark' command to mark a task as done.
     *
     * @param task The task number to mark as done.
     * @return A message confirming the task marked as done.
     */
    private String processMarkCommand(String task) {
        // mark task as done in array
        int taskNumber = Integer.parseInt(task);
        tasks.get(taskNumber - 1).isDone = true;
        // save in save file
        try {
            this.storage.saveData(tasks);
            return ui.getMarkMsg(tasks, taskNumber) + "\n Tasklist saved!";
        } catch (IOException e) {
            return "Error!" + e.getMessage();
        }
    }

    /**
     * Processes the 'unmark' command to mark a task as undone.
     *
     * @param task The task number to mark as undone.
     * @return A message confirming the task marked as undone.
     */

    private String processUnmarkCommand(String task) {
        // mark task as undone in array
        int taskNumber = Integer.parseInt(task);
        tasks.get(taskNumber - 1).isDone = false;
        // save in save file
        try {
            this.storage.saveData(tasks);
            return ui.getUnmarkMsg(tasks, taskNumber) + "\n Tasklist saved!";
        } catch (IOException e) {
            return "Error!" + e.getMessage();
        }
    }

    /**
     * Processes the 'snooze' command to snooze a deadline task by one day.
     *
     * @param task The task number of the deadline task to snooze.
     * @return A message confirming the task snoozed.
     */
    private String processSnoozeCommand(String task) {
        int taskNumber = Integer.parseInt(task);
        Task taskToSnooze = tasks.get(taskNumber - 1);
        if (taskToSnooze instanceof Deadline) {
            ((Deadline) taskToSnooze).snoozeDeadline();
        }
        // save in save file
        try {
            this.storage.saveData(tasks);
            return ui.getSnoozeMsg(tasks, taskNumber) + "\n Tasklist saved!";
        } catch (IOException e) {
            return "Error!" + e.getMessage();
        }
    }

    /**
     * Processes the 'delete' command to delete a task from the task list.
     *
     * @param task The task number to delete.
     * @return A message confirming the task deletion.
     */
    private String processDeleteCommand(String task) {
        int taskNumber = Integer.parseInt(task);
        Task taskToDelete = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);

        try {
            storage.saveData(tasks);
            return ui.getDeleteMsg(taskToDelete) + "\n Tasklist saved!";
        } catch (IOException e) {
            return "Error!" + e.getMessage();
        }
    }

    /**
     * Processes the 'todo' command to add a todo task to the task list.
     *
     * @param task The description of the todo task.
     * @return A message confirming the todo task added.
     */
    private String processTodoCommand(String task) {
        tasks.add(new Todo(task));

        try {
            storage.saveData(tasks);
            return ui.getTaskAddedMsg(tasks) + "\n Tasklist saved!";
        } catch (IOException e) {
            return "Error!" + e.getMessage();
        }
    }

    /**
     * Processes the 'deadline' command to add a deadline task to the task list.
     *
     * @param taskDesc The description of the deadline task.
     * @param deadline The deadline of the task.
     * @return A message confirming the deadline task added.
     */
    private String processDeadlineCommand(String taskDesc, String deadline) {
        try {
            // attempt to parse the string into a LocalDateTime object
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTimeDeadline = LocalDateTime.parse(deadline, formatter);
            tasks.add(new Deadline(taskDesc, dateTimeDeadline));

            try {
                storage.saveData(tasks);
                return ui.getTaskAddedMsg(tasks) + "\n Tasklist saved!";
            } catch (IOException e) {
                return "Error!" + e.getMessage();
            }

        } catch (Exception e) {
            // handle the exception if the date or time is not the correct format
            return "Oh no! That's not a correct date or time format!";
        }
    }

    /**
     * Processes the 'event' command to add an event task to the task list.
     *
     * @param taskDesc The description of the event task.
     * @param start    The start time of the event.
     * @param end      The end time of the event.
     * @return A message confirming the event task added.
     */
    private String processEventCommand(String taskDesc, String start, String end) {
        try {
            // attempt to parse the strings into a LocalDateTime object
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime dateTimeStart = LocalDateTime.parse(start, formatter);
            LocalDateTime dateTimeEnd = LocalDateTime.parse(end, formatter);
            tasks.add(new Event(taskDesc, dateTimeStart, dateTimeEnd));

            try {
                storage.saveData(tasks);
                return ui.getTaskAddedMsg(tasks) + "\n Tasklist saved!";
            } catch (IOException e) {
                return "Error!" + e.getMessage();
            }

        } catch (Exception e) {
            // handle the exception if the date or time is not the correct format
            return "Oh no! That's not a correct date or time format!";
        }
    }

    /**
     * Parses user input and executes corresponding actions until an empty input is provided.
     *
     * @param input The user input to parse.
     * @return The response message based on the input command.
     */
    public String parse(String input) {
        assert !input.isEmpty();
        // try-catch block for exceptions
        try {
            YoudonException.detectMissingDesc(input);
            YoudonException.detectInvalidCommand(input);
        } catch (YoudonException.EmptyDescException | YoudonException.InvalidCommandException e) {
            // print out error message
            return ui.getYoudonErrorMsg(e.getMessage());
        }
        String command = input;
        String task = "";
        if (input.contains(" ")) {
            // if input data has 2 parts, split into command & task number
            command = splitCommandAndTaskNumber(input, 1);
            task = splitCommandAndTaskNumber(input, 2);
        }
        switch (command) {
            case "bye":
                return ui.getByeMsg();
            case "list":
                return ui.getTaskList(tasks);
            case "find":
                return processFindCommand(task);
            case "mark":
                return processMarkCommand(task);
            case "unmark":
                return processUnmarkCommand(task);
            case "snooze":
                return processSnoozeCommand(task);
            case "delete":
                return processDeleteCommand(task);
            case "todo":
                return processTodoCommand(task);
            case "deadline":
                if (task.contains("/by ")) {
                    String taskDescDeadline = splitTaskDescAndDeadline(task, 1);
                    String deadline = splitTaskDescAndDeadline(task, 2);
                    return processDeadlineCommand(taskDescDeadline, deadline);
                }
            case "event":
                if (task.contains("/from ")) {
                    String taskDescEvent = splitTaskDescAndStartAndEnd(task, 1);
                    String start = splitTaskDescAndStartAndEnd(task, 2);
                    String end = splitTaskDescAndStartAndEnd(task, 3);
                    return processEventCommand(taskDescEvent, start, end);
                }
        }
        return "Unknown Error :(";
    }
}
