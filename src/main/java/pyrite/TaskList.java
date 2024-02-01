package pyrite;

import pyrite.task.Task;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> list = new ArrayList<>();
    public int indexOf(Task task) {
        return this.list.indexOf(task);
    }
    public int size() {
        return this.list.size();
    }
    public String toString(){
        String output = "";
        for (Task t : this.list) {
            output += (this.list.indexOf(t) + 1) + ". " + t.toString();
            if (this.list.indexOf(t) == this.list.size() - 1) {
                break;
            }
            output += "\n";
        }
        return output;
    }
    public String filteredString(String keyword) {
        String output = "";
        for (Task t : this.list) {
            if (t.toString().contains(keyword)) {
                output += (this.list.indexOf(t) + 1) + ". " + t.toString();
                if (this.list.indexOf(t) == this.list.size() - 1) {
                    break;
                }
                output += "\n";
            }
        }
        return output;
    }
    public String toString(int id) {
        return this.list.get(id).toString();
    }
    public void setStatus(int id, Task.Status status) {
        this.list.get(id).setStatus(status);
    }
    public void remove(int id) {
        this.list.remove(id);
    }
    public void add(Task task) {
        this.list.add(task);
    }
    public boolean isValidId(int id) {
        if (id < 0 || id >= this.list.size()) {
            return false;
        }
        return true;
    }
}
