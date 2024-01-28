package task;

import nicoleexceptions.NicoleException;

import java.time.LocalDate;

public class Task {
    private boolean taskCompleted;
    private String name;

    /**
     * Initialises a Task. The default state of a Task is incomplete.
     *
     */
    public Task() {
        this.taskCompleted = false;
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
        if (this.taskCompleted) {
            throw new NicoleException("That is already marked complete -_-");
        } else {
            this.taskCompleted = true;
            return "Nicole: Marked as completed! Good job :3";
        }
    }

    /**
     * Marks a task object as incomplete.
     *
     * @throws NicoleException if the task is already marked incomplete.
     */
    public String markUndone() throws NicoleException {
        if (!this.taskCompleted) {
            throw new NicoleException("That is already marked incomplete -_-");
        } else {
            this.taskCompleted = false;
            return "Nicole: Marked as incomplete. We'll get em next time";
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

    @Override
    public String toString() {
        return this.taskCompleted ? "[C] " + this.name : "[I] " + this.name;
    }
}
