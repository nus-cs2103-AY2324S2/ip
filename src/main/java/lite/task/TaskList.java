package lite.task;

import lite.Storage;
import lite.util.Printer;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the size of the array list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns true if input is within i-th task's input
     * @param i Index we are looking for
     * @param input Given input
     */
    public boolean contains(int i, String input) {
        return tasks.get(i).contains(input);
    }

    /**
     * Returns the task at index i
     * @param i The index to get from
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Adds a task to the array list
     * @param task Task to be added
     */
    public void add (Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes a task from the array list
     * @param i Index of the task to be removed
     */
    public Task remove(int i) {
        return this.tasks.remove(i);
    }

    /**
     * Outputs the string representation of the TaskList when prompted with "list"
     */
    public void outputTasks() {
        Printer.printHorizontalLine();
        System.out.println("Here are the tasks in your taskList:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i+1) + ". " + this.tasks.get(i));
        }
        Printer.printHorizontalLine();
    }

    /**
     * Outputs the string representation of the TaskList
     */
    public void taskString() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i+1) + ". " + this.tasks.get(i));
        }
    }

    /**
     * Saves the content of the TaskList into a file
     */
    public void saveFile() {
        Storage.save(this.tasks);
    }

}
