package sam;

import sam.task.Task;
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
     * Displays the list of tasks.
     *
     * Prints the details of each task in the task list to the console,
     * typically showing information such as task type, description, and status.
     * If the task list is empty, prints a message indicating that there are no tasks.
     * Each task is numbered for easy identification.
     */
    public void displayList() {
        System.out.println("____________________________________________________________");
        if (items.isEmpty()) {
            System.out.println("No tasks in the list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < items.size(); i++) {
                Task task = items.get(i);
                System.out.println(((i + 1) + "." + task));
            }
        }
        System.out.println("____________________________________________________________");
    }

    /**
     * Marks a task in the list as done.
     *
     * Marks the task at the specified index in the task list as done.
     *
     * @param index the index of the task to mark as done
     * @throws SamException if the index is out of bounds
     */
    public void markTask(int index) throws SamException{
        if (index < 0 || index >= items.size()) {
            throw new SamException("Please check how many tasks are there in your list.");
        }
        items.get(index).markAsDone();
        System.out.println("Nice, I've marked this task as done for you:");
        System.out.println((items.get(index)));
    }

    /**
     * Unmarks a task in the list as undone.
     *
     * Unmarks the task at the specified index in the task list as undone.
     *
     * @param index the index of the task to unmark as undone
     * @throws SamException if the index is out of bounds
     */
    public void unmarkTask(int index) throws SamException {
        if (index < 0 || index >= items.size()) {
            throw new SamException("Please check how many tasks are there in your list.");
        }
        items.get(index).markAsUndone();
        System.out.println("Nice, I've marked this task as undone for you:");
        System.out.println((items.get(index)));
    }

    /**
     * Adds a new task to the task list.
     *
     * Adds the provided Task object to the task list.
     *
     * @param newTask the task to be added to the list
     */
    public void addTask(Task newTask) {
        items.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + items.size() + " tasks in the list.");
    }

    /**
     * Deletes a task from the task list.
     *
     * Removes the task at the specified index from the task list.
     *
     * @param index the index of the task to delete from the list
     * @throws SamException if the index is out of bounds
     */
    public void deleteTask(int index) throws SamException {
        if (index < 0 || index >= items.size()) {
            throw new SamException("Invalid task number. Please check how many tasks your have in the list.");
        }

        Task removedTask = items.remove(index);
        System.out.println("---------------------------");
        System.out.println("I've removed this task:");
        System.out.println(" " + removedTask);
        System.out.println("Now you have " + items.size() + " task(s) left in the list. ");
        System.out.println("---------------------------");
    }

<<<<<<< HEAD
    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matchList = new ArrayList<>();
        for (Task task : items) {
            if (task.containsKeyword(keyword)) {
                matchList.add(task);
            }
        }
        return matchList;
    }

=======
    /**
     * Retrieves the file strings from the Storage object.
     *
     * Returns an ArrayList containing the file strings stored in the Storage object.
     *
     * @return an ArrayList containing the file strings
     */
>>>>>>> branch-A-JavaDoc
    public ArrayList<String> getFileStrings() {
        ArrayList<String> ret = new ArrayList<>();
        for (Task item : items) {
            ret.add(item.toFileFormat());
        }
        return ret;
    }
}
