package duke.task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public int getSize() {
        return this.taskList.size();
    }

    public ArrayList<Task> getArrayList() {
        return this.taskList;
    }

    public Task getTaskAtPosition(int position) {
        return this.taskList.get(position - 1);
    }

    public void markTask(int position) {
            Task task = taskList.get(position - 1);
            task.setDone(true);
    }

    public void unmarkTask(int position) {
        Task task = taskList.get(position - 1);
        task.setDone(false);
    }

    public void deleteTaskAtPosition(int position) {
        taskList.remove(position - 1);
    }
}
