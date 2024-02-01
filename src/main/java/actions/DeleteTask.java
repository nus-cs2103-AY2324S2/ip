package actions;

import java.util.ArrayList;
import tasks.Task;

public class DeleteTask {
    private ArrayList<Task> tasks;
    private int index;
    private String task;

    public DeleteTask(ArrayList<Task> tasks, int index) {
        this.tasks = tasks;
        this.index = index;
        this.task = "";
    }

    public void delete() {
        this.task = tasks.get(this.index - 1).toString();
        this.tasks.remove(this.index - 1);
    }

    @Override
    public String toString() {
        String temp = "Noted. I've removed this task: \n";
        temp += " " + this.task;
        temp += "\nNow you have " + tasks.size() + " tasks in this list.";
        return temp;
    }
}
