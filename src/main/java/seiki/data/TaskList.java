package seiki.data;

import seiki.data.task.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> taskList;
    protected int taskCount;

    public TaskList() {
        this.taskList = new ArrayList<>();
        this.taskCount = 0;
    }

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
        this.taskCount = tasks.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
        taskCount++;
    }

    public void deleteTask(Task task) {
        taskList.remove(task);
        taskCount--;
    }

    public Task getTaskByNumber(int taskNum) {
        return taskList.get(taskNum - 1);
    }

    public int getTaskCount() {
        return this.taskCount;
    }

    public ArrayList<Task> getAllTasks() {
        return this.taskList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (taskCount == 0) {
            sb.append("There are currently no task added.");
        } else {
            sb.append("Here are the task(s) you have:");
            for (int i = 0; i < taskList.size(); i++) {
                int taskNum = i + 1;
                String taskString = "\n→ " + taskNum + ". " + taskList.get(i).toString();
                sb.append(taskString);
            }
        }
        return sb.toString();
    }
}
