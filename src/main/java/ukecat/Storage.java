package ukecat;

import java.time.LocalDate;
import java.util.ArrayList;


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

        Task t = null;
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
            }
            taskCount++;
            String message = String.format("  Task added: %s%n%s", t, generateReport());
            FileManager.updateTasks();
            return message;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Deletes a task based on the user input and updates the task list.
     * Handles exceptions such as UkeCatException and IndexOutOfBoundsException.
     *
     * @return A string message indicating the result of the deletion.
     */
    public static String deleteTask() {
        try {
            int deleteIndex = Parser.parseDeleteTask(words);
            Task deletedTask = tasks.remove(deleteIndex);
            taskCount--;
            FileManager.updateTasks();
            return String.format("  Task removed: %s%n%s", deletedTask, generateReport());
        } catch (UkeCatException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException e) {
            return "Task not found. Please mark a valid task from the list:\n" + displayTasks();
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
            ArrayList<Task> matchingTasks = new ArrayList<>();

            if (taskCount == 0) {
                return "No tasks in the list yet!";
            }

            for (Task task : tasks) {
                if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                    matchingTasks.add(task);
                }
            }

            if (matchingTasks.isEmpty()) {
                return "No tasks match the keyword.";
            } else {
                StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
                for (int i = 0; i < matchingTasks.size(); i++) {
                    result.append(String.format("%d. %s%n", i + 1, matchingTasks.get(i).toString()));
                }
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
