package judy.storage;

import judy.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Decoder {


    public static TaskList decodeTasks(String encodedTasks) {
        String[] taskLines = encodedTasks.split("\n");
        TaskList decodedTaskList = new TaskList();

        for (String taskLine : taskLines) {
            Task decodedTask = decode(taskLine);
            decodedTaskList.add(decodedTask);
        }
        return decodedTaskList;
    }
    public static Task decode(String taskLine) {
        String[] parts = taskLine.split("\\|");
        String taskType = parts[0].trim();
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();
        Task decodedTask;
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");

        switch (taskType) {
            case "T":
                decodedTask = new Todo(description);
                break;
            case "D":
                String by = parts[3].trim();
                LocalDateTime byDateTime = LocalDateTime.parse(by, pattern);
                decodedTask = new Deadline(description, byDateTime);
                break;
            case "E":
                String[] eventTimings = parts[3].split(" - ");
                String from = eventTimings[0].trim();
                String to = eventTimings[1].trim();
                LocalDateTime fromDateTime = LocalDateTime.parse(from, pattern);
                LocalDateTime toDateTime = LocalDateTime.parse(to, pattern);
                decodedTask = new Event(description, fromDateTime, toDateTime);
                break;
            default:
                decodedTask = null;
        }
        decodedTask.setIsDone(isDone);
        return decodedTask;
    }

}
