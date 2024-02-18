package duke;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Save class manages the saving and loading of task data to and from a file.
 */
public class Save {

    /**
     * The file path for saving and loading data.
     */
    private String filePath = "data/duke.txt";

    /**
     * Constructs a new Save instance with a specified file path.
     *
     * @param f The file path.
     */
    public Save(String f) {
        this.filePath = f;
    }

    /**
     * Saves the data from the given storage to the specified file path.
     *
     * @param s The storage containing tasks to be saved.
     */
    public void saveData(Storage s) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < s.size(); i++) {
                Task t = s.get(i);
                String serializedTask = serializeTask(t);
                writer.write(serializedTask);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong with writing file: " + e.getMessage());
        }
    }

    /**
     * Serializes a task object into a string representation.
     *
     * @param t The task to be serialized.
     * @return A string representation of the task.
     */
    private String serializeTask(Task t) {
        String isDone = t.isDone ? "1" : "0";
        String desc = t.description;
        if (t instanceof ToDo) {
            return "T | " + isDone + " | " + desc;
        } else if (t instanceof Deadline) {
            return serializeDeadline((Deadline) t, isDone, desc);
        } else if (t instanceof Event) {
            return serializeEvent((Event) t, isDone, desc);
        } else {
            throw new IllegalArgumentException("Unsupported task type: " + t.getClass().getSimpleName());
        }
    }

    private String serializeDeadline(Deadline d, String isDone, String desc) {
        // Assuming Deadline class has a method to get its deadline in the desired format
        String deadline = formatDateTime(d.getDeadline());
        return "D | " + isDone + " | " + desc + " | " + deadline;
    }

    private String serializeEvent(Event e, String isDone, String desc) {
        // Assuming Event class has methods to get its start and end times in the desired format
        String from = formatDateTime(e.getFrom());
        String to = formatDateTime(e.getTo());
        return "E | " + isDone + " | " + desc + " | " + from + " | " + to;
    }

    private String formatDateTime(LocalDateTime dateTime) {
        // You might need to adjust the format pattern according to your requirements
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
        return dateTime.format(formatter);
    }

    /**
     * Loads data from the file specified by the file path into the given storage.
     *
     * @param s The storage where the loaded tasks will be stored.
     */
    public void loadData(Storage s) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                String data = new String(buffer, 0, bytesRead);
                processTasksData(data, s);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private void processTasksData(String data, Storage s) {
        String[] tasks = data.split("\n");
        for (String task : tasks) {
            processTask(task, s);
        }
    }

    private void processTask(String taskData, Storage s) {
        String[] parts = taskData.split("\\s*\\|\\s*");
        boolean isDone = parts[1].equals("1");
        switch (parts[0]) {
        case "T":
            processToDoTask(parts[2], isDone, s);
            break;
        case "E":
            processEventTask(parts, isDone, s);
            break;
        case "D":
            processDeadlineTask(parts, isDone, s);
            break;
        default:
            System.out.println("Unknown task type: " + parts[0]);
        }
    }

    private void processToDoTask(String description, boolean isDone, Storage s) {
        ToDo t = new ToDo(description);
        if (isDone) {
            t.markAsDone();
        }
        s.add(t);
    }

    private void processEventTask(String[] parts, boolean isDone, Storage s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
        LocalDateTime start = LocalDateTime.parse(parts[3], formatter);
        LocalDateTime end = LocalDateTime.parse(parts[4], formatter);
        Event e = new Event(parts[2], start, end);
        if (isDone) {
            e.markAsDone();
        }
        s.add(e);
    }

    private void processDeadlineTask(String[] parts, boolean isDone, Storage s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
        LocalDateTime deadline = LocalDateTime.parse(parts[3], formatter);
        Deadline d = new Deadline(parts[2], deadline);
        if (isDone) {
            d.markAsDone();
        }
        s.add(d);
    }
}
