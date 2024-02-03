package anita;

import java.util.ArrayList;

public class TaskList {
    private Ui ui;
    private Database database;
    protected ArrayList<Task> taskList;

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
        return taskList.get(index -1);
    }

    /**
     * Sets the Task "done" status to true.
     *
     * @param index Index of the task to be set to "done".
     */
    public void setDone(int index) {
        Task curr = taskList.get(index);
        curr.isDone = true;
        ui.setDoneMessage(curr);
    }

    /**
     * Sets the Task "done" status to false.
     *
     * @param index Index of the task to be set to not "done".
     */
    public void setNotDone(int index) {
        Task curr = taskList.get(index);
        curr.isDone = false;
        ui.setNotDoneMessage(curr);
    }

    /**
     * Adds the task into the ArrayList taskList.
     *
     * @param task Task to be added.
     * @throws ArrayIndexOutOfBoundsException If the index is not within the available range.
     */
    public void addTask(Task task) throws ArrayIndexOutOfBoundsException {
        taskList.add(task);
        if (!Duke.initialize) {
            ui.addTask(task);
            getNumberOfTasks();
        }
    }

    /**
     * Removes the task from the ArrayList taskList.
     *
     * @param description Raw user input.
     */
    public void removeTask(String description) {
        String[] tokens = description.split(" ");
        if (tokens.length == 1)
            throw new ArrayIndexOutOfBoundsException("Please enter the index of the task to be deleted.");
        int index = Integer.parseInt(tokens[1]);
        Task curr = taskList.remove(index-1);
        database.deleteLine(index);
        ui.removeTaskMessage(curr);
        getNumberOfTasks();
    }

    /**
     * Iterates through the taskList and prints out each task in it.
     */
    public void listTask() {
        for (int i = 0; i < taskList.size(); i++) {
            ui.listTaskMessage(i+1, taskList.get(i));
        }
    }

    /**
     * Prints out the number of tasks in the taskList.
     */
    public void getNumberOfTasks() {
        ui.getNumberOfTasksMessage(taskList.size());
    }
}
