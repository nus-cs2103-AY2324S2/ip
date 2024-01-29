package dino.command;

import dino.task.Task;

import java.util.ArrayList;

/**
 * Represents an ArrayList of Task.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList.
     */
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
     * Removes the Task> from the TaskList.
     *
     * @param taskNum The index of the Task to be removed.
     */
    public void deleteTask(int taskNum) throws DinoException {
        if (taskNum < 1 || taskNum > taskList.size()) {
            throw new DinoException("Invalid task number. Please provide a valid task number to delete.");
        }

        Task removedTask = taskList.remove(taskNum - 1);

        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + removedTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Lists the Task in the TaskList.
     */
    public void listTask() {
        try {
            if (taskList.isEmpty()) {
                throw new DinoException("The list is empty.");
            }
            System.out.println("Here are the tasks that you wanted to do:");
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                int index = i + 1;
                System.out.println(index + "." + task);
            }
        } catch (DinoException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Gets the taskList.
     *
     * @return taskList of type ArrayList<Task>.
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
}
