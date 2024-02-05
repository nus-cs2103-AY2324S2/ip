package SnomTaskList;

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

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            return this.task_lst.get(pos);
        }

    }

    public void markTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.task_lst.get(pos).doTask();
        }

    }

    public void unmarkTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.task_lst.get(pos).undoTask();
        }
    }



    public void deleteTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
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

    public int getCounter() {
        return this.counter;
    }

    public void displayTaskList() {
        for (int i = 0; i<counter; i++) {
            System.out.println(this.task_lst.get(i));
        }
    }

    public void printMatchingTasks(String cmd) {
        ArrayList<Task> found_tasks = new ArrayList<>();
        for (int i = 0; i<counter; i++) {
            if (this.task_lst.get(i).match(cmd)) {
                found_tasks.add(task_lst.get(i));
            }
        }
        if (found_tasks.size() > 0) {
            System.out.println("Here are the tasks that match your description");
            for (int j = 0; j < found_tasks.size(); j++) {
                System.out.println(found_tasks.get(j));
            }
        } else {
            System.out.println("Sorry, we did not find any tasks that matched your description");
        }
    }
}
