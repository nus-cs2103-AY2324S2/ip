import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
    private ArrayList<Task> Tasks = new ArrayList<>();

    /**
     * Constructor for TaskManager
     */
    public TaskManager() {
    }

    /**
     * Checks if there is local save and load it into system if there exist.
     */
    public void loadLocal() {
        try {
            this.Tasks = TaskFileManager.loadTasksFromFile();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error while accessing the file: " + e.getMessage());
        }
    }

    /**
     * Saves the data in the Tasks ArrayList onto a local file.
     */
    public void saveLocal() {
        try {
            TaskFileManager.saveTasksToFile(this.Tasks);
        } catch (IOException e) {
            System.out.println("Error while accessing the file: " + e.getMessage());
        }
    }

    /**
     * Stores the information in String into a Task and adds into the ArrayList
     * 
     * @param info The information from userInput
     * @param ins  The type of Task
     */
    public void addTask(String info, String ins) throws KException {
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
        }
        if (t != null) {
            this.Tasks.add(t);
            String response = "Got it. I've added this task:\n" + t + "\n" +
                    "Now you have " + this.Tasks.size() + " tasks in the list.";
            System.out.println(response);
        }
        saveLocal();
    }

    /**
     * Prints the Task List, labels them with numbers
     */
    protected void listTasks() {
        if (this.Tasks.size() == 0) {
            System.out.println("There are no tasks here. Please add a task!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < this.Tasks.size(); i++) {
                System.out.print((i + 1) + ".");
                System.out.println(this.Tasks.get(i));
            }
        }
    }

    /**
     * Marks a Task as completed.
     * 
     * @param index Index of Task to be marked in the Tasks ArrayList.
     */
    protected void markTask(int index) throws KException {
        if (index + 1 > this.Tasks.size() || index < 0) {
            throw new KException("Error: " + "Index out of range!");
        }
        Task t = this.Tasks.get(index);
        t.setCompleted();
        System.out.println("Nice! I've marked this task as done:\n" + t);
        saveLocal();
    }

    /**
     * Marks Task as not completed.
     * 
     * @param index Index of Task to be marked in the Tasks ArrayList.
     */
    protected void unmarkTask(int index) throws KException {
        if (index + 1 > this.Tasks.size() || index < 0) {
            throw new KException("Error: " + "Index out of range!");
        }
        Task t = this.Tasks.get(index);
        t.setNotCompleted();
        System.out.println("OK, I've marked this task as not done yet:\n" + t);
        saveLocal();
    }

    /**
     * Deletes an entry at index given.
     * 
     * @param index Index at which the Task will be removed from the List.
     * @throws KException Exception called when index is out of range.
     */
    protected void delete(int index) throws KException {
        if (index + 1 > this.Tasks.size() || index < 0) {
            throw new KException("Error: " + "Index out of range!");
        }
        Task t = this.Tasks.remove(index);
        System.out.println("OK, I've deleted this task:\n" + t
                + "\nNow you have " + this.Tasks.size() + " tasks in this list!");
        saveLocal();
    }
}
