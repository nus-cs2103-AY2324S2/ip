import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskRepository {
    private final String FILE_PATH = "./data/taskStorage.txt";

    public void saveTasks(TaskList taskList) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Task task : taskList.getTaskList()) {
            String encodedTask = "";
            try {
                encodedTask = encodeTask(task.toString());
            } catch (BotException e) {
                e.printStackTrace();
            }
            lines.add(encodedTask);
        }
        Path filePath = Paths.get(FILE_PATH);
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        } else if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        Files.write(filePath, lines);
    }

    public TaskList loadTasks() throws IOException, BotException {
        System.out.println("Loading tasks from file...");
        TaskList taskList = new TaskList();
        Path filePath = Paths.get(FILE_PATH);
        if (Files.exists(filePath)) {
            List<String> lines = Files.readAllLines(filePath);
            for (String line : lines) {
                String decodedTask = "";
                try {
                    decodedTask = decodeTaskString(line);
                } catch (BotException e) {
                    e.printStackTrace();
                }
                addTask(taskList, decodedTask);
            }
        } else {
            System.out.println("No existing task file found. Starting with an empty task list.");
        }
        return taskList;
    }

    private void addTask(TaskList tasklist, String task) {
        // get the 1st index
        String taskType = task.substring(1, 2);
        if (taskType.equals("T")) {
            String taskDescription = task.substring(4, task.length() - 1);
            tasklist.addTodo(taskDescription);
        } else if (taskType.equals("D")) {
            String taskDescription = task.substring(7, task.indexOf("(by:") - 1);
            String taskDeadline = task.substring(task.indexOf("(by:") + 5, task.indexOf(")"));
            tasklist.addDeadline(taskDescription, taskDeadline);
        } else if (taskType.equals("E")) {
            String taskDescription = task.substring(7, task.indexOf("(from:") - 1);
            String taskStartTime = task.substring(task.indexOf("(from:") + 7, task.indexOf("to:") - 1);
            String taskEndTime = task.substring(task.indexOf("to:") + 4, task.indexOf(")"));
            tasklist.addEvent(taskDescription, taskStartTime, taskEndTime);
        }
    }

    private String encodeTask(String taskString) throws BotException {
        // Define the pattern for task string
        Pattern pattern = Pattern.compile(
                "\\s*\\d+\\.\\[([TDE])\\]\\[(\\s*X\\s*)?\\]\\s*(.*?)(?:\\s*\\(by:\\s*(.*?)\\))?(?:\\s*\\(from:\\s*(.*?)\\s+to:\\s*(.*?)\\))?\\s*");
        Matcher matcher = pattern.matcher(taskString);

        // Check if the task string matches the pattern
        if (!matcher.matches()) {
            throw new BotException("Invalid task string format: " + taskString);
        }

        // Extract the task details
        String type = matcher.group(1);
        String isCompleted = matcher.group(2).isEmpty() ? "0" : "1";
        String taskDescription = matcher.group(3).trim();
        String deadline = matcher.group(4);
        String eventStart = matcher.group(5);
        String eventEnd = matcher.group(6);

        // Build the encoded task string
        StringBuilder encodedTask = new StringBuilder();
        encodedTask.append(type).append(" | ").append(isCompleted).append(" | ").append(taskDescription);

        // Add deadline or event details if present
        if (deadline != null) {
            encodedTask.append(" | ").append(deadline);
        } else if (eventStart != null && eventEnd != null) {
            encodedTask.append(" | ").append(eventStart).append(" ").append(eventEnd);
        }

        return encodedTask.toString();
    }

    // decode a task string to convert to user readable format
    private String decodeTaskString(String taskString) throws BotException {
        String[] parts = taskString.split(" \\| ");
        if (parts.length < 3) {
            throw new BotException("Invalid task string format: " + taskString);
        }

        String taskType = parts[0];
        int taskNumber = Integer.parseInt(parts[0]); // Assuming task number matches original order
        String isCompleted = parts[1].equals("1") ? "X" : " ";
        String description = parts[2].trim();

        StringBuilder decodedString = new StringBuilder();
        decodedString.append(taskNumber).append(".").append("[").append(taskType).append("][").append(isCompleted)
                .append("] ");
        decodedString.append(description);

        if (parts.length >= 4) {
            String extraInfo = parts[3];
            if (extraInfo.contains("-")) {
                decodedString.append(" (from: ").append(extraInfo).append(")");
            } else {
                decodedString.append(" (by: ").append(extraInfo).append(")");
            }
        }

        return decodedString.toString();
    }
}