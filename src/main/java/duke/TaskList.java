package duke;
import java.util.ArrayList;

import duke.tasks.Task;

/**
 * Class used to store tasks
 */
public class TaskList {
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    String indent = "    ";

    /**
     * Add task to taskList.
     * @param task .
     */
    public void addTask(Task task)  {
        taskList.add(task);
    }

    /**
     * Returns the name of the latest task added to the task list.
     * @return name of the latest task.
     */
    public String showNewest() {
        int length = taskList.size();
        Task task = taskList.get(length - 1);
        return task.getName();
    }

    public int getLength() {
        return taskList.size();
    }

    public Task getTask(int idx) {
        return taskList.get(idx);
    }

    /**
     * Returns the full list of tasks currently on the task list.
     * @return String representing list of tasks.
     */
    public String showList() {
        Task task;
        String status;
        String finalOutput = new String("");

        for (int i = 0; i < taskList.size(); i++) {
            task = taskList.get(i);
            status = task.checkStatus();
            finalOutput = finalOutput + indent + Integer.toString(i + 1) + "." + status + "\n";
        }
        return finalOutput;
    }

    /**
     * Updates the status of task in task list to done according to given index.
     * @param idx Index of task to be updated
     */
    public void markTask(int idx) {
        Task task = taskList.get(idx - 1);
        task.completeTask();
    }

    /**
     * Updates the status of task in task list to not done according to the given index.
     * @param idx Index of task to be updated.
     */
    public void unmarkTask(int idx) {
        Task task = taskList.get(idx - 1);
        task.revertStatus();
    }

    /**
     * Removes the task from the task list based on the given index
     * @param idx Index of task to be removed
     */
    public void removeTask(int idx) {
        taskList.remove(idx);
    }

    public String findTask(String keyword) {
        Task task;
        String status;
        String finalOutput = new String("");

        for (int i = 0; i < taskList.size(); i++) {
            task = taskList.get(i);
            status = task.checkStatus();
            if (task.correctKeyword(keyword)) {
                finalOutput = finalOutput + indent + Integer.toString(i + 1) + "." + status + "\n";
            }
        }        
        return finalOutput;
    }

}
