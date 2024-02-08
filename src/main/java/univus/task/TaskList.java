package univus.task;

import java.util.ArrayList;


public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public Task remove(int index) {
        return this.taskList.remove(index - 1);
    }

    public Task getTask(int index) {
        return this.taskList.get(index - 1);
    }
    public int size() {
        return this.taskList.size();
    }
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void list() {
        int index = 1;
        for (Task msg : taskList) {
            System.out.println(index + "." + msg.toString());
            index++;
        }
    }
}
