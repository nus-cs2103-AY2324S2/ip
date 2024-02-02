package yoda.storage;
import yoda.task.*;
import yoda.datetimeutil.DateTimeUtil;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Storage {
    private final String filePath;
    private final Path path;


    public Storage(String filePath) {
        this.filePath = filePath;
        this.path = Paths.get(filePath);
    }

    public void saveTasks(TaskList taskList) throws IOException {
        List<String> taskStrings = taskList.stream()
                .map(this::taskToFileFormat)
                .collect(Collectors.toList());

        if (isFileContentDifferent(taskStrings)) {
            writeToFile(taskStrings);
        }
    }

    private boolean isFileContentDifferent(List<String> taskStrings) throws IOException {
        if (!Files.exists(path)) {
            Files.createDirectories(path.getParent());
            Files.createFile(path);
            return true;
        }

        List<String> fileContent = Files.readAllLines(path);
        return !fileContent.equals(taskStrings);
    }

    private void writeToFile(List<String> taskStrings) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String taskString : taskStrings) {
                writer.write(taskString + System.lineSeparator());
            }
        }
    }

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

    public TaskList loadTasks() throws IOException {
        List<Task> loadedTasks = new ArrayList<>();
        Path path = Paths.get(filePath);

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


    private Task fileToTaskFormat(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                return createTodoTask(isDone, description);
            case "D":
                return createDeadlineTask(isDone, description, parts[3]);
            case "E":
                return createEventTask(isDone, description, parts[3], parts[4]);
            default:
                return null;
        }
    }



    private Task createTodoTask(boolean isDone, String description) {
        Todo todo = new Todo(description);
        if (isDone) todo.markAsDone();
        return todo;
    }

    private Task createDeadlineTask(boolean isDone, String description, String by) {
        LocalDateTime byDateTime = DateTimeUtil.parseDateTime(by);
        Deadline deadline = new Deadline(description, byDateTime);
        if (isDone) deadline.markAsDone();
        return deadline;
    }

    private Task createEventTask(boolean isDone, String description, String from, String to) {
        LocalDateTime fromDateTime = DateTimeUtil.parseDateTime(from);
        LocalDateTime toDateTime = DateTimeUtil.parseDateTime(to);
        Event event = new Event(description, fromDateTime, toDateTime);
        if (isDone) event.markAsDone();
        return event;
    }


}
