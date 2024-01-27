package talkingbot.util;

import java.util.ArrayList;
import talkingbot.task.Task;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this(new ArrayList<>());
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task newTask) {
        this.tasks.add(newTask);
    }

    public Task removeTask(int idx) {
        return this.tasks.remove(idx);
    }

    public Task getTask(int idx) {
        return this.tasks.get(idx);
    }

    public void setTask(int idx, Task task) {
        this.tasks.set(idx, task);
    }

    public int getSize() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        StringBuilder returnedString = new StringBuilder();
        returnedString.append("\tHere are the tasks in your list:\n");
        for (int idx = 0; idx < this.tasks.size(); idx++) {
            returnedString.append(String.format("\t%d. %s", idx+1, this.getTask(idx).toString()));
            if (idx != this.tasks.size() - 1) {
                returnedString.append("\n");
            }
        }
        return returnedString.toString();
    }
}
