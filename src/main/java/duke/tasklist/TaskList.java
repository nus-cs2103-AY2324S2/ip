package duke.tasklist;

import duke.exception.DukeException;
import duke.task.Task;
import duke.ui.Ui;
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
    
    public String findTask(String keyword) {
        StringBuilder sb = new StringBuilder();
        int num = 0;
        for (Task t: this.getList()) {
            if (t.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                num += 1;
                sb.append(num).append(".").append(t.toString()).append(System.lineSeparator());
            }
        }
        if (num == 0) {
            sb.append("No tasks match your find query for: ").append(keyword);
        }
        return sb.toString();
    }
    
}
