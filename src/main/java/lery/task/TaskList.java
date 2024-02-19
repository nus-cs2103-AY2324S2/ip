package lery.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Represents a collection of tasks in the Lery chatbot application.
 * It manages tasks, such as adding, deleting, and retrieving tasks from the list,
 * as well as providing a method to print the list of tasks.
 *
 * The class contains methods to manipulate the task list, including adding, deleting,
 * getting the size, retrieving a specific task by index, and printing the list.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tl the ArrayList of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tl) {
        this.taskList = tl;
    }

    /**
     * Adds a task to the task list.
     *
     * @param t the task to be added.
     */
    public void add(Task t) {
        this.taskList.add(t);
    }

    /**
     * Deletes a task from the task list.
     *
     * @param t the task to be deleted.
     */
    public void delete(Task t) {
        this.taskList.remove(t);
    }

    /**
     * Gets the size of the task list.
     *
     * @return the number of tasks in the list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Retrieves a task from the task list by index.
     *
     * @param id the index of the task to be retrieved.
     * @return the task at the specified index.
     */
    public Task getTask(int id) {
        return this.taskList.get(id);
    }

    /**
     * Prints the list of tasks in a formatted string.
     *
     * @return a string containing the formatted list of tasks.
     */
    public String printList() {
        String list = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.getSize(); i++) {
            Task task = this.getTask(i);
            String message = i + 1 + "." + "[" + task.getType()
                    + "]" + "[" + task.getStatusIcon() + "] " + task.getDescription()
                    + task.getExtraInfo() + "\n";
            list += message;
        }
        return list;
    }

    /**
     * Comparator for sorting tasks based on their deadlines.
     */
    private static class DeadlineComparator implements Comparator<Task> {
        @Override
        public int compare(Task task1, Task task2) {
            if (task1 instanceof Deadline && task2 instanceof Deadline) {
                LocalDate deadline1 = ((Deadline) task1).getDeadline();
                LocalDate deadline2 = ((Deadline) task2).getDeadline();
                return deadline1.compareTo(deadline2);
            } else {
                return 0;
            }
        }
    }

    /**
     * Comparator for sorting tasks based on their types.
     */
    private static class TaskTypeComparator implements Comparator<Task> {
        @Override
        public int compare(Task task1, Task task2) {
            String type1 = task1.getType();
            String type2 = task2.getType();
            return type1.compareTo(type2);
        }
    }

    /**
     * Sorts the task list based on task types and deadlines.
     */
    public void sort() {
        Collections.sort(taskList, new TaskTypeComparator());
        Collections.sort(taskList, new DeadlineComparator());
    }
}
