package victor.tasklist;

import java.util.ArrayList;

import victor.tasktype.Task;
import victor.ui.Ui;



/**
 * The TaskList class contains the methods to add new tasks to
 * the ArrayList, delete tasks and even print out all the tasks
 * current in the ArrayList.
 *
 * @author Dominic Fu Ming Jun
 */
public class TaskList {
    /**
     * The currentTaskList variable is used to hold the current data of the program
     */
    private ArrayList<Task> currentTaskLists;
    /**
     * The ui variable is used to use any method from the Ui class
     */
    private Ui ui = new Ui();

    /**
     * The TaskList Constructor will take in the ArrayList of the
     * current data from the data file, and place it in the
     * currentTaskList variable.
     *
     * @param currentTaskList The ArrayList containing all the current data from the data file
     */
    public TaskList(ArrayList<Task> currentTaskList) {
        this.currentTaskLists = currentTaskList;
    }

    /**
     * This TaskList Constructor is used to create a new TaskList using an
     * empty ArrayList of Tasks.
     */
    public TaskList() {
        this.currentTaskLists = new ArrayList<Task>();
    }

    /**
     * Prints out all the current data from the ArrayList.
     */
    public String printList() {
        StringBuilder listString = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < currentTaskLists.size(); i++) {
            listString.append(i + 1).append(".").append(currentTaskLists.get(i).toString()).append("\n");
        }
        return listString.toString();
    }

    /**
     * Returns the ArrayList of the Tasks when necessary.
     * Mainly used to return the ArrayList so the data file can be updated
     * with the new data using the updateFile method in the Storage class.
     *
     * @return The current ArrayList of Tasks
     */
    public ArrayList<Task> returnList() {
        return currentTaskLists;
    }

    /**
     * Retrieves a Task from a specific position in the ArrayList and
     * returns it.
     *
     * @param posNum The number of where in the ArrayList to retrieve the Task from.
     * @return A Task obtained from the ArrayList of Tasks
     */
    public Task getPosValue(int posNum) {
        return currentTaskLists.get(posNum);
    }

    /**
     * Add a new Task into the ArrayList.
     *
     * @param userTask A Task that is to be added to the ArrayList
     */
    public void addTask(Task userTask) {
        currentTaskLists.add(userTask);
    }

    /**
     * Removes a Task from a specific position in the ArrayList.
     *
     * @param posNum The number of where in the ArrayList to remove the Task from.
     */
    public void removeTask(int posNum) {
        currentTaskLists.remove(posNum);
    }

    /**
     * Returns the number of Tasks in the currentTaskList variable
     *
     * @return size of the ArrayList currentTaskList
     */
    public int getSize() {
        return currentTaskLists.size();
    }

    /**
     * Search each of the tasks in the currentTaskLists ArrayList,
     * then display any tasks that contains the wordSearch variable
     * within the description.
     *
     * @param wordSearch A string which would be used to check if
     *                   the description contains it.
     * @return a String containing all the tasks
     *         matching the word search
     */
    public String findTask(String wordSearch) {
        assert !wordSearch.getClass().equals(String.class): "wordSearch is not a string.";
        StringBuilder listString = new StringBuilder("Here are the matching tasks in your list:\n");
        for (Task task : currentTaskLists) {
            if (task.descriptionContains(wordSearch)) {
                listString.append(task.toString()).append("\n");
            }
        }
        return listString.toString();
    }
}
