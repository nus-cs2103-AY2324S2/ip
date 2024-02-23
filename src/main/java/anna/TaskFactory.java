package anna;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

enum TaskID {
    TODO_ID("T"),
    EVENT_ID("E"),
    DEADLINE_ID("D"),
    FIXEDDURATION_ID("F");

    private String id;

    TaskID(String id) {
        this.id = id;
    }

    public String toString() {
        return id;
    }
}

class Todo extends Task {

    Todo(String task, boolean done) {
        super(task, done, TaskID.TODO_ID);
    }

    public String serialise() {
        return String.format("%s", super.serialise());
    }

    public String taskStr() {
        return task;
    }
}

class Deadline extends Task {

    private LocalDateTime deadline;

    Deadline(String task, String deadline, boolean done) {
        super(task, done, TaskID.DEADLINE_ID);
        this.deadline = LocalDateTime.parse(deadline, DATETIME_FORMAT);
    }

    public String serialise() {
        return String.format(
        "%s%s",
        super.serialise(),
        deadline.format(DATETIME_FORMAT)
        );
    }

    public String taskStr() {
        return String.format(
        "%s (by: %s)",
        task,
        deadline.format(DATETIME_PRINT_FORMAT)
        );
    }
}

class Event extends Task {

    private LocalDateTime to;
    private LocalDateTime from;

    Event(String task, String from, String to, boolean done) {
        super(task, done, TaskID.EVENT_ID);
        this.from = LocalDateTime.parse(from, DATETIME_FORMAT);
        this.to = LocalDateTime.parse(to, DATETIME_FORMAT);
    }

    public String serialise() {
        return String.format(
        "%s%s<0>%s",
        super.serialise(),
        to.format(DATETIME_FORMAT),
        from.format(DATETIME_FORMAT)
        );
    }

    public String taskStr() {
        return String.format(
        "%s (from: %s to: %s)",
        task,
        from.format(DATETIME_PRINT_FORMAT),
        to.format(DATETIME_PRINT_FORMAT)
        );
    }
}

class FixedDuration extends Task {
    private Duration timeNeeded;

    FixedDuration(String task, String timeNeeded, boolean done) {
        super(task, done, TaskID.FIXEDDURATION_ID);
        this.timeNeeded = Duration.between(LocalTime.MIN, LocalTime.parse(timeNeeded));
    }

    public String serialise() {
        return String.format(
        "%s%s",
        super.serialise(),
        LocalTime.MIN.plus(timeNeeded).format(LOCALTIME_FORMAT)
        );
    }

    public String taskStr() {
        return String.format(
            "%s (duration: %s)",
            task,
            LocalTime.MIN.plus(timeNeeded).format(LOCALTIME_FORMAT)
        );
    }
}

/**
 * Represents a task list
 */
public class TaskFactory {

    public static Task createEvent(
        String task,
        String from,
        String to,
        boolean done
    ) {
        return new Event(task, from, to, done);
    }

    public static Task createTodo(String task, boolean done) {
        return new Todo(task, done);
    }

    public static Task createDeadline(
        String task,
        String deadline,
        boolean done
    ) {
        return new Deadline(task, deadline, done);
    }

    public static Task createFixedDuration(
        String task,
        String timeNeeded,
        boolean done
    ) {
        return new FixedDuration(task, timeNeeded, done);
    }

    /**
     * Deserealise string into Task
     */
    public static Task deserealiseTask(String taskStr) {
        String[] xs = taskStr.split("<1>");
        String[] taskData = xs[0].split("<0>");
        String[] auxData = xs.length == 1
            ? new String[0]
            : xs[1].split("<0>");
        String taskDesc = taskData[1];
        boolean isDone = taskData[2].equals("X");
        switch (taskData[0]) {
        case "T":
            return TaskFactory.createTodo(taskDesc, isDone);
        case "E":
            return TaskFactory.createEvent(
                taskDesc,
                auxData[0],
                auxData[1],
                isDone
            );
        case "F":
            return TaskFactory.createFixedDuration(taskDesc, auxData[0], isDone);
        default:
            return TaskFactory.createDeadline(taskDesc, auxData[0], isDone);
        }
    }
}
