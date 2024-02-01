package pyrite;

import pyrite.task.Task;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> tasks = new ArrayList<>();
    public int indexOf(Task task) {
        return this.tasks.indexOf(task);
    }
    public int size() {
        return this.tasks.size();
    }
    public String toString(){
        String output = "";
        for (Task t : this.tasks) {
            output += (this.tasks.indexOf(t) + 1) + ". " + t.toString();
            if (this.tasks.indexOf(t) == this.tasks.size() - 1) {
                break;
            }
            output += "\n";
        }
        return output;
    }
    public String toString(int id) {
        return this.tasks.get(id).toString();
    }
    public void setStatus(int id, Task.Status status) {
        this.tasks.get(id).setStatus(status);
    }
    public void remove(int id) {
        this.tasks.remove(id);
    }
    public void add(Task task) {
        this.tasks.add(task);
    }
    public boolean isValidId(int id) {
        if (id < 0 || id >= this.tasks.size()) {
            return false;
        }
        return true;
    }
}
