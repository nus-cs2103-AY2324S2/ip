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

    public boolean addTask(String input) {
        Task task = convertTask(input);
        if (task != null) {
            tasks.add(task);
            tasksCount++;
            return true;
        }
        return false;
    }

    public void deleteTask(int num) {
        tasks.remove(num);
        tasksCount--;
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

    public void listTask() {
        for (int i = 0; i < tasksCount; i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getDescription());
        }
    }

    public String getTaskDescription(int num) {
        return tasks.get(num).getDescription();
    }

    public void markTask(int num, boolean status) {
        tasks.get(num).mark(status);
    }

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
}
