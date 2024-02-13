package duke;

import java.time.LocalDateTime;
import java.util.ArrayList;

enum TaskID {
    TODO_ID("T"),
    EVENT_ID("E"),
    DEADLINE_ID("D");

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

/**
 * Represents a task list
 */
public class TaskList {

    private ArrayList<Task> storedTasks;

    TaskList(ArrayList<Task> storedTasks) throws DukeException {
        this.storedTasks = storedTasks;
    }

    TaskList() {
        this.storedTasks = new ArrayList<>();
    }

    public ArrayList<Task> getStoredTasks() {
        return this.storedTasks;
    }

    public boolean checkTaskIdx(int idx) {
        return 0 <= idx && idx < storedTasks.size();
    }

    public int numberOfTask() {
        return storedTasks.size();
    }

    public void addTask(Task t) throws DukeException {
        storedTasks.add(t);
    }

    public void setDone(int idx, boolean done) throws DukeException {
        storedTasks.get(idx).setDone(done);
    }

    /**
     * Pops task at idx
     * @param idx
     * @return
     */
    public Task popTask(int idx) throws DukeException {
        Task t = storedTasks.get(idx);
        storedTasks.remove(idx);
        return t;
    }

    public Task peekTask(int idx) {
        return storedTasks.get(idx);
    }

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
}
