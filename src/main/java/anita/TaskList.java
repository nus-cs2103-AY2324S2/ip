package anita;

import java.util.ArrayList;

/**
 * The TaskList class contains an ArrayList used to store task objects.
 * It also handles all task related operations.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    private Ui ui;
    private Database database;

    /**
     * The constructor for a TaskList object.
     *
     * @param database Takes in the database used for task operations.
     */
    public TaskList(Database database) {
        taskList = new ArrayList<>();
        ui = new Ui();
        this.database = database;
    }

    /**
     * Returns the Task located at the index - 1 position in the ArrayList taskList.
     *
     * @param index Index of the item to be retrieved.
     * @return Task to be retrieved.
     */
    public Task get(int index) {
        assert index > 0 : "Index should be positive and non-zero";
        return taskList.get(index - 1);
    }

    /**
     * Returns the last Task in the ArrayList taskList.
     *
     * @return Task at the last index.
     */
    public Task getLast() {
        return taskList.get(taskList.size() - 1);
    }

    /**
     * Sets the Task "done" status to true.
     *
     * @param index Index of the task to be set to "done".
     */
    public String setDone(int index) {
        Task curr = this.get(index);
        curr.isDone = true;
        database.changeLine(index, "|true");
        return ui.setDoneMessage(curr);
    }

    /**
     * Sets the Task "done" status to false.
     *
     * @param index Index of the task to be set to not "done".
     */
    public String setNotDone(int index) {
        Task curr = this.get(index);
        curr.isDone = false;
        database.changeLine(index, "|false");
        return ui.setNotDoneMessage(curr);
    }

    /**
     * Adds the task into the ArrayList taskList.
     *
     * @param task Task to be added.
     * @throws ArrayIndexOutOfBoundsException If the index is not within the available range.
     */
    public String addTask(Task task) throws ArrayIndexOutOfBoundsException {
        taskList.add(task);
        if (!Duke.initialize) {
            return ui.addTask(task) + getNumberOfTasks();
        }
        return "";
    }

    /**
     * Removes the task from the ArrayList taskList.
     *
     * @param index Index of task to be removed.
     */
    public String removeTask(int index) {
        Task curr = taskList.remove(index - 1);
        database.deleteLine(index);
        return ui.removeTaskMessage(curr) + getNumberOfTasks();
    }

    /**
     * Iterates through the taskList and prints out each task in it.
     */
    public String listTask() {
        String res = "";
        for (int i = 0; i < taskList.size(); i++) {
            res += ui.listTaskMessage(i + 1, taskList.get(i));
        }
        return res;
    }

    /**
     * Iterates through taskList and prints out all tasks with a matching
     * sequence.
     *
     * @param sequence The substring to be compared when finding tasks.
     */
    public String findTask(String sequence) {
        ArrayList<Task> matchingTaskArray = new ArrayList<>();
        for (Task task : taskList) {
            if (task.description.contains(sequence)) {
                matchingTaskArray.add(task);
            }
        }
        String res = "";
        for (int i = 0; i < matchingTaskArray.size(); i++) {
            res += ui.listTaskMessage(i + 1, matchingTaskArray.get(i));
        }
        return res;
    }

    /**
     * Prints out the number of tasks in the taskList.
     */
    public String getNumberOfTasks() {
        return ui.getNumberOfTasksMessage(taskList.size());
    }
}
