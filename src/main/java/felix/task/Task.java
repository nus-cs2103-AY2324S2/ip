package felix.task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Task {
    protected static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    protected static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("d MMM yyyy HHmm");
    private final String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a String representing the task's status as done.
     */
    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkDone() {
        this.isDone = false;
    }

    protected String getDescription() {
        return this.description;
    }

    public abstract String toFileString();

    /**
     * Creates a task from a given line of a file within the hard disk.
     * If necessary, the task will be marked as done.
     *
     * @return task represented by file line.
     */
    public static Task createTaskFromFileLine(String line) {
        String[] words = line.split(" \\| ");
        Task task;
        if (words[0].equals("T")) {
            task = new ToDo(words[2]);
        } else if (words[0].equals("D")) {
            task = new Deadline(words[2], words[3]);
        } else {
            task = new Event(words[2], words[3], words[4]);
        }
        if (words[1].equals("X")) {
            task.markAsDone();
        }
        return task;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",this.getStatusIcon(),this.description);
    }
}
