package yippee;
import java.util.ArrayList;

import yippee.exceptions.InvalidCommandException;
import yippee.exceptions.InvalidTaskNumberException;
import yippee.tasks.Task;

/**
 * Represents the list of tasks a user has added.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Instantiates new TaskList.
     * Creates and assigns new task List and Ui to local variables.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
        this.ui = new Ui();
    }

    /**
     * Instantiates new TaskList.
     * Creates and assigns new Ui to local variables.
     * Assigns given list to local tasks variable.
     * @param list List of tasks to be instantiated.
     */
    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
        this.ui = new Ui();
    }

    /**
     * Adds task from storage to current active list.
     * @param newTask Task to be added to active list.
     */
    public void addStoredTask(Task newTask) {
        this.tasks.add(newTask);
    }

    /**
     * Adds new task to active list.
     * @param task Task to be added to active list.
     */
    public void addNewTask(Task task) {
        this.tasks.add(task);
        ui.addTaskRespond(task, this.tasks.size());
    }

    /**
     * Deletes task from active list.
     * @param deleteNumber Index of task to be deleted.
     * @throws InvalidCommandException If index of task is out of bounds.
     */
    public void deleteTask(int deleteNumber) throws InvalidCommandException {
        if (deleteNumber < 1 || deleteNumber > tasks.size()) {
            throw new InvalidTaskNumberException("Invalid number! Index does not exist >:((");
        } else {
            Task task = tasks.get(deleteNumber - 1);
            tasks.remove(deleteNumber - 1);
            ui.deleteTaskRespond(task, this.tasks.size());
        }
    }

    /**
     * Marks task as complete in active list.
     * @param number Index of task to be marked as complete.
     * @throws InvalidCommandException If index of task is out of bounds
     */
    public void markTask(int number) throws InvalidCommandException {
        if (number < 1 || number > tasks.size()) {
            throw new InvalidTaskNumberException("Invalid number! Index does not exist >:((");
        } else {
            Task task = tasks.get(number - 1);
            task.markDone();
            ui.markTaskRespond(task);
        }
    }

    /**
     * Marks task as incomplete in active list.
     * @param number Index of task to be marked as incomplete.
     * @throws InvalidCommandException If index of task is out of bounds
     */
    public void unmarkTask(int number) throws InvalidCommandException {
        if (number < 1 || number > tasks.size()) {
            throw new InvalidTaskNumberException("Invalid number! Index does not exist >:((");
        } else {
            Task task = tasks.get(number - 1);
            task.markNotDone();
            ui.unmarkTaskRespond(task);
        }
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }

}
