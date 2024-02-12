package snomtasklist;


import snomexceptions.InvalidCommandIndexException;
import snomtask.Task;

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

    public String markTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.task_lst.get(pos).doTask();
            return "Succesfully marked task";
        }

    }

    public String unmarkTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.task_lst.get(pos).undoTask();
            return "Succesfully unmarked task";
        }
    }



    public String deleteTask(int pos) throws InvalidCommandIndexException {

        if (counter < pos || pos <= 0) {
            throw new InvalidCommandIndexException();
        } else {
            pos -= 1;
            this.counter -= 1;
            this.task_lst.remove(pos);
            return "Succesfully removed task";
        }

    }

    public String AddTask(Task t) {
        this.counter += 1;
        this.task_lst.add(t);
        return "Succesfully added task";
    }

    public int getCounter() {
        return this.counter;
    }

    public String displayTaskList() {
        StringBuilder lst = new StringBuilder();
        for (int i = 0; i<counter; i++) {
            lst.append(this.task_lst.get(i) + "\n");
        }
        return lst.toString();
    }

    public String printMatchingTasks(String cmd) {
        ArrayList<Task> found_tasks = new ArrayList<>();
        for (int i = 0; i<counter; i++) {
            if (this.task_lst.get(i).match(cmd)) {
                found_tasks.add(task_lst.get(i));
            }
        }
        StringBuilder lst = new StringBuilder();
        if (found_tasks.size() > 0) {
            System.out.println("Here are the tasks that match your description");
            for (int j = 0; j < found_tasks.size(); j++) {
                lst.append(this.task_lst.get(j) + "\n");
            }
            return lst.toString();
        } else {
            return "Sorry, we did not find any tasks that matched your description";
        }
    }
}
