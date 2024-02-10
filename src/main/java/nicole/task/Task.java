package nicole.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import nicole.nicoleexceptions.NicoleException;

public class Task {
    private boolean isComplete;
    private String name;

    /**
     * Initialises a Task. The default state of a Task is incomplete.
     *
     */
    public Task() {
        this.isComplete = false;
    }

    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Factory method for Tasks.
     *
     * @param name the user request.
     * @param taskType 'T' for Todo, 'D' for Deadline, 'E' for Event.
     * @throws NicoleException if any of Task, Deadline, or Event throws it due to
     *                         invalid request.
     */
    public static Task taskFactory(String name, char taskType) throws NicoleException {
        if (taskType == 'T') {
            return new Todo(name);
        } else if (taskType == 'D') {
            return new Deadline(name);
        } else {
            return new Event(name);
        }
    }

    /**
     * Marks a task object as completed.
     *
     * @throws NicoleException if the task is already marked completed.
     */
    public String markDone() throws NicoleException {
        if (isComplete) {
            throw new NicoleException("That is already marked complete -_-");
        } else {
            isComplete = true;
            return "Marked as completed! Good job :3";
        }
    }

    /**
     * Marks a task object as incomplete.
     *
     * @throws NicoleException if the task is already marked incomplete.
     */
    public String markUndone() throws NicoleException {
        if (!isComplete) {
            throw new NicoleException("That is already marked incomplete -_-");
        } else {
            isComplete = false;
            return "Marked as incomplete. We'll get em next time";
        }
    }

    /**
     * Retrieves the date associated with the Task object.
     *
     * @return the max LocalDate by default.
     */
    public LocalDate getDate() {
        return LocalDate.parse("+999999999-12-31");
    }

    /**
     * Retrieves the "from" datetime associated with the Task object. This method is not not implemented by the
     * Todo subtype.
     *
     * @return the maximum LocalDateTime by default.
     */
    public LocalDateTime getFromDateTime() {
        return LocalDateTime.parse("+999999999-12-31T23:59:59.999999999");
    }

    /**
     * Retrieves the "to" datetime associated with the Task object. This method is only implemented for the Event
     * subtype.
     *
     * @return the minimum LocalDateTime by default.
     */
    public LocalDateTime getToDateTime() {
        return LocalDateTime.parse("-999999999-01-01T00:00:00");
    }

    /**
     * Overwrites the name of this task.
     *
     */
    public void updateName(String newTaskName) {
        name = newTaskName;
    }

    /**
     * Verifies if there is a keyword match with this task.
     *
     * @param keyword the partial or full keyword.
     */
    public boolean contains(String keyword) {
        return this.name.contains(keyword);
    }

    @Override
    public String toString() {
        return isComplete ? "[C] " + name : "[I] " + name;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Task)) {
            return false;
        }
        Task task = (Task)object;
        return this.name.equals(task.name);
    }
}
