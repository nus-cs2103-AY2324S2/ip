package victor.tasklist;

import victor.tasktype.Task;
import victor.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The TaskList class contains the methods to add new tasks to
 * the ArrayList, delete tasks and even print out all the tasks
 * current in the ArrayList.
 *
 * @author Dominic Fu Ming Jun
 *
 */
public class TaskList {
    /** The currentTaskList variable is used to hold the current data of the program */
    ArrayList<Task> currentTaskList;
    /** The ui variable is used to use any method from the Ui class */
    Ui ui = new Ui();

    /**
     * The TaskList Constructor will take in the ArrayList of the
     * current data from the data file, and place it in the
     * currentTaskList variable.
     *
     * @param currentTaskList The ArrayList containing all the current data from the data file
     *
     */
    public TaskList(ArrayList<Task> currentTaskList) {
        this.currentTaskList = currentTaskList;
    }

    /**
     * This TaskList Constructor is used to create a new TaskList using an
     * empty ArrayList of Tasks.
     *
     */
    public TaskList() {
        this.currentTaskList = new ArrayList<Task>();
    }

    /**
     * Prints out all the current data from the ArrayList.
     *
     */
    public void printList() {
        ui.displayBarrier();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < currentTaskList.size(); i++) {
            System.out.println(i+1 + "." + currentTaskList.get(i).toString());
        }
        ui.displayBarrier();
    }

    /**
     * Returns the ArrayList of the Tasks when necessary.
     * Mainly used to return the ArrayList so the data file can be updated
     * with the new data using the updateFile method in the Storage class.
     *
     * @return The current ArrayList of Tasks
     */
    public ArrayList<Task> returnList() {
        return currentTaskList;
    }

    /**
     * Retrieves a Task from a specific position in the ArrayList and
     * returns it.
     *
     * @param posNum The number of where in the ArrayList to retrieve the Task from.
     * @return A Task obtained from the ArrayList of Tasks
     */
    public Task getPosValue(int posNum) {
        return currentTaskList.get(posNum);
    }

    /**
     * Add a new Task into the ArrayList.
     *
     * @param userTask A Task that is to be added to the ArrayList
     *
     */
    public void addTask(Task userTask) {
        currentTaskList.add(userTask);
    }

    /**
     * Removes a Task from a specific position in the ArrayList.
     *
     * @param posNum The number of where in the ArrayList to remove the Task from.
     *
     */
    public void removeTask(int posNum) {
        currentTaskList.remove(posNum);
    }

    /**
     * Returns the number of Tasks in the currentTaskList variable
     *
     * @return size of the ArrayList currentTaskList
     */
    public int getSize() {
        return currentTaskList.size();
    }
}
