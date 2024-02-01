package duke.utility;

import java.util.ArrayList;

import duke.task.Task;
public class TaskList {

    private ArrayList<Task> taskStore;

    public TaskList() {
        this.taskStore = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> loadedTaskStore) {
        this.taskStore = loadedTaskStore;
    }

    public Task markTask(int index) {
        Task currTask = taskStore.get(index);
        currTask.updateTask(true);
        return currTask;
    }

    public Task unmarkTask(int index) {
        Task currTask = taskStore.get(index);
        currTask.updateTask(false);
        return currTask;
    }

    public Task deleteTask(int index) {
        Task deletedTask = taskStore.remove(index);
        return deletedTask;
    }
    public void addTask(Task taskToBeAdded) {
        taskStore.add(taskToBeAdded);
    }
    public ArrayList<Task> getTaskStore() {
        return taskStore;
    }

    public int listSize() {
        return taskStore.size();
    }

    public ArrayList<Task> findTask(String keyword) {
        ArrayList<Task> filterList = new ArrayList<>();
        for (int k = 0; k < this.listSize(); k++) {
            if (this.taskStore.get(k).getDescription().contains(keyword)) {
                filterList.add(this.taskStore.get(k));
            }
        }
        return filterList;
    }
}
