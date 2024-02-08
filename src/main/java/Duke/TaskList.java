package Duke;

import java.util.ArrayList;

public class TaskList extends ArrayList<Task> {
    public TaskList() {
        super();
    }

    public String showALlTask() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            int counter = i + 1;
            String output = counter + ". " + this.get(i).toString();
            s.append(output).append("\n");
        }
        return s.toString();
    }

    public Task mark(int index, boolean toMark) {
        index -= 1;
        Task task = this.get(index);
        if (toMark) {
            task.markTask();
        } else {
            task.unmarkTask();
        }
        return task;
    }

    public Task delete(int index) {
        index -= 1;
        Task task = this.get(index);
        this.remove(index);
        return task;
    }

}
