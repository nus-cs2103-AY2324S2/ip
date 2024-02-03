package ellie;
import ellie.task.Task;
import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks and provides methods for managing tasks.
 * It interacts with the Storage class to load and save tasks.
 */
public class TaskList {

    private final ArrayList<Task> taskArrayList;
    private final Storage storage;

    /**
     * Constructs a TaskList object with the specified Storage.
     * Loads tasks from the Storage during initialization.
     *
     * @param storage The Storage object used for loading and saving tasks.
     */
    public TaskList(Storage storage) {
        taskArrayList = new ArrayList<>();
        this.storage = storage;
        storage.load(taskArrayList);

    }

    /**
     * Adds a task to the task list, saves the updated list to storage, and prints a confirmation message.
     *
     * @param element The Task object to be added to the list.
     */
    public void addTask(Task element) {
        taskArrayList.add(element);
        saveTasks();

        System.out.println("Got it. I've added this task:");
        System.out.println("    " + element.listTaskString());
        System.out.println("Now you have " + this.taskQuantity() + " tasks in the list.\n");
    }

    /**
     * Prints the list of tasks to the console.
     * If the list is empty, prints a message indicating that there are no items in the list.
     */
    public void listTasks() {
        if (taskArrayList.isEmpty()) {
            System.out.println("No items in list!\n");
            return;
        }

        int index = 0;
        System.out.println("Here are your tasks!");
        for (Task element : taskArrayList) {
            index++;
            System.out.println("    " + index + "." + element.listTaskString());
        }
        System.out.print("\n");
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the list.
     */
    public int taskQuantity() {
        return taskArrayList.size();
    }

    /**
     * Marks a task as done at the specified index, saves the updated list to storage, and prints a confirmation message.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markTaskIndex(int index) {
        if (index > taskArrayList.size()) {
            System.out.println("Sorry! There doesn't seem to be enough tasks for there to be a task " + index + "!\n");
            return;
        }

        Task task = taskArrayList.get(index - 1);
        task.markTask();
        saveTasks();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task.listTaskString() + "\n");

    }

    /**
     * Unmarks a task as done at the specified index, saves the updated list to storage, and prints a confirmation message.
     *
     * @param index The index of the task to be unmarked as done.
     */
    public void unmarkTaskIndex(int index) {
        if (index > taskArrayList.size()) {
            System.out.println("Sorry! There doesn't seem to be enough tasks for there to be a task " + index + "!\n");
            return;
        }

        Task task = taskArrayList.get(index - 1);
        task.unmarkTask();
        saveTasks();

        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println("  " + task.listTaskString() + "\n");

    }

    /**
     * Deletes a task at the specified index, saves the updated list to storage, and prints a confirmation message.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTaskIndex(int index) {
        if (index > taskArrayList.size()) {
            System.out.println("Sorry! There doesn't seem to be enough tasks for there to be a task " + index + "!\n");
            return;
        }

        Task removedTask = taskArrayList.remove(index - 1);
        saveTasks();
        System.out.println("Got it! I've removed this task from your list:");
        System.out.println("  " + removedTask.listTaskString() + "\n");


    }

    /**
     * Saves the current task list to storage.
     */
    private void saveTasks() {
        storage.save(taskArrayList);
    }


}
