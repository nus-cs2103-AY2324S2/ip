package Dude;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The Dude class is the entry point of the application that manages tasks.
 * It is responsible for initializing the system, loading existing tasks from a file,
 * and processing user commands.
 */
public class Dude {
    TaskList tasks;
    Ui ui = new Ui();

    public Dude() {
        try {
            tasks = new TaskList(TaskStorage.loadTasksFromFile());
        } catch (IOException e) {
            tasks = new TaskList(new ArrayList<>());
        }

    }



    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command cmd = Parser.getCommand(input);
            int index;

            switch (cmd.action){
                case BYE:
                    return ui.showGoodbye();
                case LIST:
                    return ui.showTaskList(tasks.toArrayList());
                case DONE:
                    index = Parser.getDoneIndex(input);

                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    tasks.get(index).markAsDone();
                    return ui.showDone(tasks.get(index));
                case UNDONE:
                    index = Parser.getDoneIndex(input);

                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    tasks.get(index).markAsNotDone();
                    return ui.showUndone(tasks.get(index));
                case DELETE:
                    index = Parser.getDeleteIndex(input);
                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number does not exist.");
                    }
                    Task removedTask = tasks.remove(index);
                    return ui.showDelete(removedTask);
                case FIND:
                    String keyword = cmd.info;
                    ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
                    return ui.showTaskList(matchingTasks);
                case TODO:
                    ToDo todo = new ToDo(cmd.info);
                    tasks.add(todo);
                    return ui.showAddTask(todo);
                case DEADLINE:
                    if (!cmd.info.contains(" /by ")) {
                        throw new IllegalArgumentException(
                                "Invalid deadline format. Use '/by' to specify the deadline.");
                    }
                    String[] deadlineParts = cmd.info.split(" /by ", 2);
                    Deadline deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                    tasks.add(deadline);
                    return ui.showAddTask(deadline);
                case EVENT:
                    if (!cmd.info.contains(" /from ") || !cmd.info.contains(" /to ")) {
                        throw new IllegalArgumentException(
                                "Invalid event format. Use '/from' and '/to' to specify the event times.");
                    }
                    String[] eventParts = cmd.info.split(" /from ", 2);
                    String[] eventTimes = eventParts[1].split(" /to ", 2);
                    Event newEvent = new Event(eventParts[0], eventTimes[0], eventTimes[1]);
                    tasks.add(newEvent);
                    return ui.showAddTask(newEvent);
            }

            TaskStorage.saveTasksToFile(tasks.toArrayList());
        } catch (NumberFormatException e) {
            return ui.showError("Please enter a valid task number.");
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            return ui.showError(e.getMessage());
        } catch (Exception e) {
            return ui.showError("An unexpected error occurred: " + e.getMessage());
        }
        return "";
    }

}

// Base Task class
/**
 * Abstract class representing the structure and functionality of a task.
 * This class serves as a base for different types of tasks that can be created, tracked, and modified.
 * Each task has a description and a status indicating whether it is done.
 */
abstract class Task {
    /**
     * The descriptive text of the task.
     */
    String description;

    /**
     * The completion status of the task.
     */
    boolean isDone;

    /**
     * Constructs a new Task with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done by setting the isDone field to true.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done by setting the isDone field to false.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task,
     * which includes the description and completion status.
     *
     * @return A string representation of the task.
     */
    @Override
    public abstract String toString();

    public abstract String toFileString();
}


// ToDo subclass
/**
 * Represents a ToDo task with a description.
 * Inherits from the Task class.
 */
class ToDo extends Task {
    /**
     * Constructs a new ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task,
     * including its type, completion status, and description.
     *
     * @return A string representation of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + description;
    }

    @Override
    public String toFileString() {
        return "T|" + (isDone ? "1" : "0") + "|" + description;

    }
}

/**
 * Represents a Deadline task with a description and a due date.
 * Inherits from the Task class.
 */
class Deadline extends Task {
    /**
     * The due date of the Deadline task.
     */
    LocalDate by;

