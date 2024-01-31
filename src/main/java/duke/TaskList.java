package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> newTasks) {
        this.tasks = newTasks;
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    public Task deleteTask(int index) {
        return this.tasks.remove(index);
    }

    public void markTaskAsDone(int index) {
        this.tasks.get(index).markAsDone();
    }

    public void markTaskAsUndone(int index) {
        this.tasks.get(index).markAsUndone();
    }

    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public int getSize() {
        return this.tasks.size();
    }

    public String[] getTaskStrings() {
        String[] taskStrings = new String[this.tasks.size()];
        for (int i = 0; i < this.tasks.size(); i++) {
            taskStrings[i] = this.tasks.get(i).toString();
        }
        return taskStrings;
    }
    public String[] getFileStrings() {
        String[] storageStrings = new String[this.tasks.size()];
        for (int i = 0; i < this.tasks.size(); i++) {
            storageStrings[i] = this.tasks.get(i).toFileString();
        }
        return storageStrings;
    }

    public ArrayList<String> getMatchingTasks(String keyword) {
        ArrayList<String> matchingTasks = new ArrayList<String>();
        for (Task task : this.tasks) {
            if (task.matchesKeyword(keyword)) {
                matchingTasks.add(task.toString());
            }
        }
        return matchingTasks;
    }
}
