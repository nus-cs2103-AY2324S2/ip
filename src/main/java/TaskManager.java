import java.util.*;
import java.io.*;

/**
 * Manages all communications between the bot simulation and the database of
 * Tasks. Inspired by ChatGPT, but I simply just created this class and
 * transferred all my original code over from KBot.java
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class TaskManager {
    /** ArrayList<Task> to store all the Tasks the user has created. */
    private final ArrayList<Task> TASKS = new ArrayList<>();

    /**
     * Constructor for TaskManager
     */
    public TaskManager() {
    }

    /**
     * Stores the information in String into a Task and adds into the ArrayList
     * 
     * @param info The information from userInput
     */
    protected void addTask(String info, String ins) throws KException {
        Task t = null;
        switch (ins) {
            case "todo":
                t = new ToDo(info);
                break;
            case "deadline":
                String[] deadlineParts = info.split(" /by ", 2);
                if (deadlineParts.length == 1) { // if there is command but no input
                    throw new KException("Invalid parameters for " + ins);
                }
                String dName = deadlineParts[0];
                String dEndTime = deadlineParts[1];
                t = new Deadline(dName, dEndTime);
                break;
            case "event":
                String[] eventParts = info.split(" /from ", 2);
                if (eventParts.length == 1) { // if there is command but no input
                    throw new KException("Invalid parameters for " + ins);
                }
                String eName = eventParts[0];
                String time = eventParts[1];
                String[] timeParts = time.split(" /to ", 2);
                if (timeParts.length == 1) { // if there is command but no input
                    throw new KException("Invalid parameters for " + ins);
                }
                String startTime = timeParts[0];
                String endTime = timeParts[1];
                t = new Event(eName, startTime, endTime);
                break;
            default:
                break;
        }
        if (t != null) {
            TASKS.add(t);
            String response = "Got it. I've added this task:\n" + t + "\n" +
                    "Now you have " + TASKS.size() + " tasks in the list.";
            System.out.println(response);
        }
    }

    /**
     * Prints the Task List, labels them with numbers
     */
    protected void listTasks() {
        if (TASKS.size() == 0) {
            System.out.println("There are no tasks here. Please add a task!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < TASKS.size(); i++) {
                System.out.print((i + 1) + ".");
                System.out.println(TASKS.get(i));
            }
        }
    }

    /**
     * Marks a Task as completed.
     * 
     * @param index Index of Task to be marked in the TASKS ArrayList.
     */
    protected void markTask(int index) throws KException {
        if (index + 1 > TASKS.size() || index < 0) {
            throw new KException("Error: " + "Index out of range!");
        }
        Task t = TASKS.get(index);
        t.setCompleted();
        System.out.println("Nice! I've marked this task as done:\n" + t);
    }

    /**
     * Marks Task as not completed.
     * 
     * @param index Index of Task to be marked in the TASKS ArrayList.
     */
    protected void unmarkTask(int index) throws KException {
        if (index + 1 > TASKS.size() || index < 0) {
            throw new KException("Error: " + "Index out of range!");
        }
        Task t = TASKS.get(index);
        t.setNotCompleted();
        System.out.println("OK, I've marked this task as not done yet:\n" + t);
    }

    protected void delete(int index) throws KException {
        if (index + 1 > TASKS.size() || index < 0) {
            throw new KException("Error: " + "Index out of range!");
        }
        Task t = TASKS.remove(index);
        System.out.println("OK, I've deleted this task:\n" + t
                + "\nNow you have " + TASKS.size() + " tasks in this list!");
    }
}