    /**
     * Constructs a new Deadline task with the specified description and due date.
     *
     * @param description The description of the Deadline task.
     * @param by          The due date of the task in the format "yyyy-MM-dd".
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(by, formatter);
    }

    /**
     * Returns a string representation of the Deadline task,
     * including its type, completion status, description, and due date.
     *
     * @return A string representation of the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        String formattedDate = by.format(formatter);
        return "[D]" + (isDone ? "[X] " : "[ ] ") + description + " (by: " + formattedDate + ")";
    }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String byDate = by.format(formatter);
        return "D|" + (isDone ? "1" : "0") + "|" + description + "|" + byDate;
    }
}


/**
 * Represents an Event task with a description, start date, and end date.
 * Inherits from the Task class.
 */
class Event extends Task {
    LocalDate start;
    LocalDate end;
    /**
     * Constructs a new Event task with the specified description, start date, and end date.
     *
     * @param description The description of the Event task.
     * @param start       The start date of the event in the format "yyyy-MM-dd".
     * @param end         The end date of the event in the format "yyyy-MM-dd".
     */
    public Event(String description, String start, String end) {
        super(description);
        // Parse the strings to LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.start = LocalDate.parse(start, formatter);
        this.end = LocalDate.parse(end, formatter);
    }

    /**
     * Returns a string representation of the Event task,
     * including its type, completion status, description, start date, and end date.
     *
     * @return A string representation of the Event task.
     */
    @Override
    public String toString() {
        // Define a formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
        // Format the LocalDate to the desired format
        String formattedStart = start.format(formatter);
        String formattedEnd = end.format(formatter);
        return "[E]" + (isDone ? "[X] " : "[ ] ") + description + " (from: " + formattedStart + " to: " + formattedEnd
                + ")";
    }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String startDate = start.format(formatter);
        String endDate = end.format(formatter);
        return "E|" + (isDone ? "1" : "0") + "|" + description + "|" + startDate + "|" + endDate;
    }
}

/**
 * Provides functionality to save and load tasks to and from a file.
 * This class handles the conversion of Task objects into a string format suitable for file storage
 * and the parsing of these strings back into Task objects.
 */
class TaskStorage {
    public static final String FILE_NAME = "tasks.txt";

    /**
     * Saves a list of tasks to a file, converting each task to a string format.
     * Each task is written on a new line in the file.
     *
     * @param tasks The list of Task objects to be saved.
     * @throws IOException If an I/O error occurs writing to the file.
     */
    public static void saveTasksToFile(ArrayList<Task> tasks) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME));
        for (Task task : tasks) {
            String taskString = task.toFileString();
            if (!taskString.isEmpty()) {
                bufferedWriter.write(taskString);
                bufferedWriter.newLine();
            }
        }

    }

    /**
     * Loads a list of tasks from a file, converting each line of the file back into a Task object.
     *
     * @return An ArrayList of Task objects read from the file.
     * @throws IOException If an I/O error occurs reading from the file.
     */
    public static ArrayList<Task> loadTasksFromFile() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Task task = taskFromString(line);
            if (task != null) {
                tasks.add(task);
            }
        }

        return tasks;
    }




    /**
     * Converts a string representation of a task back into a Task object.
     * The string format should include task type, completion status, description, and dates if applicable.
     *
     * @param line The string representation of the task.
     * @return A Task object corresponding to the string, or null if the string format is invalid.
     */
    private static Task taskFromString(String line) {
        String[] parts = line.split("\\|");
        boolean isDone = "1".equals(parts[1]);
        String description = parts[2];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            switch (parts[0]) {
            case "T":
                ToDo todo = new ToDo(description);
                if (isDone) todo.markAsDone();
                return todo;
            case "D":
                Deadline deadline = new Deadline(description, parts[3]);
                if (isDone) deadline.markAsDone();
                return deadline;
            case "E":
                Event event = new Event(description, parts[3], parts[4]);
                if (isDone) event.markAsDone();
                return event;
            default:
                return null;
            }
        } catch (DateTimeParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
            return null;
        }
    }
}

