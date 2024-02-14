package whisper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Storage {
    private String FILE_PATH;

    public Storage(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
    }

    public ArrayList<Task> load() throws WhisperException {
        ArrayList<Task> tasks = new ArrayList<>();

        try (Scanner sc = new Scanner(new File(FILE_PATH))) {
            while (sc.hasNextLine()) {
                String input = sc.nextLine().trim();
                Task task = parseTaskFromString(input);
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new WhisperException("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    public void saveFile(ArrayList<Task> tasks) throws WhisperException {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                fw.write(taskToFormattedString(task) + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new WhisperException("Error saving tasks to file: " + e.getMessage());
        }
    }

    String taskToFormattedString(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.getTaskCat().name()).append(" | ")
                .append(task.isDone? "1" : "0").append(" | ")
                .append(task.getDescription());

        switch (task.getTaskCat()) {
        case D:
            sb.append(" | ").append(task.getDeadline().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            break;
        case E:
            sb.append(" | ")
                    .append(task.getEventFromDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")))
                    .append("-")
                    .append(task.getEventToDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            break;
        }
        return sb.toString();
    }

    Task parseTaskFromString(String input) throws WhisperException {
        try {
            String[] parts = input.split("\\|");

            if (parts.length < 3) {
                throw new WhisperException("Invalid file format.");
            }

            String taskType = parts[0].trim();
            int isDone = Integer.parseInt(parts[1].trim());
            String description = parts[2].trim();

            Task task;

            switch (taskType) {
            case "T":
                task = new Task(description, Task.TaskCategory.T);
                break;
            case "D":
                if (parts.length < 4) {
                    throw new WhisperException("Invalid file format.");
                }
                String by = parts[3].trim();
                LocalDateTime deadlineDateTime = parseDateTime(by);
                task = new Task(description, Task.TaskCategory.D, deadlineDateTime);
                break;
            case "E":
                if (parts.length < 4) {
                    throw new WhisperException("Invalid file format.");
                }
                String dateTime = parts[3].trim();
                String[] timeParts = dateTime.split("-");

                if (timeParts.length == 2) {
                    String from = timeParts[0];
                    String to = timeParts[1];
                    LocalDateTime fromDateTime = parseDateTime(from);
                    LocalDateTime toDateTime = parseDateTime(to);

                    task = new Task(description, Task.TaskCategory.E, fromDateTime, toDateTime);
                } else {
                    throw new WhisperException("Invalid file format.");
                }
                break;
            default:
                throw new WhisperException("Invalid file format.");
            }

            if (isDone == 1) {
                task.markAsDone();
            }
            return task;
        } catch (WhisperException | NumberFormatException | IndexOutOfBoundsException e) {
            throw new WhisperException("Error parsing task from file.");
        }
    }

    LocalDateTime parseDateTime(String dateTime) throws WhisperException {
        try {
            return LocalDateTime.parse(dateTime.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        } catch (DateTimeParseException e) {
            throw new WhisperException("Invalid date time format. Please follow the format: dd/MM/yyyy HH:mm");
        }
    }
}