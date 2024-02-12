package snomtasklist;

import java.util.ArrayList;

import snomexceptions.InvalidCommandIndexException;
import snomtask.Task;

public class TaskList {
    private ArrayList<Task> taskList;
    private int counter;


    private TaskList() {
        this.taskList = new ArrayList<>();
        this.counter = 0;
    }

    /**
     * Makes a empty tasklist.
     * @return a empty tasklist.
     */
    public static TaskList makeTaskList() {
        return new TaskList();
    }

    /**
     * Gets the tasklist at the specified index within the tasklist.
     * If the index is invalid, an exception is thrown.
     * @param pos is the index of the task.
     * @return the task at the specified index.
     * @throws InvalidCommandIndexException if the index is out
     *         of range of the tasklist.
     */
    public Task getTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            return this.taskList.get(pos);
        }

    }

    /**
     * Marks the task at the specified index within the tasklist as done.
     * If the index is invalid, an exception is thrown.
     * @param pos is the index of the task.
     * @throws InvalidCommandIndexException if the index is out
     *         of range of the tasklist.
     */
    public void markTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.taskList.get(pos).doTask();
        }

    }

    /**
     * Unarks the task at the specified index within the tasklist as undone.
     * If the index is invalid, an exception is thrown.
     * @param pos is the index of the task.
     * @throws InvalidCommandIndexException if the index is out
     *         of range of the tasklist.
     */
    public void unmarkTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.taskList.get(pos).undoTask();
        }
    }



    /**
     * deletes the task at the specified index within the tasklist.
     * If the index is invalid, an exception is thrown.
     * @param pos is the index of the task.
     * @throws InvalidCommandIndexException if the index is out
     *         of range of the tasklist.
     */
    public void deleteTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.counter -= 1;
            this.taskList.remove(pos);
        }

    }

    /**
     * Adds a new task to the tasklist.
     */
    public void addTask(Task t) {
        this.counter += 1;
        this.taskList.add(t);
    }

    /**
     * Gets the number of tasks in the tasklist.
     * @return an integer representing the number
     *         of the tasks within the tasklist.
     */
    public int getCounter() {
        return this.counter;
    }

    /**
     * Prints out all the tasks in the tasklist.
     */
    public void displayTaskList() {
        for (int i = 0; i < counter; i++) {
            System.out.println(this.taskList.get(i));
        }
    }

    /**
     * Prints out the tasks matching the description within
     *         the tasklist.
     * @param cmd is the command containing the word that
     *            the user wishes to search for in the tasklist.
     */
    public void printMatchingTasks(String cmd) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (int i = 0; i < counter; i++) {
            if (this.taskList.get(i).match(cmd)) {
                foundTasks.add(taskList.get(i));
            }
        }
        if (foundTasks.size() > 0) {
            System.out.println("Here are the tasks that match your description");
            for (int j = 0; j < foundTasks.size(); j++) {
                System.out.println(foundTasks.get(j));
            }
        } else {
            System.out.println("Sorry, we did not find any tasks that matched your description");
        }
    }
}
