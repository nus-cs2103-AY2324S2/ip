package utilities;

import java.util.ArrayList;
import java.util.List;

import tasks.Task;

/**
 * The TaskList contains the task list e.g. it has operations to add/delete tasks in the list
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor if there is no existing file
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor if there is an existing txt file with tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Print out of all the tasks
     */
    public void printList() {
        if (this.tasks.isEmpty()) {
            System.out.println("Your list is empty. Please add some task to the list first!");
        } else {
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < this.tasks.size(); i++) {
                System.out.println((i + 1) + ". " + this.tasks.get(i).toString());
            }
        }
        System.out.println();
    }

    /**
     * Add task into the list of tasks
     * 
     * @param task Input that is the task (could be todo, deadline or event)
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.\n");
    }

    /**
     * Delete the specified task from the list
     * 
     * @param input The ID of the task
     */
    public void deleteFromList(String input) {
        if (this.tasks.isEmpty()) {
            System.out.println(
                    "There are no task to be deleted. Please add some task to the list first!\n");
        } else {
            int idOfItem = Integer.parseInt(input);
            int actualId = idOfItem - 1;
            System.out.println("Noted. I've removed this task:");
            System.out.println(this.tasks.get(actualId).toString());
            this.tasks.remove(actualId);
            System.out.println("Now you have " + this.tasks.size() + " tasks in the list.\n");
        }
    }

    /**
     * Unmark/mark the task based on the ID
     * 
     * @param input The ID of the task
     */
    public void markAndUnmark(String input) {
        int idOfItem = Integer.parseInt(input);
        int actualId = idOfItem - 1;
        this.tasks.get(actualId).changeIsDone();
        System.out.println(this.tasks.get(actualId).toString() + "\n");
    }

    /**
     * Getter method for task
     * 
     * @return arraylist Arraylist of tasks
     */
    public List<Task> getTasks() {
        return this.tasks;
    }
}
