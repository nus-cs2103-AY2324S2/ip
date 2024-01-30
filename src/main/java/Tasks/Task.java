package Tasks;

import Exceptions.DukeException;

public class Task {
    protected String description;
    protected boolean isDone;

    public enum TaskType {
        T,
        D,
        E;

        public static TaskType getType(String s) {
            switch (s) {
                case "T":
                    return T;
                case "D":
                    return D;
                case "E":
                    return E;
                default:
                    return null;
            }
        }
    }

    public Task(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("Description cannot be empty!");
        };

        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}