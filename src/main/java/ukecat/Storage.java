package ukecat;

import java.util.ArrayList;
import java.time.LocalDate;


/**
 * The Storage class manages the storage of tasks and related information for the UkeCat application.
 * It includes methods for adding tasks and accessing the list of tasks.
 */
public class Storage {
    // ukecat.Storage from input
    public static String input; // user input
    public static String[] words; // split words using
    public static String desc; // for any task
    public static LocalDate by; // for deadlines
    public static LocalDate start; // for events
    public static LocalDate end; // for events

    private static final ArrayList<Task> tasks = new ArrayList<>();
    public static int numT = 0;

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Adds a task based on the user input and parsed information.
     * Handles different task types (ToDo, Deadline, Event).
     */
    public static void addTask() {
        Task t = null;
        try{
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
            numT++;
            System.out.println("  I added this task: " + t);
            report();
            FileManager.updateTasks();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes a task based on the user input and updates the task list.
     * Handles exceptions such as UkeCatException and IndexOutOfBoundsException.
     */
    public static void deleteTask() {
        try {
            int deleteIndex = Parser.parseDeleteTask(words);
            Task deletedTask = tasks.remove(deleteIndex);
            numT--;
            System.out.println("  I removed this task: " + deletedTask);
            report();
            FileManager.updateTasks();
        } catch (UkeCatException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task not found. Please delete task from list:");
            printTasks();
        }
    }

    /**
     * Marks a task as done or not done based on the user input and updates the task list.
     * Handles exceptions such as UkeCatException and IndexOutOfBoundsException.
     *
     * @param markType The type of marking (MARK for done, UNMARK for not done).
     */
    public static void markTask(MarkType markType) {
        try {
            int taskIndex = Parser.parseMarkTask(words);
            tasks.get(taskIndex).setDone(markType);
            FileManager.updateTasks();
        } catch (UkeCatException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task not found. Please mark a valid task from the list:");
            printTasks();
        }
    }

    /**
     * Adds a task based on the CSV input and updates the task list.
     * Handles exceptions for parsing different task types (ToDo, Deadline, Event).
     *
     * <p>Assumes that words[1] is "0" or "1" representing the task's done status.
     */
    public static void addCsvTask() {
        Task t;
        try{
            switch (words[0]) {
            case "todo":
                Parser.parseToDo(input);
                t = new ToDo(words[1], desc);
                tasks.add(t);
                break;

            case "deadline":
                Parser.parseDeadline(input);
                t = new Deadline(words[1], desc, by);
                tasks.add(t);
                break;

            case "event":
                Parser.parseEvent(input);
                t = new Event(words[1], desc, start, end);
                tasks.add(t);
                break;
            }
            numT++;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
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
     * Prints the list of tasks to the console.
     * If no tasks are present, a message indicating an empty list is displayed.
     */
    public static void printTasks() {
        if (numT == 0) {
            System.out.println("  No tasks in the list yet!");
            return;
        }
        for (int i = 0; i < numT; i++) {
            System.out.format("  %d. %s%n", i + 1, tasks.get(i).toString());
        }
    }

    /**
     * Displays a report indicating the number of tasks in the list.
     */
    public static void report() {
        String out = String.format("  You have %d task%s in the list now.", numT, numT == 1 ? "" : "s");
        System.out.println(out);
    }
}
