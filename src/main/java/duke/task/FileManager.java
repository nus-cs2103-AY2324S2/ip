package duke.task;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The FileManager class handles the reading and writing of tasks to a file.
 * It manages the loading and saving of tasks to a user-specific file.
 */
public class FileManager {
    private final String filePath;
    private static final String BASE_PATH = "./data/users/";

    /**
     * Constructs a FileManager instance with the specified username.
     * Creates a user-specific directory to store the tasks file.
     *
     * @param username The username associated with the file manager.
     */
    public FileManager(String username) {
        File userDirectory = new File(BASE_PATH + username);
        if (!userDirectory.exists() && !userDirectory.mkdirs()) {
            System.out.println(" ");
        }
        this.filePath = BASE_PATH + username + "/duke.txt";
    }

    /**
     * Loads tasks from the file into the provided task list.
     *
     * @param tasks The list of tasks to load from the file.
     * @return The list of tasks after loading from the file.
     */
    public List<Task> loadTasks(List<Task> tasks) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            //
        }

        return tasks;
    }

    /**
     * Saves the provided tasks to the file.
     *
     * @param tasks The list of tasks to save to the file.
     */
    public void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Task task : tasks) {
                String status = task.isCompleted ? "1" : "0";
                String taskIcon = getTaskIcon(task);
                String taskDetails = getTaskDetails(task);

                writer.write(taskIcon + " | " + status + " | " + taskDetails + "\n");
            }
        } catch (IOException e) {
            //
        }
    }

    /**
     * Gets the task details in a formatted string based on the task type.
     *
     * @param task The task for which to get the details.
     * @return The formatted task details string.
     */
    private String getTaskDetails(Task task) {
        if (task instanceof Todo) {
            return ((Todo) task).getTaskDescription();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return event.getEventDescription() + " | " +
                    formatDateTime(event.getFrom()) + " | " + formatDateTime(event.getTo());
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return deadline.getDeadlineDescription() + " | " + formatDateTime(deadline.getBy());
        } else {
            return "";
        }
    }

    /**
     * Formats the given date and time in a specified pattern.
     *
     * @param dateTime The date and time to format.
     * @return The formatted date and time string.
     */
    private String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Parses a task line from the file and returns the corresponding Task instance.
     *
     * @param line The task line from the file.
     * @return The Task instance created from the task line.
     */
    private Task parseTaskLine(String line) {
        String regex = "\\[(T|D|E)\\] \\| (0|1) \\| (.+?)(?: \\| (.+?)(?: \\| (.+))?)?";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line);

        if (matcher.matches()) {
            String taskType = matcher.group(1);
            boolean isMarked = "1".equals(matcher.group(2));
            String taskDescription = matcher.group(3);

            switch (taskType) {
                case "T":
                    return new Todo(taskDescription, isMarked);
                case "D":
                    String deadlineDate = matcher.group(4);
                    LocalDateTime by = LocalDateTime.parse(deadlineDate,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    return new Deadline(taskDescription, isMarked, by);
                case "E":
                    String from = matcher.group(4);
                    String to = matcher.group(5);

                    LocalDateTime fromDate = LocalDateTime.parse(from,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                    LocalDateTime toDate = LocalDateTime.parse(to,
                            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                    return new Event(taskDescription, isMarked, fromDate, toDate);
                default:
                    System.err.println("Sorry, there's no such task in my system. " +
                            "Try these: todo, deadline, event");
                    break;
            }
        }

        return null;
    }

    /**
     * Parses the date and time from the specified string.
     *
     * @param dateTimeString The string representing the date and time.
     * @return The LocalDateTime instance parsed from the string.
     */
    private LocalDateTime parseDateTime(String dateTimeString) {
        return LocalDateTime.parse(dateTimeString,
                DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"));
    }

    /**
     * Gets the task icon based on the task type.
     *
     * @param task The task for which to get the icon.
     * @return The task icon associated with the task type.
     */
    private String getTaskIcon(Task task) {
        return task.getTaskIcon();
    }
}
