package tasks;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void addTask(String taskDescription) {
        Task task = new Task(taskDescription);
        taskList.add(task);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            if (i != 0) {
                sb.append("\n");
            }
            sb.append(i + 1 + ". " + taskList.get(i));
        }
        return sb.toString();
    }
}
