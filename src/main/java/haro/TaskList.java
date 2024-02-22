package haro;

import java.util.ArrayList;

import haro.exception.InvalidArgsException;
import haro.task.Deadline;
import haro.task.Event;
import haro.task.Task;


/**
 * The TaskList class manages a list of tasks, providing methods for adding, marking, unMarking and deleting tasks.
 * It also provides methods for getting Task data, and total number of tasks in the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList from an existing ArrayList of Task objects.
     * @param tasks ArrayList containing Task objects to initialise the TaskList
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Converts tasks in the TaskList to a formatted string.
     *
     * @return Formatted string representation of tasks
     */
    public String tasksToString() {
        String taskString = "";

        if (tasks.isEmpty()) {
            return taskString;
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            int index = i + 1;
            String formattedTaskString = formatTaskString(index, currTask);
            taskString += formattedTaskString;
        }

        return taskString;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task at the specified index.
     *
     * @param index Index of the task to be marked
     * @return The marked Task object
     * @throws InvalidArgsException If the index given is out of bounds
     */
    public Task markTask(int index) throws InvalidArgsException {
        if (index >= tasks.size()) {
            throw new InvalidArgsException("Sorry that item does not exist in your list!\n");
        } else if (index < 0) {
            throw new InvalidArgsException("Please input a positive task number!\n");
        }

        Task currTask = tasks.get(index);
        currTask.markTask();
        return currTask;
    }

    /**
     * UnMarks a task at the specified index.
     *
     * @param index Index of the task to be unmarked
     * @return The unmarked Task object
     * @throws InvalidArgsException If the index given is out of bounds
     */
    public Task unmarkTask(int index) throws InvalidArgsException {
        if (index >= tasks.size()) {
            throw new InvalidArgsException("Sorry that item does not exist in your list!\n");
        } else if (index < 0) {
            throw new InvalidArgsException("Please input a positive task number!\n");
        }

        Task currTask = tasks.get(index);
        currTask.unmarkTask();
        return currTask;
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index Index of the task to be deleted
     * @return The deleted Task Object
     * @throws InvalidArgsException If the index given is out of bounds
     */
    public Task deleteTask(int index) throws InvalidArgsException {
        if (index >= tasks.size()) {
            throw new InvalidArgsException("Sorry that item does not exist in your list!\n");
        } else if (index < 0) {
            throw new InvalidArgsException("Please input a positive task number!\n");
        }

        Task currTask = tasks.get(index);
        tasks.remove(index);
        return currTask;
    }

    /**
     * Finds the tasks in task list with keyword present.
     *
     * @param searchString keyword that the task list will search for
     * @return String representation of tasks with keyword present
     */
    public String findTask(String searchString) {
        String resultString = "";
        int index = 1;

        if (tasks.isEmpty()) {
            return resultString;
        }

        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            boolean isFound = currTask.isFound(searchString);

            if (isFound) {
                String formattedTaskString = formatTaskString(index, currTask);
                resultString += formattedTaskString;
                index++;
            }
        }

        return resultString;
    }

    /**
     * Returns an edited version of the task in the task list with updated sections as indicated by the user.
     *
     * @param taskIndex Index of the task in the task list
     * @param portionToEdit The portion of the task that the user wishes to edit
     * @param updatedPortion The new supplied value for the portion the user wants to edit
     * @return Edited version of the task
     * @throws InvalidArgsException If the arguments supplied by the user are invalid
     */
    public Task editTask(int taskIndex, String portionToEdit, String updatedPortion) throws InvalidArgsException {
        Task currTask = tasks.get(taskIndex);

        if (portionToEdit.equals("/task")) {
            currTask.setTask(updatedPortion);
            return currTask;
        }

        if (portionToEdit.equals("/by")) {
            if (!(currTask instanceof Deadline)) {
                throw new InvalidArgsException("Please use edit /by only for deadline tasks");
            }

            // Safe to type cast because of instanceof check
            Deadline currDeadline = (Deadline) currTask;
            currDeadline.setDueDate(updatedPortion);
            return currDeadline;
        } else if (portionToEdit.equals("/from")) {
            if (!(currTask instanceof Event)) {
                throw new InvalidArgsException("Please use edit /from only for event tasks");
            }

            // Safe to type cast because of instanceof check
            Event currEvent = (Event) currTask;
            currEvent.setFromDate(updatedPortion);
            return currEvent;
        } else if (portionToEdit.equals("/to")) {
            if (!(currTask instanceof Event)) {
                throw new InvalidArgsException("Please use edit /to only for event tasks");
            }

            // Safe to type cast because of instanceof check
            Event currEvent = (Event) currTask;
            currEvent.setToDate(updatedPortion);
            return currEvent;
        }

        throw new InvalidArgsException("Please give a valid sub command for edit using /task, /by, /from or /to"
                + " to edit chosen portions " + portionToEdit);
    }

    /**
     * Retrieves the ArrayList of Task objects.
     *
     * @return ArrayList of Task objects
     */
    public ArrayList<Task> getArrayList() {
        return tasks;
    }

    /**
     * Retrieves the total number of tasks in the TaskList.
     *
     * @return Number of tasks in the TaskList
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Formats tasks for adding into task list String representation.
     *
     * @param index Current index of task.
     * @param task Task who will be formatted into String representation
     * @return Formatted string representation of task
     */
    private String formatTaskString(int index, Task task) {
        String result = index + ". " + task.printTask() + "\n";
        return result;
    }
}
