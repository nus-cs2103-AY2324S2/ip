package floofy;

import floofy.task.Task;

import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public String[] getTaskList() {
        String[] taskList = new String[this.tasks.size()];
        for (int i = 0; i < this.tasks.size(); i++) {
            taskList[i] = this.tasks.get(i).toFileFormat();
        }
        return taskList;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

    public void markTaskAsDone(int index) {
        this.tasks.get(index).markTask();
    }

    public void markTaskAsUndone(int index) {
        this.tasks.get(index).unmarkTask();
    }

}
