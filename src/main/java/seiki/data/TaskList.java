package seiki.data;

import java.util.ArrayList;

import seiki.data.task.Task;

/**
 * Represents the entire task list.
 * Contains the data of the task list.
 */
public class TaskList {
    protected ArrayList<Task> taskList;
    protected int taskCount;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
        this.taskCount = 0;
    }

    /**
     * Constructs a task list with the given data.
     * @param tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
        this.taskCount = tasks.size();
    }

    /**
     * Adds task to the task list.
     * @param task
     */
    public void addTask(Task task) {
        taskList.add(task);
        taskCount++;
    }

    /**
     * Deletes task from the task list.
     * @param task
     */
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

    /**
     * Searches for task that matches the {@code keyword}.
     * @param keyword
     */
    public TaskList searchByKeyword(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : taskList) {
            if (task.hasKeyword(keyword)) {
                result.add(task);
            }
        }
        return new TaskList(result);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (taskCount == 0) {
            sb.append("There are currently no task added.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                int taskNum = i + 1;
                String taskString = "→ " + taskNum + ". " + taskList.get(i).toString();
                sb.append(taskString);
                if (taskNum != taskList.size()) {
                    sb.append("\n");
                }
            }
        }
        return sb.toString();
    }
}
