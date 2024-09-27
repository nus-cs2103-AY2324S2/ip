package pew;

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

    /**
     * finds all tasks that contain the keyword
     *
     * @param keyword the keyword to search for
     * @return an ArrayList of tasks that contain the keyword
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task task : taskArr) {
            if (task.getDescription().contains(keyword)) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    public String listAllTasks() {
        StringBuilder result = new StringBuilder();
        for (Task task : taskArr) {
            result.append(task.getTask()).append("\n");
        }
        return result.toString();
    }

    public void unmarkTask(int index) {
        taskArr.get(index - 1).unmark();
    }

    public void markTask(int index) {
        taskArr.get(index - 1).mark();
    }

    public Task getTaskObject(int index) {
        return taskArr.get(index - 1);
    }

    public String printSelectedTask(int index) {
        return taskArr.get(index - 1).getTask();
    }

    public void deleteTask(int index) {
        taskArr.remove(index - 1);
    }

    public int size() {
        return taskArr.size();
    }

    public void addTask(Task task) {
        taskArr.add(task);
    }
}
