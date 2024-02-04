package SnomTaskList;

import SnomExceptions.InvalidCommandIndexException;
import SnomTask.Task;

import java.util.ArrayList;

/**
 * TaskList implements a class that keeps track
 * of the tasks added by the user.
 */
public class TaskList {
    private ArrayList<Task> task_lst;
    private int counter;


    private TaskList() {
        this.task_lst = new ArrayList<>();
        this.counter = 0;
    }

    public static TaskList makeTaskList() {
        return new TaskList();
    }

    /**
     * Retrieves the task from the TaskList based on the index.
     *
     * @param pos is the index of the task in the TaskList.
     * @return the instance of the Task at the specified index.
     * @throws InvalidCommandIndexException if the index specified by the user
     * is not valid.
     */
    public Task getTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            return this.task_lst.get(pos);
        }

    }

    /**
     * Changes the status of the task in TaskList to complete.
     * @param pos is the index of the task in the TaskList.
     * @throws InvalidCommandIndexException
     */
    public void markTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.task_lst.get(pos).doTask();
        }

    }

    /**
     * Changes the status of the task in TaskList to incomplete.
     * @param pos is the index of the task in the TaskList.
     * @throws InvalidCommandIndexException if the index specified by the user
     * is not valid.
     *
    */
    public void unmarkTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.task_lst.get(pos).undoTask();
        }
    }

    /**
     * Deletes the instance of the Task from TaskList.
     * @param pos is the index of the task in the TaskList.
     * @throws InvalidCommandIndexException if the index specified by the user
     * is not valid.
     *
     */
    public void deleteTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.counter -= 1;
            this.task_lst.remove(pos);
        }

    }

    /**
     * Adds the task to the TaskList.
     * @param t is the instance of the task to be added.
     */
    public void AddTask(Task t) {
        this.counter += 1;
        this.task_lst.add(t);
    }

    /**
     * Retrieves the number of tasks in the TaskList.
     * @return an integer representing the number of tasks
     * in the TaskList.
     */
    public int getCounter() {
        return this.counter;
    }

    /**
     * Prints the tasks for the user to see.
     */
    public void displayTaskList() {
        for (int i = 0; i<counter; i++) {
            System.out.println(this.task_lst.get(i));
        }
    }
}
