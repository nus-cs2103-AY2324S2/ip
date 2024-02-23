package chatteroo.tasks;

import java.util.ArrayList;

/**
 * Tasklist represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> listStore = new ArrayList<>();

    /**
     * Constructor for the TaskList class.
     */
    public TaskList(ArrayList<Task> listStore) {
        this.listStore = listStore;
    }

    /**
     * Marks the specified task as done.
     * @param taskNum
     */
    public void markTaskAsDone(int taskNum) {
        listStore.get(taskNum - 1).markAsDone();
    }

    /**
     * Marks the specified task as not done.
     * @param taskNum
     */
    public void markTaskAsNotDone(int taskNum) {
        listStore.get(taskNum - 1).markAsNotDone();
    }

    /**
     * Deletes the specified task from the list of tasks.
     * @param taskNum
     */
    public void deleteTask(int taskNum) {
        listStore.remove(taskNum - 1);
    }

    /**
     * Finds the tasks that contain the specified keyword and returns a tasklist.
     * @param keyword The keyword to be searched.
     * @return The list of tasks that contain the keyword.
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : listStore) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return new TaskList(foundTasks);
    }

    /**
     * Returns the list of tasks in the required format to be saved in the file.
     * @return The list of tasks.
     */
    public String getTaskListString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < listStore.size(); i++) {
            int taskNumber = i + 1;
            sb.append(taskNumber)
              .append(". ")
              .append(listStore.get(i).toString())
              .append("\n");
        }
        return sb.toString();
    }

    public int getTaskListSize() {
        return listStore.size();
    }

    public Task getTask(int index) {
        return listStore.get(index - 1);
    }

    /**
     * Adds a task to the list of tasks.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        listStore.add(task);
    }

    /**
     * Removes the tasks that are marked as done in the task list.
     */
    public void clearDoneTasks() {
        listStore.removeIf(task -> task.getIsDone());
    }
}
