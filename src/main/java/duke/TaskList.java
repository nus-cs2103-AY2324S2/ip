package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArr;

    public TaskList(ArrayList<Task> taskArr) {
        this.taskArr = taskArr;
    }

    // Add methods to manipulate the task list (e.g., add, delete tasks)
    public ArrayList<Task> getTaskArr() {
        return taskArr;
    }

    public ArrayList<Task> reOrder() {
        int index = 1;
        for (Task task : taskArr) {
            task.changeindex(index);
            index++;
        }
        return taskArr;
    }

    // Add other methods as needed
}
