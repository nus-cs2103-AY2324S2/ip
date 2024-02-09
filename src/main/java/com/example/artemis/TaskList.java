package com.example.artemis;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Artemis application.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the TaskList based on the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Retrieves the list of tasks from the TaskList.
     *
     * @return The list of tasks in the TaskList.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public boolean containsTask(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equalsIgnoreCase(description)) {
                return true;
            }
        }
        return false;
    }

    public String findTask(String description) {
        for (int i = 0; i < tasks.size(); ++i) {
            if (tasks.get(i).getDescription().equalsIgnoreCase(description)) {
                return "     " + (i + 1) + "." + tasks.get(i).toString();
            }
        }
        return "";
    }
}
