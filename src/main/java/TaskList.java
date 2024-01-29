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

        return createTask(inputs[0], inputs[1]);
    }

    public Task createTask(String command, String description) {
        if (command.startsWith("todo")) {
            return new ToDo(description);

        } else if (command.startsWith("deadline")) {
            String[] parts = description.split("/by", 2);
            LocalDateTime by = DateParser.parseDateTime(parts[1].trim());
            return new Deadline(parts[0], by);

        } else {
            String[] parts = description.split("\\s+/from\\s+|\\s+/to\\s+");
            LocalDateTime from = DateParser.parseDateTime(parts[1].trim());
            LocalDateTime to = DateParser.parseDateTime(parts[2].trim());
            return new Event(parts[0], from, to);

        }
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
                writer.println(taskToFileString(task));
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }
    private void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(fileStringToTask(line));
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

    private String taskToFileString(Task task) {
        StringBuilder sb = new StringBuilder();

        // Append the type of the task
        if (task instanceof ToDo) {
            sb.append("T");
        } else if (task instanceof Deadline) {
            sb.append("D");
        } else if (task instanceof Event) {
            sb.append("E");
        }

        // Append the done status
        sb.append(" | ").append(task.getStatus() ? "1 | " : "0 | ").append(task.getInitialDesc());

        // Append the deadline or event time if applicable
        if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            sb.append(" | ").append(deadline.getBy()); // Append the 'by' time for deadlines
        } else if (task instanceof Event) {
            Event event = (Event) task;
            sb.append(" | ").append(event.getFrom()).append(" - ").append(event.getTo()); // Append the 'from - to' times for events
        }

        return sb.toString();
    }


    private Task fileStringToTask(String fileString) {
        String[] parts = fileString.split(" \\| ");
        if (parts.length < 3) {
            ChatbotException.getError(ChatbotException.ErrorType.TASK_CORRUPT);
        }

        String type = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2];

        Task task = new Task(description);

        switch (type) {
            case "T":
                task = createTask("todo", description);
                break;
            case "D":
                if (parts.length < 4) ChatbotException.getError(ChatbotException.ErrorType.TODO_CORRUPT);
                description += " /by " + parts[3];
                task = createTask("deadline", description);
                break;
            case "E":
                String timeInfo = parts[3].trim();
                String[] timeParts = timeInfo.split(" - ");
                if (timeParts.length < 2) ChatbotException.getError(ChatbotException.ErrorType.EVENT_CORRUPT);
                description += " /from " + timeParts[0].trim() + " /to " + timeParts[1].trim();
                task = createTask("event", description);
                break;
            default:
                ChatbotException.getError(ChatbotException.ErrorType.UNKNOWN_TASK);
        }

        task.mark(isDone);
        return task;
    }
}
