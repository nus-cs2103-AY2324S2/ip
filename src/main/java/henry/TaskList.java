package henry;

import henry.task.Task;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        items.add(task);
        System.out.println("Added this task");
        System.out.println(items.get(items.size() - 1));
        System.out.printf("Now you have %d tasks in the list :(\n", items.size());
        System.out.println();
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws HenryException If the index is out of bounds.
     */
    public void markTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        items.get(index).markAsDone();
        System.out.println("This task is marked as done XD");
        System.out.println(items.get(index));
        System.out.println();
    }

    /**
     * Marks a task as undone.
     *
     * @param index The index of the task to be marked as undone.
     * @throws HenryException If the index is out of bounds.
     */
    public void unmarkTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        items.get(index).unmarkAsDone();
        System.out.println("This task is marked as undone :(");
        System.out.println(items.get(index));
        System.out.println();
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task to be deleted.
     * @throws HenryException If the index is out of bounds.
     */
    public void deleteTask(int index) throws HenryException {
        if (index < 0 || index >= items.size()) {
            throw new HenryException("The index is out of bounds!");
        }
        System.out.println("This task is deleted :)");
        System.out.println(items.get(index));
        System.out.println();
        items.remove(index);
    }

    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> ret = new ArrayList<>();
        for (Task task : items) {
            if (task.containsKeyword(keyword)) {
                ret.add(task);
            }
        }
        return ret;
    }

    /**
     * Prints the list of tasks.
     */
    public void printList() {
        System.out.println("Here is a matching list of tasks:");
        for (int i = 0; i < items.size(); i = i + 1) {
            System.out.printf("%d. %s\n", i + 1, items.get(i));
        }
        System.out.println();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<String> getFileStrings() {
        ArrayList<String> ret = new ArrayList<>();
        for (Task item : items) {
            ret.add(item.toFileString());
        }
        return ret;
    }
}
