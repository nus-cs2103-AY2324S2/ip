package duke.tasks;

import java.time.LocalDate;

/**
 * Super class of the tasks. Has mainly a task name, a boolean value indicating if the task is marked as completed,
 * and a task type.
 */
public class Task {
    String taskName;
    Boolean isDone;
    String taskType;

    public Task(String taskName, Boolean isDone, String taskType) {
        this.taskName = taskName;
        this.isDone = isDone;
        this.taskType = taskType;
    }
    /**
     * Marks the task as done, changing the isDone boolean value.
     */
    public void mark() {
        this.isDone = true;
    }
    /**
     * Marks the task as undone, changing the isDone boolean value.
     */
    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String done = " ";
        if (this.isDone) {
            done = "X";
        }
        return "[" + done + "] " + this.taskName;
    }

    /**
     *
     * @return Getter returning task attribute.
     */
    public String getTaskType() {
        return this.taskType;
    }
    /**
     *
     * @return Getter returning task attribute.
     */
    public String getTaskName() {
        return this.taskName;
    }
    /**
     *
     * @return Getter returning task attribute.
     */
    public Boolean getIsDone() {
        return this.isDone;
    }
    /**
     *
     * @return Getter returning task attribute.
     */
    public LocalDate getDeadline() {
        return LocalDate.parse("12-12-2019");
    }
    /**
     *
     * @return Getter returning task attribute.
     */
    public LocalDate getStart() {
        return LocalDate.parse("12-12-2019");
    }
    /**
     *
     * @return Getter returning task attribute.
     */
    public LocalDate getEnd() {
        return LocalDate.parse("12-12-2019");
    }

}
