package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the list of tasks in the Duke application.
 * It encapsulates operations to manipulate tasks such as adding, deleting, and marking tasks as done or not done.
 */
public class TaskList {
    /**
     * The list of tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList instance.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Loads tasks into the task list.
     *
     * @param taskList The list of tasks to be loaded.
     */
    public void loadTasks(ArrayList<Task> taskList) {
        this.tasks = taskList;
    }

    /**
     * Adds a new task to the task list and saves the updated list to storage.
     *
     * @param newTask The new task to add to the list.
     * @param storage The storage handler to save tasks after adding the new task.
     */
    public String addTask(Task newTask, Storage storage) {
        assert tasks != null : "Task list must not be null";
        tasks.add(newTask);
        try {
            storage.saveTasks(tasks);
        } catch(IOException e) {
            System.out.print(e);
        }

        return "Got it. I've added this task: \n" + newTask + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Deletes a task from the task list based on its number and saves the updated list to storage.
     *
     * @param taskNumber The number of the task to delete, based on its position in the task list.
     * @param storage The storage handler to save tasks after deleting the task.
     * @throws ChatbotException If the task number is out of bounds (less than 1 or greater than the number of tasks).
     */
    public String deleteTask(int taskNumber, Storage storage) throws ChatbotException {
        assert tasks != null : "Task list must not be null";
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new ChatbotException("Unknown task number. Please try again");
        }
        Task removedTask = tasks.remove(taskNumber - 1);
        try {
            storage.saveTasks(tasks);
        } catch(IOException e) {
            System.out.print(e);
        }

        return "Noted. I've removed this task: \n" + removedTask.toString() + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Marks a task as done or not done based on its number and saves the updated list to storage.
     *
     * @param taskNumber The number of the task to mark, based on its position in the task list.
     * @param isDone True to mark the task as done, false to mark it as not done.
     * @param storage The storage handler to save tasks after updating the task's status.
     * @throws ChatbotException If the task number is out of bounds (less than 1 or greater than the number of tasks).
     */
    public String markTask(int taskNumber, boolean isDone, Storage storage) throws ChatbotException {
        assert tasks != null : "Task list must not be null";
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            throw new ChatbotException("Unknown task number. Please try again");
        }
        Task task = tasks.get(taskNumber - 1);
        if (isDone) {
            task.markAsDone();
        } else {
            task.unmarkAsDone();
        }
        try {
            storage.saveTasks(tasks);
        } catch(IOException e) {
            System.out.print(e);
        }
        return "  " + task.toString();
    }

    /**
     * Prints all tasks in the task list to the console.
     */
    public String printTasks() {
        String finalString = "";
        for (int i = 0; i < tasks.size(); i++) {
            finalString += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return finalString;
    }

    /**
     * Finds and displays tasks that contain the given keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public String findTask(String keyword) {
        String finalString = "Here are the matching tasks in your list: \n";
        int matchCount = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task.getDescription().contains(keyword)) {
                finalString += (i + 1) + "." + task + "\n";
                matchCount++;
            }
        }
        if (matchCount == 0) {
            finalString = "No tasks match your search.";
        }
        return finalString;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}

