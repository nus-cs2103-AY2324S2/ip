package duke.task;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import duke.ui.Ui;

public class TaskList {
    private static LinkedList<Task> tasks = null;
    private static LinkedList<Task> lastFilteredTasks = null;
    private static TaskList instance = null;
    private Ui ui = null;

    private boolean isFiltered = false;

    private TaskList() {
        //this.livInstance = Liv.getInstance();
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

    public void initTaskList() {
        tasks = new LinkedList<>();
        ui = Ui.getInstance();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public String setTaskDoneWithIndex(int index, String isDoneUpdateString, boolean isDone)
            throws TaskIndexOutOfBoundsException {
        try {
            getTask(index).setIsDone(isDoneUpdateString, isDone);
            return getTask(index).updateIsDoneMessage();
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new TaskIndexOutOfBoundsException(index);
        }
    }

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
