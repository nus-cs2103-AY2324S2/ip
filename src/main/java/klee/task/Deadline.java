package klee.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has to be completed with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime deadline;

    /**
     * Constructor for Deadline class.
     *
     * @param description
     * @param deadline
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
        this.type = "D";
    }

    /**
     * Returns the type and current completion status of task with its deadline.
     *
     * @return String to represent current state of class.
     */
    public String getStatus() {
        String statusIcon = (isDone ? "X" : " ");
        return "[" + type + "][" + statusIcon + "] " + description + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:ma")) + ")";
    }

    /**
     * Creates a String to represent this task in the txt file.
     *
     * @return String to be stored in txt file.
     */
    public String toText() {
        return super.toText() + " / " + deadline.format(DateTimeFormatter.ofPattern("yyyy MM dd H m"));
    }

    /**
     * Given input from txt file create new instance of Deadline.
     *
     * @param description
     * @param done
     * @param by
     * @return Instance of Task.
     */
    public static Task fromText(String description, String done, LocalDateTime by) {
        Task task = new Deadline(description, by);
        task.isDone = done.equals("1");
        return task;
    }

    /**
     * Checks if obj is the same as the current instance.
     *
     * @param obj
     * @return If obj has the same fields as this.
     */
    @Override
    public boolean equals(Object obj) {
        if (Task.class.isAssignableFrom(obj.getClass())) {
            boolean hasSameFields = this.deadline == ((Deadline) obj).deadline;
            return super.equals(obj) && hasSameFields;
        } else {
            return false;
        }
    }
}
