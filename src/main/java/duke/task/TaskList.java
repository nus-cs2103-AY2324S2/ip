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
    private static LinkedList<Task> listBeforeLastAction = null;
    private static TaskList instance = null;
    private Ui ui = null;
    private boolean isFiltered = false;

    public boolean isFiltered() {
        return isFiltered;
    }

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
        backupOriginalList();
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
            backupOriginalList();
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
            backupOriginalList();
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

    /**
     * Search each keyword from keywords through the taskList.
     * The taskList after operation would only contain tasks whose
     * description contain all keywords specified,
     * with possible overlapping and in no specific order.
     * Tasks lacking any of the keyword specified will be filtered out.
     *
     * @param keywords keywords to search through the taskList with
     */
    public void findTaskWithKeyword(String[] keywords) {
        backupOriginalList();
        filterListWithKeyword(tasks, keywords);
    }

    public void setFilteredFalse() {
        isFiltered = false;
    }

    private LinkedList<Task> filterListWithKeyword(LinkedList<Task> tasks, String ... keywords) {
        if (keywords.length == 1) { // there is no keyword except "find"
            return null;
        }
        // Using Java 8 Streams and filter method
        List<Task> filteredTasks = tasks;

        for (int i = 1; i < keywords.length; i++) {
            filteredTasks = filterTasksWithOneKeyword(filteredTasks, keywords[i]);
        }

        // Convert the filtered list back to a linked list
        lastFilteredTasks = new LinkedList<>(filteredTasks);
        isFiltered = true;
        return lastFilteredTasks;
    }

    private List<Task> filterTasksWithOneKeyword(List<Task> tasks, String keyword) {
        return tasks.stream().filter(task -> task.description.contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * @return TaskList with duplicated tasks removed.
     */
    public LinkedList<Task> removeDuplicatedTasks() {
        backupOriginalList();
        tasks = new LinkedList<>(tasks.stream().distinct().collect(Collectors.toList()));
        return tasks;
    }

    /**
     * @return TaskList sorted based on taskType then deadline / timing
     */
    public LinkedList<Task> sortTasks() {
        backupOriginalList();
        tasks = new LinkedList<>(tasks.stream().sorted().collect(Collectors.toList()));
        return tasks;
    }

    public void undo() {
        tasks = listBeforeLastAction;
    }

    private void backupOriginalList() {
        listBeforeLastAction = new LinkedList<>(tasks);
    }
}