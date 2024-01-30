import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    protected static int tasksCount = 0;

    private static final String FILE_PATH = "./data/duke.txt";

    public TaskList() {
        this.tasks = new ArrayList<>();
        ensureFileExists();
        loadTasks();
    }

    private void ensureFileExists() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Ensure the directory exists
                file.createNewFile(); // Create the file if it doesn't exist
            }
        } catch (IOException e) {
            System.out.println("An error occurred while ensuring the task file exists.");
        }
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
            System.out.println((i + 1)+ ". " + tasks.get(i).getDescription());
        }
    }

    public String getTaskDescription(int num) {
        return tasks.get(num).getDescription();
    }

    public void markTask(int num, boolean status) {
        tasks.get(num).mark(status);
    }

    public void saveTasks() {
        try (PrintWriter writer = new PrintWriter(new File(FILE_PATH))) {
            for (Task task : tasks) {
                writer.println(Storage.taskToFileString(task));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }
    private void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Storage.fileStringToTask(line));
            }
            tasksCount = tasks.size(); // Ensure the count reflects loaded tasks
        } catch (FileNotFoundException e) {
            System.out.println("Task file not found. A new file will be created.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks.");
        } catch (Exception e) {
            System.out.println("Task file is corrupted.");
        }
    }
}
