package ukecat;

import java.util.ArrayList;
import java.time.LocalDate;

// Holds information to be used conveniently
// Contains:
// Parsed user input
// Array to hold tasks
public class Storage {
    // ukecat.Storage from input
    public static String input; // user input
    public static String[] words; // split words using
    public static String desc; // for any task
    public static LocalDate by; // for deadlines
    public static LocalDate start; // for events
    public static LocalDate end; // for events

    // Tasks
    private static final ArrayList<Task> tasks = new ArrayList<>();
    public static int numT = 0;

    public static ArrayList<Task> getTasks() {
        return tasks;
    }

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

    public static void markTask(MarkType markType) {
        try {
            int taskIndex = Parser.parseMarkTask(words);
            tasks.get(taskIndex).setDone(markType);
            FileManager.updateTasks();
        } catch (UkeCatException e) {
            System.out.println(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task not found. Please mark task from list:");
            printTasks();
        }
    }

    // words[1] is "0"/"1"
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

    public static void loadTask(String csv) {
        Parser.parseCsv(csv);
        addCsvTask();
    }

    public static void printTasks() {
        if (numT == 0) {
            System.out.println("  No tasks in list yet!");
            return;
        }
        for (int i = 0; i < numT; i++) {
            System.out.format("  %d. %s%n", i+1, tasks.get(i).toString());
        }
    }

    public static void report() {
        String out = String.format("  You have %s tasks in the list now.", numT);
        System.out.println(out);
    }
}
