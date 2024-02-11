package ukecat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * The Storage class manages the storage of tasks and related information for the UkeCat application.
 * It includes methods for adding tasks and accessing the list of tasks.
 */
public class Storage {
    public static String input;
    public static String[] words;
    public static String desc;
    public static LocalDate by;
    public static LocalDate start;
    public static LocalDate end;

    public static int taskCount = 0;
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task based on the user input and parsed information.
     * Handles different task types (ToDo, Deadline, Event).
     *
     * @return A string message indicating the result of the addition.
     */
    public static String addTask() {
        assert words != null : "Words array must not be null.";
        assert words.length > 0 : "Words array must have at least one element.";
        assert desc != null: "Every task must have a description.";

        Task t;

        try {
            switch (words[0]) {
            case "todo":
                Parser.parseToDo(input);
                t = new ToDo(desc);
                tasks.add(t);
                break;
            case "deadline":
                Parser.parseDeadline(input);
                t = new Deadline(desc, by);
                tasks.add(t);
                break;
            case "event":
                Parser.parseEvent(input);
                t = new Event(desc, start, end);
                tasks.add(t);
                break;
            default:
                return "Invalid task type. Please provide a valid task type (todo, deadline, event).";
            }
            taskCount++;
            String message = String.format("  Task added: %s%n%s", t, generateReport());
            FileManager.updateTasks();
            return message;
        } catch (Exception e) {
            return "Error adding task: " + e.getMessage();
        }
    }

    /**
     * Deletes a task based on the user input and updates the task list.
     * Handles exceptions such as UkeCatException and checks for IndexOutOfBoundsException.
     *
     * @return A string message indicating the result of the deletion.
     */
    public static String deleteTask() {
        try {
            int deleteIndex = Parser.parseDeleteTask(words);

            // Guard case: Check if the deleteIndex is within the valid range
            boolean isValidIndex = deleteIndex < 0 || deleteIndex >= tasks.size();

            if (isValidIndex) {
                return "Task not found. Please delete a valid task from the list:\n" + displayTasks();
            }

            Task deletedTask = tasks.remove(deleteIndex);
            taskCount--;
            FileManager.updateTasks();
            return String.format("  Task removed: %s%n%s", deletedTask, generateReport());
        } catch (UkeCatException e) {
            return e.getMessage();
        }
    }

    /**
     * Marks a task as done or not done based on the user input and updates the task list.
     * Handles exceptions such as UkeCatException and IndexOutOfBoundsException.
     *
     * @param markType The type of marking (MarkType.MARK for done, MarkType.UNMARK for not done).
     * @return A string message indicating the result of the marking operation.
     */
    public static String markTask(MarkType markType) {
        try {
            int taskIndex = Parser.parseMarkTask(words);
            return tasks.get(taskIndex).setDone(markType);
        } catch (UkeCatException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "Task not found. Please mark a valid task from the list:\n" + displayTasks();
        }
    }

    /**
     * Finds tasks in the list containing a specified keyword.
     *
     * @return A string message indicating the result of the search.
     */
    public static String findTask() {
        try {
            String keyword = Parser.parseFindTask(words);

            if (taskCount == 0) {
                return "No tasks in the list yet!";
            }

            List<Task> matchingTasks = tasks.stream()
                    .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                    .collect(Collectors.toList());

            if (matchingTasks.isEmpty()) {
                return "No tasks match the keyword.";
            } else {
                StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
                matchingTasks.forEach(task ->
                        result.append(String.format("%d. %s%n", matchingTasks.indexOf(task) + 1, task.toString())));
                return result.toString();
            }
        } catch (UkeCatException e) {
            return e.getMessage();
        }
    }

    public static void addCsvTask() {
        Task t = createTaskFromCsv();
        if (t != null) {
            tasks.add(t);
            taskCount++;
        }
    }

    /**
     * Creates a task based on the CSV input and returns the task.
     *
     * @return The created task.
     */
    private static Task createTaskFromCsv() {
        Task t = null;
        try {
            TaskStatus status = TaskStatus.valueOf(words[1]);

            switch (words[0]) {
            case "todo":
                Parser.parseToDo(input);
                t = new ToDo(status, desc);
                break;

            case "deadline":
                Parser.parseDeadline(input);
                t = new Deadline(status, desc, by);
                break;

            case "event":
                Parser.parseEvent(input);
                t = new Event(status, desc, start, end);
                break;

            default:
                System.out.println("Unknown task type: " + words[0]);
            }
        } catch (UkeCatException e) {
            System.out.println(e.getMessage());
        }
        return t;
    }

    /**
     * Loads a task from CSV input, parses it, and adds it to the task list.
     *
     * @param csv The CSV input representing a task.
     */
    public static void loadTask(String csv) {
        Parser.parseCsv(csv);
        addCsvTask();
    }

    /**
     * Displays the list of tasks.
     * If no tasks are present, a message indicating an empty list is displayed.
     *
     * @return A string representation of the tasks.
     */
    public static String displayTasks() {
        if (taskCount == 0) {
            return "  No tasks in the list yet!";
        }
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < taskCount; i++) {
            listOfTasks.append(String.format("  %d. %s%n", i + 1, tasks.get(i).toString()));
        }
        return listOfTasks.toString();
    }

    /**
     * Generates a report indicating the number of tasks in the list.
     *
     * @return A string representation of the report.
     */
    public static String generateReport() {
        return String.format("  You have %d task%s in the list now.", taskCount, taskCount == 1 ? "" : "s");
    }
}
