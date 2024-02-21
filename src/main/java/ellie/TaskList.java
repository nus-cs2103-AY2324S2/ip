package ellie;

import java.util.ArrayList;

import ellie.task.Task;

/**
 * The TaskList class represents a list of tasks and provides methods for managing tasks.
 * It interacts with the Storage class to load and save tasks.
 */
public class TaskList {

    private static final String NEW_LINE = "\n";
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
    public String addTask(Task element) {
        taskArrayList.add(element);
        saveTasks();

        String response = "";

        response += "Got it. I've added this task: " + NEW_LINE;
        response += "    " + element.listTaskString() + NEW_LINE;
        response += "Now you have " + this.taskQuantity() + " tasks in the list." + NEW_LINE;

        return response;
    }

    /**
     * Searches for tasks containing the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public String searchTask(String keyword) {

        String noResponse = "No matching tasks found." + NEW_LINE;

        ArrayList<Task> matchingTasks = new ArrayList<>();
        String response = NEW_LINE;

        for (Task task : taskArrayList) {
            if (task.toString().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            response += noResponse;
        } else {
            response += "Matching tasks: " + NEW_LINE;
            int index = 0;
            for (Task matchingTask : matchingTasks) {
                index++;
                response += "    " + index + ". " + matchingTask.listTaskString() + NEW_LINE;
            }
        }

        return response;
    }


    /**
     * Prints the list of tasks to the console.
     * If the list is empty, prints a message indicating that there are no items in the list.
     */
    public String listTasks() {
        String noTasksResponse = "No items in list!" + NEW_LINE;

        String response = NEW_LINE;

        if (taskArrayList.isEmpty()) {
            response += noTasksResponse;
            return response;
        }

        int index = 0;
        response += "Here are your tasks!" + NEW_LINE;
        for (Task element : taskArrayList) {
            index++;
            response += "    " + index + ". " + element.listTaskString() + NEW_LINE;
        }
        return response;
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
     * Marks a task as done at the specified index, saves the updated list to storage,
     * and prints a confirmation message.
     *
     * @param index The index of the task to be marked as done.
     */
    public String markTaskIndex(int index) {
        final String invalidIndexResponse = "Sorry!" + NEW_LINE
                                            + "There doesn't seem to be enough tasks for there to be a task "
                                                + index + "!" + NEW_LINE;
        String response = NEW_LINE;

        if (index > taskArrayList.size()) {
            response += invalidIndexResponse;
            return response;
        }

        Task task = taskArrayList.get(index - 1);
        task.markTask();
        saveTasks();

        response += "Nice! I've marked this task as done:" + NEW_LINE;
        response += "  " + task.listTaskString() + NEW_LINE;
        return response;
    }

    /**
     * Unmarks a task as done at the specified index, saves the updated list to storage,
     * and prints a confirmation message.
     *
     * @param index The index of the task to be unmarked as done.
     */
    public String unmarkTaskIndex(int index) {
        String response = NEW_LINE;

        if (index > taskArrayList.size()) {
            response += "Sorry!" + NEW_LINE
                        + "There doesn't seem to be enough tasks for there to be a task "
                            + index + "!" + NEW_LINE;
            return response;
        }

        Task task = taskArrayList.get(index - 1);
        task.unmarkTask();
        saveTasks();

        response += "Nice! I've marked this task as not done yet:" + NEW_LINE;
        response += "  " + task.listTaskString() + NEW_LINE;
        return response;
    }

    /**
     * Deletes a task at the specified index, saves the updated list to storage, and prints a confirmation message.
     *
     * @param index The index of the task to be deleted.
     */
    public String deleteTaskIndex(int index) {
        String response = NEW_LINE;

        if (index > taskArrayList.size()) {
            response += "Sorry!" + NEW_LINE
                        + "There doesn't seem to be enough tasks for there to be a task "
                            + index + "!" + NEW_LINE;
            return response;
        }

        Task removedTask = taskArrayList.remove(index - 1);
        saveTasks();

        response += "Got it! I've removed this task from your list:" + NEW_LINE;
        response += "  " + removedTask.listTaskString() + NEW_LINE;
        return response;
    }

    /**
     * Saves the current task list to storage.
     */
    private void saveTasks() {
        storage.save(taskArrayList);
    }


}
