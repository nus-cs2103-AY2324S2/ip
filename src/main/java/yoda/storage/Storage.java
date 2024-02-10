package yoda.storage;
import yoda.task.Task;
import yoda.task.TaskList;
import yoda.task.Todo;
import yoda.task.Deadline;
import yoda.task.Event;
import yoda.datetimeutil.DateTimeUtil;


import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

/** Represents the storage for tasks. */
public class Storage {
    private final String FILE_PATH;
    private final Path PATH;


    /**
     * Constructs a new Storage object.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.FILE_PATH = filePath;
        this.PATH = Paths.get(filePath);
    }

    /**
     * Saves the tasks to the file.
     * @param taskList The list of tasks to save.
     * @throws IOException If an I/O error occurs.
     */
    public void saveTasks(TaskList taskList) throws IOException {
        List<String> taskStrings = taskList.stream()
                .map(this::taskToFileFormat)
                .collect(Collectors.toList());

        if (isFileContentDifferent(taskStrings)) {
            writeToFile(taskStrings);
        }
    }

    /**
     * Checks if the file content is different from the given list of task strings.
     * @param taskStrings The list of task strings to compare with the file content.
     * @return true if the file content is different, false otherwise.
     * @throws IOException If an I/O error occurs.
     */
    private boolean isFileContentDifferent(List<String> taskStrings) throws IOException {
        if (!Files.exists(PATH)) {
            Files.createDirectories(PATH.getParent());
            Files.createFile(PATH);
            return true;
        }

        List<String> fileContent = Files.readAllLines(PATH);
        return !fileContent.equals(taskStrings);
    }

    /**
     * Writes a list of task strings to the file.
     *
     * @param taskStrings The list of task strings to be written.
     * @throws IOException If an I/O error occurs.
     */
    private void writeToFile(List<String> taskStrings) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String taskString : taskStrings) {
                writer.write(taskString + System.lineSeparator());
            }
        }
    }

    /**
     * Converts a Task object into a string format suitable for file storage.
     *
     * @param task The task to be converted.
     * @return The task in string format.
     */
    public String taskToFileFormat(Task task) {
        String status = task.isDone() ? "1" : "0";
        String type = task instanceof Todo ? "T" :
                task instanceof Deadline ? "D" :
                        task instanceof Event ? "E" : "";
        String details = task.getDescription();

        if (task instanceof Deadline) {
            Deadline deadlineTask = (Deadline) task;
            details += " | " + deadlineTask.getByString();
        } else if (task instanceof Event) {
            Event eventTask = (Event) task;
            details += " | " + eventTask.getFromString() + " to " + eventTask.getToString();
        }
        return type + " | " + status + " | " + details;
    }

    /**
     * Loads tasks from the file and returns them as a TaskList.
     *
     * @return The list of tasks loaded from the file.
     * @throws IOException If an I/O error occurs.
     */
    public TaskList loadTasks() throws IOException {
        List<Task> loadedTasks = new ArrayList<>();
        Path path = Paths.get(FILE_PATH);

        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
        }

        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = fileToTaskFormat(line);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
        }
        return new TaskList(loadedTasks);
    }

    /**
     * Converts a line from the file into a Task object.
     *
     * @param line The line from the file to be converted.
     * @return The task represented by the line.
     */
    private Task fileToTaskFormat(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) return null; // Basic validation

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            return createTodoTask(isDone, description);
        case "D":
            if (parts.length < 4) return null; // Additional validation for deadlines
            return createDeadlineTask(isDone, description, parts[3]);
        case "E":
            if (parts.length < 5) return null; // Additional validation for events
            return createEventTask(isDone, description, parts[3], parts[4]);
        default:
            return null;
        }
    }


    /**
     * Creates a Todo task from the given parameters.
     *
     * @param isDone      Whether the task is done.
     * @param description The description of the task.
     * @return The Todo task created.
     */
    private Task createTodoTask(boolean isDone, String description) {
        Todo todo = new Todo(description);
        if (isDone) todo.markAsDone();
        return todo;
    }

    /**
     * Creates a Deadline task from the given parameters.
     *
     * @param isDone      Whether the task is done.
     * @param description The description of the task.
     * @param by          The deadline of the task.
     * @return The Deadline task created.
     */
    private Task createDeadlineTask(boolean isDone, String description, String by) {
        LocalDateTime byDateTime = DateTimeUtil.parseDateTime(by);
        Deadline deadline = new Deadline(description, byDateTime);
        if (isDone) deadline.markAsDone();
        return deadline;
    }

    /**
     * Creates an Event task from the given parameters.
     *
     * @param isDone      Whether the task is done.
     * @param description The description of the task.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     * @return The Event task created.
     */
    private Task createEventTask(boolean isDone, String description, String from, String to) {
        LocalDateTime fromDateTime = DateTimeUtil.parseDateTime(from);
        LocalDateTime toDateTime = DateTimeUtil.parseDateTime(to);
        Event event = new Event(description, fromDateTime, toDateTime);
        if (isDone) event.markAsDone();
        return event;
    }


}
