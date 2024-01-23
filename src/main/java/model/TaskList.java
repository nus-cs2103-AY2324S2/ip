package model;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Simply add a task to the TaskList object.
     *
     * @param t task to be added
     */
    public void addTask(Task t) {
        taskList.add(t);
    }

    /**
     * Returns the numbered list of tasks.
     *
     * @return list of tasks as a string
     */
    @Override
    public String toString() {
        String res = "";
        for (int i = 0; taskList.size() > i; i++) {
            res += String.format("%d. %s\n", i + 1, taskList.get(i));
        }
        return res;
    }
}
