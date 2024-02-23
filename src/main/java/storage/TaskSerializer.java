package storage;

import java.util.List;

import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

public class TaskSerializer {
    private final static String DELIMITER = ", ";

    // localDateTime --> String in 12/02/2023 1800 format
    public static String serialize(List<Task> tasks) {
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            String[] taskFields = {"", "", "", "", ""};
            if (t instanceof Todo) {
                Todo todo = (Todo) t;
                taskFields[0] = "T";
                taskFields[1] = todo.getStatus() ? "1" : "0";
                taskFields[2] = todo.getDescription();
            } else if (t instanceof Deadline) {
                Deadline deadline = (Deadline) t;
                taskFields[0] = "D";
                taskFields[1] = deadline.getStatus() ? "1" : "0";
                taskFields[2] = deadline.getDescription();
                taskFields[4] = deadline.getDueDate().format(Task.INPUT_DATE_FORMAT);
            } else if (t instanceof Event) {
                Event event = (Event) t;
                taskFields[0] = "E";
                taskFields[1] = event.getStatus() ? "1" : "0";
                taskFields[2] = event.getDescription();
                taskFields[3] = event.getStartDate().format(Task.INPUT_DATE_FORMAT);
                taskFields[4] = event.getEndDate().format(Task.INPUT_DATE_FORMAT);
            } else {
                throw new RuntimeException("Unable to cast Task class into any of its subclasses.");
            }
            String serializedTask = String.join(DELIMITER, taskFields);
            sb.append(serializedTask);
            sb.append("\n");
        }
        return sb.toString();
    }

    public static Task parseText(String text) {
        String[] fields = text.split(DELIMITER);
        String taskType = fields[0];
        Task t = null;
        
        boolean status = fields[1].equals("0") ? false : true;

        switch (taskType) {
            case "T":
                t = new Todo(fields[2]);
                t.setStatus(status);
                break;
            case "D":
                t = new Deadline(fields[2], fields[4]);
                t.setStatus(status);
                break;
            case "E":
                t = new Event(fields[2], fields[3], fields[4]);
                t.setStatus(status);
                break;
            default:
            break;
        }

        return t;
    }
}
