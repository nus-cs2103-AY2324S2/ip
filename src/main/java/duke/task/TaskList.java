package duke.task;

import java.util.ArrayList;

import duke.core.ChatbotException;
import duke.storage.Storage;

/**
 * Represents a TaskList that stores all tasks specified by user.
 */
public class TaskList {
    public static int tasksCount = 0;

    private ArrayList<Task> tasks;

    /**
     * Initialises a TaskList with an array to store the tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        Storage.ensureFileExists();
        Storage.loadTasks(tasks);
    }

    /**
     * Adds user-specified task into the tasklist
     *
     * @param input Task to be added into the tasklist.
     * @return Boolean if it's a valid task.
     */
    public boolean addTask(String input) {
        Task task = convertTask(input);
        if (task != null) {
            tasks.add(task);
            tasksCount++;
            return true;
        }
        return false;
    }

    /**
     * Deletes user-specified task from the tasklist
     *
     * @param num Number associated with the task.
     */
    public void deleteTask(int num) {
        assert num <= tasksCount : "Task number does not exist";
        tasks.remove(num);
        tasksCount--;
    }

    /**
     * Creates Task while ensuring user input is valid
     *
     * @param input User-specified task.
     * @return Task if valid user input, else return null.
     */
    public Task convertTask(String input) {

        if (input.trim().isEmpty()) {
            ChatbotException.getError(ChatbotException.ErrorType.UNKNOWN_COMMAND);
            return null;
        }

        String[] inputs = input.split("\\s+", 2);
        if (inputs.length < 2 || inputs[1].trim().isEmpty()) {
            catchInputError(inputs[0]);
            return null;
        }

        return Storage.createTask(inputs[0], inputs[1]);
    }

    /**
     * Lists all tasks stored in tasklist
     */
    public String listTask() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasksCount; i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i).getDescription()).append("\n");
        }
        return sb.toString();
    }

    /**
     * Updates status of the user-specified task
     *
     * @param num Number associated with the task.
     * @param status Status if the task is done or not done.
     */
    public void markTask(int num, boolean status) {
        assert num <= tasksCount : "Task number does not exist";
        tasks.get(num).mark(status);
    }

    /**
     * Finds all tasks associated with the given keyword
     *
     * @param keyword User-specified keyword
     */
    public String findTasks(String keyword) {
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> foundTasks = new ArrayList<>();

        // Search for tasks containing the keyword
        for (Task task : tasks) {
            if (task.getInitialDesc().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(task);
            }
        }

        // Build the string of found tasks
        for (int i = 0; i < foundTasks.size(); i++) {
            sb.append(i + 1).append(". ").append(foundTasks.get(i).getDescription()).append("\n");
        }

        return sb.toString();
    }


    public String getTaskDescription(int num) {
        assert num <= tasksCount : "Task number does not exist";
        return tasks.get(num).getDescription();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    private void catchInputError(String command) {
        switch (command.toLowerCase()) {
        case "todo":
            ChatbotException.getError(ChatbotException.ErrorType.TODO_EMPTY);
            break;
        case "deadline":
            ChatbotException.getError(ChatbotException.ErrorType.DEADLINE_EMPTY);
            break;
        case "event":
            ChatbotException.getError(ChatbotException.ErrorType.EVENT_EMPTY);
            break;
        default:
            ChatbotException.getError(ChatbotException.ErrorType.UNKNOWN_COMMAND);
        }
    }
}
