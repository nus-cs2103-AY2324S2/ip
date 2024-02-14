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
    public void printList() {
        Ui.showLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < this.tasklist.size(); i++) {
            System.out.println("\t\t" + (i + 1) + "." + this.tasklist.get(i).toString());
        }
        Ui.showLine();
    }

    /**
     * Deletes a task from the list.
     * 
     * @param taskNo The index of the task to delete.
     * @throws EmisException If the specified task number is invalid.
     */
    public void deleteTask(int taskNo) throws EmisException {
        if (taskNo <= 0 || taskNo > this.tasklist.size()) {
            throw new EmisException("This task does not exist!");
        } else {
            Ui.showLine();
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t\t" + this.tasklist.get(taskNo - 1).toString());
            this.tasklist.remove(taskNo - 1);
            System.out.println("\tNow you have " + this.tasklist.size() + " tasks in the list.");
            Ui.showLine();
        }
    }

    /**
     * Adds a task to the list.
     * 
     * @param task The task to add.
     */
    public void addTask(Task task) {
        this.tasklist.add(task);
        Ui.showLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println("\tNow you have " + this.tasklist.size() + " tasks in the list.");
        Ui.showLine();
    }
    
    /**
     * Marks a task as done.
     * 
     * @param taskNo The index of the task to mark as done.
     * @throws EmisException If the specified task number is invalid.
     */
    public void markAsDone(int taskNo) throws EmisException {
        if (taskNo <= 0 || taskNo > tasklist.size()) {
            throw new EmisException("This task does not exist!");
        } else {
            Task task = this.tasklist.get(taskNo - 1);
            task.markAsDone();
            this.tasklist.set(taskNo - 1, task);
        }
    }

    /**
     * Marks a task as undone.
     * 
     * @param taskNo The index of the task to mark as undone.
     * @throws EmisException If the specified task number is invalid.
     */
    public void markAsUndone(int taskNo) throws EmisException {
        if (taskNo <= 0 || taskNo > tasklist.size()) {
            throw new EmisException("This task does not exist!");
        } else {
            Task task = this.tasklist.get(taskNo - 1);
            task.markAsUndone();
            this.tasklist.set(taskNo - 1, task);
        }
    }

    /**
     * Finds tasks containing a specific keyword in their descriptions.
     * 
     * @param keywordToFind The keyword to search for in task descriptions.
     * @return A string representation of matching tasks found in the task list.
     */
    public void findTasks(String keywordToFind) {
        Ui.showLine();
        System.out.println("Here are the matching tasks in your list:");
        int counter = 1;
        for (Task task : this.tasklist) {
            if (task.getDescription().contains(keywordToFind)) {
                System.out.println(Integer.toString(counter) + ". " + task.toString());
                counter++;
            }
        }
        Ui.showLine();
    }
}