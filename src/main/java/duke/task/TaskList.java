package duke.task;

import duke.storage.Storage;
import duke.core.ChatbotException;

import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> tasks;

    public static int tasksCount = 0;

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
    public void listTask() {
        for (int i = 0; i < tasksCount; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getDescription());
        }
    }

    /**
     * Updates status of the user-specified task
     *
     * @param num Number associated with the task.
     * @param status Status if the task is done or not done.
     */
    public void markTask(int num, boolean status) {
        tasks.get(num).mark(status);
    }

    /**
     * Finds all tasks associated with the given keyword
     *
     * @param keyword User-specified keyword
     */
    public void findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (Task task : tasks) {
            if (task.getInitialDesc().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.add(task);
            }
        }

        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.println((i + 1) + ". " + foundTasks.get(i).getDescription());
        }
    }

    public String getTaskDescription(int num) {
        return tasks.get(num).getDescription();
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