class Ui {
    private Scanner scanner;

    public Ui() {
    }

    public String showWelcome() {
        return "Hello! I'm Dude\nWhat can I do for you?";
    }

    public String showAddTask(Event event){
        return "Added Event: " + event;
    }

    public String showAddTask(Deadline deadline) {
        return "Added Deadline: " + deadline;
    }

    public String showAddTask(ToDo todo) {
        return "Added Todo: " + todo;
    }

    public String showDelete(Task task) {
        return "Deleted: "  + task;
    }

    public String showDone(Task task) {
        return "Marked as Done: " + task;
    }

    public String showUndone(Task task) {
        return "Marked as Not Done: " + task;
    }


    public String showGoodbye() {
        return("Bye. Hope to see you again soon!");
    }

    public String showError(String message) {
        return("Error: " + message);
    }

    public String showMessage(String message) {
        return(message);
    }

    public String showTaskList(ArrayList<Task> tasks) {
        StringBuilder s = new StringBuilder();

        if (tasks.isEmpty()) {
            return ("No Tasks to show.");
        }

        for (int i = 0; i < tasks.size(); i++) {
            s.append((i + 1)).append(". ").append(tasks.get(i)).append("\n");
        }
        return s.toString();
    }
}

enum Actions {
    DELETE,
    ADD,
    DONE,
    UNDONE,
    EVENT,
    DEADLINE,
    TODO,
    LIST,
    BYE,
    FIND
}

/**
 * Represents a command issued by the user, encapsulating an action and optional additional information.
 */
class Command {
    public Actions action;

    public String info;

    public Command(Actions action, String info) {
        this.action = action;
        this.info = info;
    }

    public Command(Actions action) {
        this.action = action;
    }

}

/**
 * A utility class to parse input strings into Command objects.
 * This class interprets user inputs and translates them into actionable commands
 * for the application to execute.
 */
 class Parser {

    /**
     * Parses the user input into a Command object.
     * This method identifies the action requested by the user and any associated information.
     *
     * @param input The user input as a String.
     * @return A Command object representing the action to be performed.
     * @throws RuntimeException If the input does not match any known command patterns.
     */
    public static Command getCommand(String input) {
        if (Parser.isBye(input)) {
            return new Command(Actions.BYE);
        }
        if (Parser.isList(input)) {
            return new Command(Actions.LIST);
        }
        if (input.startsWith("done ")) {
            return new Command(Actions.DONE);
        }
        if (input.startsWith("undo ")) {
            return new Command(Actions.UNDONE);
        }
        if (input.startsWith("delete ")) {
            return new Command(Actions.DELETE);
        }

        if (input.startsWith("find ")) {
            return new Command(Actions.FIND, input.substring(5).trim());
        }

        String[] parts = input.split(" ", 2);
        String command = parts[0];
        String taskInfo = parts.length > 1 ? parts[1] : "";

        switch (command.toLowerCase()) {
        case "todo":
            return new Command(Actions.TODO, taskInfo);
         case "deadline":
             return new Command(Actions.DEADLINE, taskInfo);
         case "event":
             return new Command(Actions.EVENT, taskInfo);
         default:
             throw new RuntimeException("Invalid input. Try again!");
        }

    }
    public static boolean isBye(String input) {
        return input.equalsIgnoreCase("bye");
    }


    public static boolean isList(String input) {
        return input.equalsIgnoreCase("list");
    }

    /**
     * Extracts the task index from a done command input.
     *
     * @param input The user input as a String.
     * @return The index of the task to be marked as done.
     */
    public static int getDoneIndex(String input) {
        return Integer.parseInt(input.substring(5));
    }

    /**
     * Extracts the task index from a delete command input.
     *
     * @param input The user input as a String.
     * @return The index of the task to be deleted.
     */
    public static int getDeleteIndex(String input) {
        return Integer.parseInt(input.substring(7));
    }

}


class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> startingTasks) {
        this.tasks = startingTasks;
    }
    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public void markAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> toArrayList() {
        return tasks;
    }

    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}

