package TaskFlow.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import TaskFlow.exception.TaskFlowException;

/**
 * Represents a task in the Duke chatbot application.
 * It can be a ToDo, Deadline, or Event task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        this.isDone = false;
    }

    /**
     * Constructs a Task object with the specified description.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return A string representing the status icon ("X" if done, " " if not done).
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Converts a string representation of a task into a Task object.
     *
     * @param task A string representation of the task.
     * @return A Task object parsed from the input string.
     */
    public static Task fromString(String task) throws TaskFlowException {
        String[] inputs = task.split(" \\| ", 4);
        String type = inputs[0];
        String status = inputs[1];
        String description = inputs[2];
        String date = "";

        if (inputs.length > 3) {
            date = inputs[3];
        }

        switch (type) {
        case "T":
            ToDo todo = new ToDo(description);
            if (status.equals("X")) {
                todo.markAsDone();
            }
            return todo;
        case "D":
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy 'at' hh:mma");
            LocalDateTime d = LocalDateTime.parse(date, formatter);
            DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma");
            String formatted = customFormat.format(d);
            Deadline dl = new Deadline(description, formatted);
            if (status.equals("X")) {
                dl.markAsDone();
            }
            return dl;
        case "E":
            String[] timeDate = date.split("-");
            Event e = new Event(description, timeDate[0], timeDate[1]);
            if (status.equals("X")) {
                e.markAsDone();
            }
            return e;
        default:
            return null;
        }
    }

    /**
     * Checks if the task's description contains the specified keyword.
     *
     * @param keyword The keyword to search for in the task's description.
     * @return true if the description contains the keyword, false otherwise.
     */
    public boolean containsKeyword(String keyword) {
        return description.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task.
     */
    @Override
    public String toString() {
        return getStatusIcon() + " | " + description;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Task t = (Task) obj;
        return Objects.equals(description, t.description);
    }
}
