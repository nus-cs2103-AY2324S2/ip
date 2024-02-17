package Fredricksen.tasks;

import Fredricksen.tasks.taskType.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(TaskList list) {
        this.list = list.getList();
    }

    /**
     * Add a new Task to the ArrayList list
     * @param task The Task to be added to the ArrayList
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Remove a Task from the ArrayList based on
     * the position in the ArrayList
     * @param task The index of the Task in the ArrayList.
     */
    public void deleteTask(int task) {
        this.list.remove(task);
    }

    public void deleteTask(Task task) {
        this.list.remove(task);
    }

    /**
     * Returns the Task in the ArrayList based on
     * the position in the ArrayList.
     * @param index The index of the Task in the ArrayList
     * @return A Task in the ArrayList.
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * Returns the size of the ArrayList
     * @return an int that represents the size of the ArrayList currently.
     */
    public int size() {
        return this.list.size();
    }

    public void clearList() {
        this.list.clear();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Task task : this.list) {
            sb.append(task.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
