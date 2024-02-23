package Dude;

import java.util.ArrayList;

class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> startingTasks) {
        this.tasks = startingTasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public void markAsDone(int index) {
        tasks.get(index).markAsDone();
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> toArrayList() {
        return tasks;
    }

    /**
     * Searches for tasks containing the specified keyword in their description.
     * <p>
     * This method iterates over a list of tasks and adds to a result list
     * all tasks whose descriptions contain the keyword, ignoring case.
     * </p>
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return An ArrayList of {@link Task} objects that contain the keyword in their description.
     */
    public ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }
}
