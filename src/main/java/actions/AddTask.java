package actions;

import java.util.ArrayList;
import tasks.Task;

public class AddTask {
    private ArrayList<Task> tasks;
    private Task t;

    public AddTask(ArrayList<Task> tasks, Task t) {
        this.tasks = tasks;
        this.t = t;
    }

    public void add() {
        this.tasks.add(t);
    }

    public String toString() {
        String temp = "Got it. I've added this task: \n";
        temp += " " + this.tasks.get(this.tasks.size() - 1).toString();
        temp += "\nNow you have " + this.tasks.size() + " tasks in the list.";
        return temp;
    }
}