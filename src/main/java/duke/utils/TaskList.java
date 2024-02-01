package duke.utils;

import java.util.ArrayList;

import duke.tasks.Task;

/**
 * This class implementions the functionality of a variable length list containg Task objects.
 * 
 * @author delishad21
 */
public class TaskList {
    private ArrayList<Task> toDoList;

    /**
     * Creates a TaskList that contains an ArrayList.
     */
    public TaskList() {
        this.toDoList = new ArrayList<>();
    }

    /**
     * Prints out all items in the TaskList.
     * 
     * @param ui Used for printing.
     */
    public void printList(Ui ui) {
        String tasklistString = "";
        for (int i = 1; i <= toDoList.size(); i++) {
            tasklistString += i + "." + toDoList.get(i - 1) + "\n";
        }
        ui.botPrint(tasklistString);
    }

    /**
     * Adds task to list.
     * 
     * @param t Task that is to be added to list.
     */
    public void add(Task t) {
        this.toDoList.add(t);
    }

    /**
     * Removes task from list based on task input.
     * 
     * @param t Task that is to be removed from list.
     */
    public void remove(Task t) {
        this.toDoList.remove(t);
    }

    /**
     * Removes Task from list based on index.
     * 
     * @param i Index of task to be removed from list.
     */
    public void remove(int i) {
        this.toDoList.remove(i - 1);
    }

    /**
     * Gets items from TaskList using index.
     * 
     * @param i Index of item to be retrieved.
     * @return Task retrieved from TaskList.
     */
    public Task get(int i) {
        return this.toDoList.get(i - 1);
    }

    /**
     * Gets current size of TaskList.
     * 
     * @return Size of TaskList as integer.
     */
    public int size() {
        return this.toDoList.size();
    }

}
