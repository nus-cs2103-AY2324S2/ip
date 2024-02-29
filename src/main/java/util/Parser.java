package util;

import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import exceptions.ChillChiefException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * A parser class to parse user input and executing corresponding actions.
 */
public class Parser {

    /**
     * Parses the user input and performs corresponding actions.
     *
     * @param userInput The text input by the user.
     * @param tasks     The TaskList containing tasks.
     * @param textUi    The TextUi for user interface interactions.
     * @param storage   The Storage for saving and loading tasks to and from file.
     * @return The string message based on the user input.
     * @throws ChillChiefException If user input is invalid.
     * @throws IOException         if user input is invalid.
     */
    public static String parseInput(String userInput, TaskList tasks, TextUi textUi, Storage storage) throws ChillChiefException, IOException {
        String[] tokens = userInput.split(" ", 2);
        assert !tokens[0].isEmpty() : "userInput cannot have empty command keyword.";
        String command = tokens[0].toLowerCase();

        switch (command) {
        case "bye":
            storage.save(tasks.getAllTasks());
            return textUi.showOutroMessage();
        case "help":
            return textUi.showCommands();
        case "list":
        case "li":
            return textUi.showTaskList(tasks);
        case "todo":
        case "t":
            return handleTodo(tokens, tasks, textUi);
        case "deadline":
        case "d":
            return handleDeadline(tokens, tasks, textUi);
        case "event":
        case "e":
            return handleEvent(tokens, tasks, textUi);
        case "unmark":
            return handleUnmark(tokens, tasks, textUi);
        case "mark":
            return handleMark(tokens, tasks, textUi);
        case "delete":
        case "del":
            return handleDelete(tokens, tasks, textUi);
        case "find":
        case "f":
            return handleFind(tokens, tasks, textUi);
        default:
            try {
                throw new ChillChiefException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (ChillChiefException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static String handleFind(String[] tokens, TaskList taskList, TextUi textUi) {
        assert tokens != null : "Tokens cannot be null";
        int count = 1;
        StringBuilder result = new StringBuilder();

        // extract the keyword to match with existing tasks' descriptions.
        String keyword = tokens[1].trim();
        ArrayList<Task> tasks = taskList.getAllTasks();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                result.append("  ").append(count).append(".").append(task).append("\n");
                count++;
            }
        }

        return textUi.showFindMessage(String.valueOf(result));
    }

    private static LocalDateTime parseDate(String dateString) throws ChillChiefException {
        assert dateString != null && !dateString.isEmpty() : "dateString should not be null or empty";

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new ChillChiefException("Error Parsing!");
        }

    }

    private static String handleTodo(String[] tokens, TaskList tasks, TextUi textUi) {
        assert tokens != null : "Tokens cannot be null";
        String description = tokens[1].trim();
        Todo todoTask = new Todo(description, false);
        tasks.addToTaskList(todoTask);
        return textUi.showTaskAdded(todoTask, tasks.getTaskListLength());
    }

    private static String handleDeadline(String[] tokens, TaskList tasks, TextUi textUi) throws ChillChiefException {
        assert tokens != null : "Tokens cannot be null";
        String descriptionAndBy = tokens[1].trim();
        String deadlineDescription = descriptionAndBy.split(" /by ")[0].trim();
        String when = descriptionAndBy.split(" /by ")[1].trim();
        LocalDateTime by = parseDate(when);
        Deadline deadlineTask = new Deadline(deadlineDescription, false, by);
        tasks.addToTaskList(deadlineTask);
        return textUi.showTaskAdded(deadlineTask, tasks.getTaskListLength());
    }

    private static String handleEvent(String[] tokens, TaskList tasks, TextUi textUi) throws ChillChiefException {
        assert tokens != null : "Tokens cannot be null";
        String descriptionAndStartAndEnd = tokens[1].trim();
        String[] parts = descriptionAndStartAndEnd.split(" /from | /to ");
        String eventDescription = parts[0].trim();
        LocalDateTime start = parseDate(parts[1].trim());
        LocalDateTime end = parseDate(parts[2].trim());
        Event eventTask = new Event(eventDescription, false, start, end);
        tasks.addToTaskList(eventTask);
        return textUi.showTaskAdded(eventTask, tasks.getTaskListLength());
    }

    private static String handleUnmark(String[] tokens, TaskList tasks, TextUi textUi) throws ChillChiefException {
        assert tokens != null && tokens.length > 2 : "Invalid command format for unmark";
        int index = Integer.parseInt(tokens[1]) - 1;
        Task taskToUnMark = tasks.getTask(index);
        tasks.getTask(index).markNotDone();
        return textUi.showMarkedOrUnmarkMessage(taskToUnMark);
    }

    private static String handleMark(String[] tokens, TaskList tasks, TextUi textUi) throws ChillChiefException {
        assert tokens != null && tokens.length > 2 : "Invalid command format for mark";
        int index = Integer.parseInt(tokens[1]) - 1;
        Task taskToMark = tasks.getTask(index);
        taskToMark.markDone();
        return textUi.showMarkedOrUnmarkMessage(taskToMark);
    }

    private static String handleDelete(String[] tokens, TaskList tasks, TextUi textUi) throws ChillChiefException {
        assert tokens != null : "Tokens cannot be null";
        int index = Integer.parseInt(tokens[1]) - 1;
        assert index < tasks.getTaskListLength() : "Index for task to delete is out of bounds";
        Task taskToDelete = tasks.getTask(index);
        tasks.deleteTask(index);
        return textUi.showDeletedTask(taskToDelete, tasks.getTaskListLength());
    }


    /**
     * Parses line from a file with tasks saved in file format, line by line.
     *
     * @param fileString The string representation of a task read from the file.
     * @return The corresponding Task object from the input string.
     * @throws ChillChiefException If tasks are formatted incorrectly in the file.
     */
    public static Task parseFileFormattedTask(String fileString) throws ChillChiefException {
        String[] splitFileString = fileString.split("\\|");
        // T|1|description
        // D|1|description|yyyy-MM-dd_HH:mm
        // E|1|description|yyyy-MM-dd_HH:mm_to_yyyy-MM-dd_HH:mm
        String taskType = splitFileString[0].trim(); // T D OR E
        String taskDescription = splitFileString[2].trim(); // description
        String when = null;

        if (splitFileString.length > 3) {
            when = splitFileString[3]; // yyyy-MM-dd_HH:mm or yyyy-MM-dd_HH:mm_to_yyyy-MM-dd_HH:mm
        }
        boolean isDone = splitFileString[1].trim().equals("1");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        switch(taskType) {
        case "T":
            return new Todo(taskDescription, isDone);
        case "D":
            assert when != null;
            LocalDateTime by = LocalDateTime.parse(when, formatter);
            return new Deadline(taskDescription, isDone, by);
        case "E":
            assert when != null;
            String[] sections = when.split("to");
            String start = sections[0].trim(); // yyyy-MM-dd_HH:mm
            String end = sections[1].trim(); //yyyy-MM-dd_HH:mm
            LocalDateTime startTime = LocalDateTime.parse(start, formatter);
            LocalDateTime endTime = LocalDateTime.parse(end, formatter);
            return new Event(taskDescription, isDone, startTime, endTime);
        default:
            throw new ChillChiefException("Error parsing saved tasks!!");
        }
    }
}
