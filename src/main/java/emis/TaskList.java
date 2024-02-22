package emis;

import emisTask.Task;
import java.util.ArrayList;

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
     * 
     * @return A string representation of the tasks in the list.
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
     * @return A message confirming the deletion of the task.
     * @throws AssertionError If the specified task number is invalid.
     */
    public String deleteTask(int taskNo) {
        String response = "";
        assert taskNo > 0 && taskNo <= this.tasklist.size() : "Task must exist!";
        response += "Noted. I've removed this task:";
        response += "\n" + this.tasklist.get(taskNo - 1).toString();
        this.tasklist.remove(taskNo - 1);
        response += ("\nNow you have " + this.tasklist.size() + " tasks in the list.");
        return response;
    }

    /**
     * Adds a task to the list.
     * 
     * @param task The task to add.
     * @return A message confirming the addition of the task.
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
     * @return A message confirming the task has been marked as done.
     * @throws AssertionError If the specified task number is invalid.
     */
    public String markAsDone(int taskNo) {
        assert taskNo > 0 && taskNo <= tasklist.size() : "Task must exist!";
        Task task = this.tasklist.get(taskNo - 1);
        String response = task.markAsDone();
        this.tasklist.set(taskNo - 1, task);
        return response;
    }

    /**
     * Marks a task as undone.
     * 
     * @param taskNo The index of the task to mark as undone.
     * @return A message confirming the task has been marked as undone.
     * @throws AssertionError If the specified task number is invalid.
     */
    public String markAsUndone(int taskNo) {
        assert taskNo > 0 && taskNo <= tasklist.size() : "Task must exist!";
        Task task = this.tasklist.get(taskNo - 1);
        String response = task.markAsUndone();
        this.tasklist.set(taskNo - 1, task);
        return response;
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
