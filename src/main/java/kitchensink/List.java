package kitchensink;

import java.io.IOException;
import java.util.ArrayList;

import kitchensink.task.Task;

public class List {
    private ArrayList<Task> tasks;

    public List(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task, Storage storage, Ui ui) throws IOException {
        tasks.add(task);
        storage.saveTasks(this);
        ui.sayTaskAdded(task, tasks.size());
    }

    public void deleteTask(int taskNum, Storage storage, Ui ui) throws IOException {
        Task task = tasks.get(taskNum);
        tasks.remove(taskNum);
        storage.saveTasks(this);
        ui.sayTaskDeleted(task, tasks.size());
    }

    public boolean validTaskNum(int taskNum) {
        return taskNum > 0 && taskNum <= tasks.size();
    }

    public void markTask(int taskNum, Storage storage) throws IOException {
        tasks.get(taskNum).mark();
        storage.saveTasks(this);
    }

    public void unmarkTask(int taskNum, Storage storage) throws IOException {
        tasks.get(taskNum).unmark();
        storage.saveTasks(this);
    }

    public int getListSize() {
        return tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append(i + 1).append(".").append(tasks.get(i).toString());
            if (i != tasks.size() - 1) {
                result.append("\n");
            }
        }
        return String.valueOf(result);
    }
}
