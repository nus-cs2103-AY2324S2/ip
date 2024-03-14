package judy.storage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import judy.task.Deadline;
import judy.task.Event;
import judy.task.Task;
import judy.task.TaskList;
import judy.task.Todo;

/**
 * The Decoder class is to decodes the storage into TaskList objects.
 */
public class Decoder {

    /**
     * Decodes an encoded list of tasks and returns a TaskList object.
     *
     * @param encodedTasks The encoded string containing task information.
     * @return The TaskList object containing decoded tasks.
     */
    public static TaskList decodeTasks(String encodedTasks) {
        String[] taskLines = encodedTasks.split("\n");
        TaskList decodedTaskList = new TaskList();

        for (String taskLine : taskLines) {
            Task decodedTask = decode(taskLine);
            decodedTaskList.add(decodedTask);
        }
        return decodedTaskList;
    }

    /**
     * Decodes an encoded task and returns the corresponding Task object.
     *
     * @param taskLine The encoded string containing information about a task.
     * @return The Task object representing the decoded task.
     */
    public static Task decode(String taskLine) {
        String[] parts = taskLine.split("\\|");
        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        Task decodedTask;

        switch (taskType) {
        case "T":
            decodedTask = new Todo(description);
            break;
        case "D":
            String by = parts[3].trim();
            LocalDateTime byDateTime = decodeDateTime(by);
            decodedTask = new Deadline(description, byDateTime);
            break;
        case "E":
            String[] eventTimings = parts[3].split(" - ");
            String from = eventTimings[0].trim();
            String to = eventTimings[1].trim();
            LocalDateTime fromDateTime = decodeDateTime(from);
            LocalDateTime toDateTime = decodeDateTime(to);
            decodedTask = new Event(description, fromDateTime, toDateTime);
            break;
        default:
            decodedTask = null;
        }
        decodedTask.setIsDone(isDone);
        return decodedTask;
    }

    /**
     * Decodes date time format stored in text file to Local Date Tie format.
     * @param dateTime The date time stored in a string format in text file.
     * @return Local Date Time that represents a specific date time.
     */
    private static LocalDateTime decodeDateTime(String dateTime) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return LocalDateTime.parse(dateTime, pattern);
    }

}
