package tyler.task;

import java.util.ArrayList;
public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getList() {
        return this.taskList;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }

    public int getNumOfTasks() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public void find(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task: taskList) {
            if (task.name.toLowerCase().contains(keyword.toLowerCase())) {
                matchedTasks.add(task);
            }
        }
        if (matchedTasks.isEmpty()) {
            System.out.println("    Sorry. No matching tasks found.");
        } else {
            System.out.println("    Here are the matching tasks in your list:");
            for (int i = 0; i < matchedTasks.size(); i++) {
                System.out.println("      " + (i + 1) + ". " + matchedTasks.get(i));
            }
        }
    }
}
