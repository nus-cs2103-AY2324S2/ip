package chatbot;

import java.io.Serializable;

import chatbot.exceptions.AlreadyMarkedException;
import chatbot.exceptions.AlreadyUnmarkedException;

/**
 * Represents a Task created by the user, as part of a list of tasks.
 */
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String description;
    private boolean done;

    /**
     * Constructor for the task.
     *
     * @param desc Description for the task.
     */
    public Task(String desc) {
        this.description = desc;
        this.done = false;
    }

    @Override
    public String toString() {
        return (done ? "[X] " : "[ ] ") + description;
    }

    public boolean contains(String s) {
        return this.description.contains(s);
    }

    /**
     * Marks the Task as done.
     *
     * @throws AlreadyMarkedException If the task is already marked as done.
     */
    public void mark() throws AlreadyMarkedException {
        if (this.done) {
            throw new AlreadyMarkedException();
        }
        this.done = true;
    }

    /**
     * Unmarks the Task.
     *
     * @throws AlreadyUnmarkedException If the task is not marked as done.
     */
    public void unmark() throws AlreadyUnmarkedException {
        if (!this.done) {
            throw new AlreadyUnmarkedException();
        }
        this.done = false;
    }
}
