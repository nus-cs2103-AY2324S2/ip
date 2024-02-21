package tofu.task;

import java.util.ArrayList;

import tofu.TofuException;
import tofu.ui.Ui;

import java.util.List;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to be added.
     * @throws TofuException If the task already exists in the list.
     */
    public void add(Task task) throws TofuException {
        if (tasks.contains(task)) {
            throw new TofuException(Ui.duplicateTaskError());
        }
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }

    public void remove(int index) {
        tasks.remove(index);
    }
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Search for tasks in the list that contain the specified string.
     *
     * @param str The string to search for.
     * @return A new TaskList containing the found tasks.
     */
    public TaskList find(String str) {
        ArrayList<Task> foundList = new ArrayList<>();
        for (Task task: tasks) {
            if (task.contains(str)) {
                foundList.add(task);
            }
        }
        return new TaskList(foundList);
    }

    public String toStore() {
        String out = "";
        for (Task task: tasks) {
            out += task.toStore() + "\n";
        }
        return out;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof TaskList taskList) {
            boolean isEqual = true;
            for (int i = 0; i < tasks.size(); i++) {
                if (!tasks.get(i).equals(taskList.get(i))) {
                    isEqual = false;
                    break;
                }
            }
            return isEqual;
        } else {
            return false;
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public String toString() {
        int index = 1;
        String out = "";
        for (Task task: tasks) {
            out += "    " + index + ". " + task.toString() + "\n";
            index++;
        }
        return out;
    }
}
