package dino;

import java.util.ArrayList;

import dino.DinoException;
import dino.task.Task;

/** Represents an ArrayList of Task. */
public class TaskList {

    private ArrayList<Task> taskList;

    /** Constructs a new TaskList. */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds the Task into the TaskList.
     *
     * @param task The Task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Removes the Task from the TaskList.
     *
     * @param taskNum The index of the Task to be removed.
     * @return String representation of the updated TaskList.
     */
    public String deleteTask(int taskNum) throws DinoException {
        if (taskNum < 1 || taskNum > taskList.size()) {
            throw new DinoException("Invalid task number. Please provide a valid task number to delete.");
        }

        Task removedTask = taskList.remove(taskNum - 1);

        return "Noted. I've removed this task:\n"
                + "  " + removedTask + "\n"
                + "Now you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Lists the Task in the TaskList.
     *
     * @return String representation of TaskList.
     */
    public String listTask() {
        try {
            if (taskList.isEmpty()) {
                throw new DinoException("The list is empty.");
            }
            StringBuilder printTaskList = new StringBuilder("Here are the tasks that you wanted to do:\n");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                int index = i + 1;
                printTaskList.append(index).append(".").append(task).append("\n");
            }
            return String.valueOf(printTaskList);
        } catch (DinoException e) {
            return (e.getMessage());
        }
    }

    /**
     * Gets the taskList.
     *
     * @return taskList of type ArrayList.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Gets the size of the taskList.
     *
     * @return integer value of size.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets the Task of the specified index.
     *
     * @param index Index of the Task.
     * @return      Task in the taskList.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Searches for Tasks with the specified keyword.
     *
     * @param keyword String value input.
     * @return        An ArrayList with the filtered Tasks.
     */
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.containsKeyword(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

}
