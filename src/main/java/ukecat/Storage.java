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
    private static String input;
    private static String[] words;
    private static String desc;
    private static LocalDate by;
    private static LocalDate start;
    private static LocalDate end;

    private static RecurType recurType;
    private static LocalDate nextRefresh;
    private static int taskCount = 0;
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
        assert getWords() != null : "Words array must not be null.";
        assert getWords().length > 0 : "Words array must have at least one element.";
        assert getDesc() != null : "Every task must have a description.";

        Task t;

        try {
            switch (getWords()[0]) {
            case "todo":
                Parser.parseToDo(getInput());
                t = new ToDo(getDesc());
                tasks.add(t);
                break;
            case "deadline":
                Parser.parseDeadline(getInput());
                t = new Deadline(getDesc(), getBy());
                tasks.add(t);
                break;
            case "event":
                Parser.parseEvent(getInput());
                t = new Event(getDesc(), getStart(), getEnd());
                tasks.add(t);
                break;
            case "recur":
                Parser.parseRecurTask(getInput());
                System.out.println(Storage.getNextRefresh());
                t = new RecurTask(getDesc(), getRecurType(), getNextRefresh());
                tasks.add(t);
                break;
            default:
                return "Invalid task type. Please provide a valid task type (todo, deadline, event, recur).";
            }
            setTaskCount(getTaskCount() + 1);
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
            int deleteIndex = Parser.parseDeleteTask(getWords());

            // Guard case: Check if the deleteIndex is within the valid range
            boolean isValidIndex = deleteIndex < 0 || deleteIndex >= tasks.size();

            if (isValidIndex) {
                return "Task not found. Please delete a valid task from the list:\n" + displayTasks();
            }

            Task deletedTask = tasks.remove(deleteIndex);
            setTaskCount(getTaskCount() - 1);
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
            int taskIndex = Parser.parseMarkTask(getWords());
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
            String keyword = Parser.parseFindTask(getWords());

            if (getTaskCount() == 0) {
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

    /**
     * Adds a task to the task list based on CSV input.
     * The CSV input is parsed to create a task using {@link #createTaskFromCsv()}.
     * If the task creation is successful, it is added to the task list, and the task count is incremented.
     */
    public static void addCsvTask() {
        Task t = createTaskFromCsv();
        if (t != null) {
            tasks.add(t);
            setTaskCount(getTaskCount() + 1);
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
            TaskStatus status = TaskStatus.valueOf(getWords()[1]);

            switch (getWords()[0]) {
            case "todo":
                Parser.parseToDo(getInput());
                t = new ToDo(status, getDesc());
                break;

            case "deadline":
                Parser.parseDeadline(getInput());
                t = new Deadline(status, getDesc(), getBy());
                break;

            case "event":
                Parser.parseEvent(getInput());
                t = new Event(status, getDesc(), getStart(), getEnd());
                break;
            case "recur":
                Parser.parseRecurTask(getInput());
                t = new RecurTask(status, getDesc(), getRecurType(), getNextRefresh());
                break;
            default:
                System.out.println("Unknown task type: " + getWords()[0]);
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
        if (getTaskCount() == 0) {
            return "  No tasks in the list yet!";
        }
        StringBuilder listOfTasks = new StringBuilder();
        for (int i = 0; i < getTaskCount(); i++) {
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
        return String.format("  You have %d task%s in the list now.", getTaskCount(), getTaskCount() == 1 ? "" : "s");
    }

    public static LocalDate getEnd() {
        return end;
    }

    public static void setEnd(LocalDate end) {
        Storage.end = end;
    }

    public static LocalDate getStart() {
        return start;
    }

    public static void setStart(LocalDate start) {
        Storage.start = start;
    }

    public static LocalDate getBy() {
        return by;
    }

    public static void setBy(LocalDate by) {
        Storage.by = by;
    }

    public static String getDesc() {
        return desc;
    }

    public static void setDesc(String desc) {
        Storage.desc = desc;
    }

    public static String[] getWords() {
        return words;
    }

    public static void setWords(String[] words) {
        Storage.words = words;
    }

    public static String getInput() {
        return input;
    }

    public static void setInput(String input) {
        Storage.input = input;
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public static void setTaskCount(int taskCount) {
        Storage.taskCount = taskCount;
    }

    public static RecurType getRecurType() {
        return recurType;
    }

    public static void setRecurType(String s) {
        switch (s) {
        case "day":
            Storage.recurType = RecurType.DAILY;
            break;
        case "week":
            Storage.recurType = RecurType.WEEKLY;
            break;
        case "month":
            Storage.recurType = RecurType.MONTHLY;
            break;
        default:
            System.out.println("Error setting recurType");
            break;
        }
    }

    public static LocalDate getNextRefresh() {
        return nextRefresh;
    }

    public static void setNextRefresh(LocalDate nextRefresh) {
        Storage.nextRefresh = nextRefresh;
    }
}

