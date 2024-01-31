//public abstract class Task {
//    boolean done;
//    String taskName;
//
//    public Task(String taskName) {
//        this.taskName = taskName;
//        this.done = false;
//    }
//
//    public void mark() {
//        this.done = true;
//    }
//
//    public void unmark() {
//        this.done = false;
//    }
//
//    public abstract String getType();
//
//    @Override
//    public String toString() {
//        return String.format("[%s][%s] %s", getType(), done ? "X" : " ", taskName);
//    }
//}
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private boolean done;
    private String taskName;
    private TaskType taskType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    private static final DateTimeFormatter DATE_FORMAT_OUTPUT = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");

    public Task(String taskName, TaskType taskType, Boolean done ) {
        this.taskName = taskName;
        this.done = done;
        this.taskType = taskType;
    }
    public Task(String taskName, TaskType taskType, Boolean done, String startDate) {
        this.taskName = taskName;
        this.done = done;
        this.taskType = taskType;
        this.startDate= parseDateTime(startDate);
    }
    public Task(String taskName, TaskType taskType, Boolean done, String startDate, String endDate) {
        this.taskName = taskName;
        this.done = done;
        this.taskType = taskType;
        this.startDate = parseDateTime(startDate);
        this.endDate = parseDateTime(endDate);
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public boolean isDone() {
        return this.done;
    }

    public String getName() {
        return this.taskName;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public String getStartDateString() {
        if (startDate != null) {
            return startDate.format(DATE_TIME_FORMAT);
        } else  {
            return null;
        }

    }

    public String getEndDateString() {
        if (endDate != null) {
            return endDate.format(DATE_TIME_FORMAT);
        } else  {
            return null;
        }
    }

    @Override
    public String toString() {
        String status = done ? "[X]" : "[ ]";
        switch (taskType) {
            case T:
                return String.format("[%s]%s %s", taskType, status, taskName);
            case D:
                return String.format("[%s]%s %s (by: %s)", taskType, status, taskName,
                        startDate.format(DATE_FORMAT_OUTPUT));
            case E:
                return String.format("[%s]%s %s (from: %s to: %s)", taskType, status, taskName,
                        startDate.format(DATE_FORMAT_OUTPUT), endDate.format(DATE_FORMAT_OUTPUT));
            default:
                return "";
        }
    }

    private LocalDateTime parseDateTime(String dateTimeString) {
        try {
            return LocalDateTime.parse(dateTimeString, DATE_TIME_FORMAT);
        } catch (Exception e) {
            // Handle parsing errors, e.g., invalid date format
            System.out.println("Error parsing date: " + e.getMessage());
            return null; // Or throw an exception if appropriate
        }
    }
}