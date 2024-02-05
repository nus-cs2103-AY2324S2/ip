package duke;

import duke.Task;

import java.util.ArrayList;



public class TaskList {

    public ArrayList<Task> tasks;

    public  TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int i) {
        return tasks.get(i);
    }
}
