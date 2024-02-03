package Duke;

import Duke.Tasks.Task;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public int size() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    public void setAsDone(int index) {
        this.taskList.get(index).setAsDone();
    }

    public void setAsNotDone(int index) {
        this.taskList.get(index).setAsNotDone();
    }

}
