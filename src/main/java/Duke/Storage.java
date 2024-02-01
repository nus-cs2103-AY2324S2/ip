package Duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasksToFile(TaskList tasks) throws IOException {
        Path filePath = Paths.get(this.filePath);

        // Create the parent directory if it doesn't exist
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        List<String> encodedTasks = TaskListEncoder.encode(tasks.getTasks());
        // Write tasks to the file
        Files.write(filePath, encodedTasks);
    }

    public TaskList loadTasksFromFile() throws IOException, DukeDataCorruptedException {
        Path filePath = Paths.get(this.filePath);

        TaskList tasks;

        // Check if the file exists before attempting to load
        if (Files.exists(filePath)) {
            try {
                // Read tasks from the file
                List<String> lines = Files.readAllLines(filePath);
                tasks = new TaskList(TaskListDecoder.decode(lines));
            } catch (FileNotFoundException e) {
                // If the file is not found, throw a more specific exception
                throw new FileNotFoundException("Data file not found");
            } catch (IOException e) {
                throw new IOException("Error reading tasks from the file.", e);
            } catch (DukeDataCorruptedException e) {
                // Rethrow other exceptions, including DukeDataCorruptedException
                throw e;
            }
        } else {
            tasks = new TaskList(); // Initialize with an empty list if the file doesn't exist yet
        }

        return tasks;
    }

    public void ensureDataFileExists() throws IOException {
        Path filePath = Paths.get(this.filePath);

        // Check if the file exists or create it
        if (!Files.exists(filePath)) {
            // Check if the parent directory exists, create it if not
            if (!Files.exists(filePath.getParent())) {
                Files.createDirectories(filePath.getParent());
            }

            // Create the data file
            Files.createFile(filePath);
        }
    }
}


class TaskListEncoder {
    public static List<String> encode(List<Task> tasks) {
        List<String> encodedTasks = new ArrayList<>();
        for (Task task : tasks) {
            encodedTasks.add(encodeTask(task));
        }
        return encodedTasks;
    }

    private static String encodeTask(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            String by = deadlineTask.getBy().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return "D | " + (task.isDone() ? "1" : "0") + " | " + deadlineTask.getDescription() + " | " + "by " + by;
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            String from = eventTask.getFrom().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String to = eventTask.getTo().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return "E | " + (task.isDone() ? "1" : "0") + " | " + eventTask.getDescription() + " | " + from + " to " + to;
        } else {
            // Handle other task types if needed
            return "";
        }
    }
}

class TaskListDecoder {
    public static List<Task> decode(List<String> lines) throws DukeDataCorruptedException {
        List<Task> decodedTasks = new ArrayList<>();
        try {
            for (String line : lines) {
                decodedTasks.add(decodeTask(line));
            }
        } catch (IllegalArgumentException e) {
            throw new DukeDataCorruptedException("Data file is corrupted: " + e.getMessage());
        }

        return decodedTasks;
    }


    private static Task decodeTask(String line) {
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1"); // Check if the task is marked as done
        String description = parts[2];

        switch (taskType) {
            case "T":
                Task newTodo = new Todo(description);
                newTodo.setDone(isDone);
                return newTodo;
            case "D":
                String by = parts[3];
                LocalDate byDateTime = LocalDate.parse(by.substring(3), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                Task newDeadline = new Deadline(description, byDateTime);
                newDeadline.setDone(isDone);
                return newDeadline;
            case "E":
                String dateTimeString = parts[3];
                String[] dateTimeParts = dateTimeString.split(" to ");
                String from = dateTimeParts[0];
                String to = dateTimeParts[1];

                LocalDate fromDateTime = LocalDate.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate toDateTime = LocalDate.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                Task newEvent =  new Event(description, fromDateTime, toDateTime);
                newEvent.setDone(isDone);
                return newEvent;
            default:
                // Handle other task types if needed
                return null;
        }
    }
}

