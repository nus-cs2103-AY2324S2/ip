package dune.task;

import dune.DuneException;

/**
 * Represents a todo. A todo has a description and a boolean isDone status.
 */
public class ToDo extends Task {
    /**
     * Constructor for ToDo.
     *
     * @param description
     */
    public ToDo(String description) throws DuneException {
        super(description);
    }

    /**
     * Constructor for ToDo with boolean isDone.
     *
     * @param description
     * @param isDone
     */
    public ToDo(String description, boolean isDone) throws DuneException {
        super(description, isDone);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ToDo) {
            ToDo t = (ToDo) o;
            return t.getDescription().equals(this.getDescription());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
