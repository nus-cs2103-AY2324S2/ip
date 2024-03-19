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

    Todo(String task, boolean isDone) {
        super(task, isDone, TaskID.TODO_ID);
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

    Deadline(String task, String deadline, boolean isDone) {
        super(task, isDone, TaskID.DEADLINE_ID);
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

    Event(String task, String from, String to, boolean isDone) {
        super(task, isDone, TaskID.EVENT_ID);
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

    FixedDuration(String task, String timeNeeded, boolean isDone) {
        super(task, isDone, TaskID.FIXEDDURATION_ID);
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
 * The TaskFactory class provides static methods to create various task objects.
 */
public class TaskFactory {

    /**
     * Creates an Event task.
     *
     * @param task the description of the task
     * @param from the start time of the event
     * @param to the end time of the event
     * @param isDone the status indicating whether the task is done or not
     * @return an Event task
     */
    public static Task createEvent(
        String task,
        String from,
        String to,
        boolean isDone
    ) {
        return new Event(task, from, to, isDone);
    }

    /**
     * Creates a Todo task.
     *
     * @param task the description of the task
     * @param isDone the status indicating whether the task is done or not
     * @return a Todo task
     */
    public static Task createTodo(String task, boolean isDone) {
        return new Todo(task, isDone);
    }

    /**
     * Creates a Deadline task.
     *
     * @param task the description of the task
     * @param deadline the deadline of the task
     * @param isDone the status indicating whether the task is done or not
     * @return a Deadline task
     */
    public static Task createDeadline(
        String task,
        String deadline,
        boolean isDone
    ) {
        return new Deadline(task, deadline, isDone);
    }

    /**
     * Creates a FixedDuration task.
     *
     * @param task the description of the task
     * @param timeNeeded the time needed for the task
     * @param isDone the status indicating whether the task is done or not
     * @return a FixedDuration task
     */
    public static Task createFixedDuration(
        String task,
        String timeNeeded,
        boolean isDone
    ) {
        return new FixedDuration(task, timeNeeded, isDone);
    }

    /**
     * Deserializes a string into a Task object.
     *
     * @param taskStr the serialized string representation of the task
     * @return a Task object deserialized from the given string
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
