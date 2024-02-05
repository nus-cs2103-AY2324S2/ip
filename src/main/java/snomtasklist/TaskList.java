package snomtasklist;

import snomexceptions.InvalidCommandIndexException;
import snomtask.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasklst;
    private int counter;


    private TaskList() {
        this.tasklst = new ArrayList<>();
        this.counter = 0;
    }

    public static TaskList makeTaskList() {
        return new TaskList();
    }

    public Task getTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            return this.tasklst.get(pos);
        }

    }

    public void markTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.tasklst.get(pos).doTask();
        }

    }

    public void unmarkTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.tasklst.get(pos).undoTask();
        }
    }



    public void deleteTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.counter -= 1;
            this.tasklst.remove(pos);
        }

    }

    public void addTask(Task t) {
        this.counter += 1;
        this.tasklst.add(t);
    }

    public int getCounter() {
        return this.counter;
    }

    public void displayTaskList() {
        for (int i = 0; i < counter; i++) {
            System.out.println(this.tasklst.get(i));
        }
    }
}
