package duke.tasklist;

import duke.task.Task;
import java.util.ArrayList;
public class TaskList {
    protected ArrayList<Task> list;
    
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }
    
    public ArrayList<Task> getList() {
        return this.list;
    }
    
    public void add(Task t) {
        this.getList().add(t);
    }
    
    public void delete(Task t) {
        this.getList().remove(t);
    }
    
    public int size() {
        return this.getList().size();
    }
    
    public Task getTask(int index) {
        return index >= 0 && index < this.size() ? this.getList().get(index) : null;
    }
    
    public boolean contains(Task t) {
        return this.getList().contains(t);
    }
    
    
}
