package emis;

import emisExceptions.EmisException;
import emisTask.Task;
import java.util.ArrayList;

// TaskList class, contains task list and its operations
/**
 * The TaskList class contains a list of tasks and provides operations to manipulate the task list in the EMIS application.
 */
public class TaskList {
    /** The list of tasks. */
    private ArrayList<Task> tasklist;

    /**
     * Constructs a new TaskList object with the specified list of tasks.
     * 
     * @param tasklist The list of tasks.
     */
    public TaskList(ArrayList<Task> tasklist) {
        this.tasklist = tasklist;
    }

    /**
     * Constructs a new TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.tasklist = new ArrayList<>();
    }

    /**
     * Prints the list of tasks.
     */
    public String printList() {
        String response = "Here are the tasks in your list:";
        for (int i = 0; i < this.tasklist.size(); i++) {
            response += ("\n" + (i + 1) + "." + this.tasklist.get(i).toString());
        }
        return response;
    }

    /**
     * Deletes a task from the list.
     * 
     * @param taskNo The index of the task to delete.
     * @throws EmisException If the specified task number is invalid.
     */
    public String deleteTask(int taskNo) throws EmisException {
        String response = "";
        if (taskNo <= 0 || taskNo > this.tasklist.size()) {
            throw new EmisException("This task does not exist!");
        } else {
            response += "Noted. I've removed this task:";
            response += "\n" + this.tasklist.get(taskNo - 1).toString();
            this.tasklist.remove(taskNo - 1);
            response += ("\nNow you have " + this.tasklist.size() + " tasks in the list.");
            return response;
        }
    }

    /**
     * Adds a task to the list.
     * 
     * @param task The task to add.
     */
    public String addTask(Task task) {
        String response = "";
        this.tasklist.add(task);
        response += "Got it. I've added this task:";
        response += "\n" + task.toString();
        response += ("\nNow you have " + this.tasklist.size() + " tasks in the list.");
        return response;
    }
    
    /**
     * Marks a task as done.
     * 
     * @param taskNo The index of the task to mark as done.
     * @throws EmisException If the specified task number is invalid.
     */
    public String markAsDone(int taskNo) throws EmisException {
        if (taskNo <= 0 || taskNo > tasklist.size()) {
            throw new EmisException("This task does not exist!");
        } else {
            Task task = this.tasklist.get(taskNo - 1);
            String response = task.markAsDone();
            this.tasklist.set(taskNo - 1, task);
            return response;
        }
    }

    /**
     * Marks a task as undone.
     * 
     * @param taskNo The index of the task to mark as undone.
     * @throws EmisException If the specified task number is invalid.
     */
    public String markAsUndone(int taskNo) throws EmisException {
        if (taskNo <= 0 || taskNo > tasklist.size()) {
            throw new EmisException("This task does not exist!");
        } else {
            Task task = this.tasklist.get(taskNo - 1);
            String response = task.markAsUndone();
            this.tasklist.set(taskNo - 1, task);
            return response;
        }
    }

    /**
     * Finds tasks containing a specific keyword in their descriptions.
     * 
     * @param keywordToFind The keyword to search for in task descriptions.
     * @return A string representation of matching tasks found in the task list.
     */
    public String findTasks(String keywordToFind) {
        String response = "Here are the matching tasks in your list:";
        int counter = 1;
        for (Task task : this.tasklist) {
            if (task.getDescription().contains(keywordToFind)) {
                response += ("\n" + Integer.toString(counter) + ". " + task.toString());
                counter++;
            }
        }
        return response;
    }
}