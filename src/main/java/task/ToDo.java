package task;

import util.CsvUtil;

/**
 * Represents a todo task.
 */
public class ToDo extends Task {

    /**
     * Constructs a new ToDo object with the given description.
     *
     * @param description the description of the ToDo
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a new ToDo object with the given marked status and description.
     *
     * @param isMarked    the marked status of the ToDo
     * @param description the description of the ToDo
     */
    public ToDo(boolean isMarked, String description) {
        super(isMarked, description, null);
    }

    /**
     * Formats the ToDo object into a CsvUtil object.
     *
     * @return the CsvUtil object representing the formatted ToDo
     */
    @Override
    public CsvUtil format() {
        return new CsvUtil("T", String.valueOf(super.isMarked), super.description);
    }

    /**
     * Returns a string representation of the ToDo object.
     *
     * @return a string representation of the ToDo object
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Checks if the ToDo object is equal to another object.
     *
     * @param o the object to compare with
     * @return true if the ToDo object is equal to the other object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ToDo toDo = (ToDo) o;
        return isMarked == toDo.isMarked && description.equals(toDo.description);
    }
}
