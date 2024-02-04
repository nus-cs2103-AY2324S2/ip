package duke.task;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import duke.ui.Ui;

/**
 * Represents a taskList to store tasks.
 */
public class TaskList {
    private static LinkedList<Task> tasks = null;
    private static LinkedList<Task> lastFilteredTasks = null;
    private static TaskList instance = null;
    private Ui ui = null;
    private boolean isFiltered = false;

    /**
     * Returns number of tasks stored in taskList.
     */
    public int getNumOfTasks() {
        return tasks.size();
    }

    public int getNumOfFilteredTasks() {
        return lastFilteredTasks.size();
    }

    public static TaskList getInstance() {
        if (instance == null) {
            instance = new TaskList();
        }
        return instance;
    }

    /**
     * Initialises taskList.
     */
    public void initTaskList() {
        tasks = new LinkedList<>();
        ui = Ui.getInstance();
    }

    /**
     * Adds a task to the taskList.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks specified task as done/not done.
     *
     * @param index Index of task to be marked.
     * @param isDone New status of the task to be marked.
     * @return The string describing marked/unmarked task.
     * @throws TaskIndexOutOfBoundsException If index specified is out of bounds.
     */
    public String setTaskDoneWithIndex(int index, boolean isDone)
            throws TaskIndexOutOfBoundsException {
        try {
            getTask(index).setIsDone(isDone);
            return getTask(index).getUpdateIsDoneMessage();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException(index);
        }
    }

    /**
     * Deletes specified task from the taskList.
     *
     * @param index Index of the task to be deleted.
     * @return Task deleted.
     * @throws TaskIndexOutOfBoundsException If index specified is out of bounds.
     */
    public Task deleteTask(int index) throws TaskIndexOutOfBoundsException {
        try {
            //duke.task.Task deletedTask =
            Task task = getTask(index);
            tasks.remove(task);
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException(index);
        }
    }

    public Task getTask(int i) {
        if (!isFiltered) {
            return tasks.get(i - 1);
        } else {
            // currently filtered
            return lastFilteredTasks.get(i - 1);
        }
    }

    public void findTaskWithKeyword(String keyword) {
        filterListWithKeyword(tasks, keyword);
    }

    public void unfilterTasks() {
        isFiltered = false;
    }

    private LinkedList<Task> filterListWithKeyword(LinkedList<Task> tasks, String keyword) {
        // Using Java 8 Streams and filter method
        List<Task> filteredTasks = tasks.stream()
                .filter(task -> task.description.contains(keyword))//.contains(keyword))
                .collect(Collectors.toList());

        // Convert the filtered list back to a linked list
        lastFilteredTasks = new LinkedList<>(filteredTasks);
        isFiltered = true;
        return lastFilteredTasks;
    }
}
