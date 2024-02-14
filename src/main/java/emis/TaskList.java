package main.java.emis;
import main.java.emis.exceptions.EmisException;
import main.java.emis.command.*;
import main.java.emis.task.*;
import java.util.ArrayList;

// TaskList class, contains task list and its operations
/**
 * The TaskList class contains a list of tasks and provides operations to manipulate the task list in the EMIS application.
 */
public class TaskList {
    /** The list of tasks. */
    private ArrayList<Task> al;

    /**
     * Constructs a new TaskList object with the specified list of tasks.
     * 
     * @param al The list of tasks.
     */
    public TaskList(ArrayList<Task> al) {
        this.al = al;
    }

    /**
     * Constructs a new TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.al = new ArrayList<>();
    }

    /**
     * Prints the list of tasks.
     */
    public void printList() {
        Ui.showLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < this.al.size(); i++) {
            System.out.println("\t\t" + (i + 1) + "." + this.al.get(i).toString());
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
        if (taskNo <= 0 || taskNo > al.size()) {
            throw new EmisException("This task does not exist!");
        } else {
            Ui.showLine();
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t\t" + this.al.get(taskNo - 1).toString());
            this.al.remove(taskNo - 1);
            System.out.println("\tNow you have " + this.al.size() + " tasks in the list.");
            Ui.showLine();
        }
    }

    /**
     * Adds a task to the list.
     * 
     * @param task The task to add.
     */
    public void addTask(Task task) {
        this.al.add(task);
        Ui.showLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task.toString());
        System.out.println("\tNow you have " + this.al.size() + " tasks in the list.");
        Ui.showLine();
    }
    
    /**
     * Marks a task as done.
     * 
     * @param taskNo The index of the task to mark as done.
     * @throws EmisException If the specified task number is invalid.
     */
    public void markAsDone(int taskNo) throws EmisException {
        if (taskNo <= 0 || taskNo > al.size()) {
            throw new EmisException("This task does not exist!");
        } else {
            Task t = this.al.get(taskNo - 1);
            t.markAsDone();
            this.al.set(taskNo - 1, t);
        }
    }

    /**
     * Marks a task as undone.
     * 
     * @param taskNo The index of the task to mark as undone.
     * @throws EmisException If the specified task number is invalid.
     */
    public void markAsUndone(int taskNo) throws EmisException {
        if (taskNo <= 0 || taskNo > al.size()) {
            throw new EmisException("This task does not exist!");
        } else {
            Task t = this.al.get(taskNo - 1);
            t.markAsUndone();
            this.al.set(taskNo - 1, t);
        }
    }
}