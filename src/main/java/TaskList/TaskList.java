package TaskList;

import SnomExceptions.InvalidCommandIndexException;
import SnomTask.Task;

import java.util.ArrayList;

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

    public Task getTask(int pos) throws InvalidCommandIndexException {

        if (counter <= pos) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            return this.task_lst.get(pos);
        }

    }

    public void markTask(int pos) throws InvalidCommandIndexException {

        if (counter <= pos) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.task_lst.get(pos).doTask();
        }

    }

    public void unmarkTask(int pos) throws InvalidCommandIndexException {

        if (counter <= pos) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.task_lst.get(pos).undoTask();
        }
    }



    public void deleteTask(int pos) throws InvalidCommandIndexException {

        if (counter <= pos) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.counter -= 1;
            this.task_lst.remove(pos);
        }

    }

    public void AddTask(Task t) {
        this.counter += 1;
        this.task_lst.add(t);
    }
}
