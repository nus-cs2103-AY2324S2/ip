package dude.tasks;

import dude.exceptions.InvalidDescriptionException;
import dude.utils.Utils;

/**
 * The Todo class represents a task with a description.
 */
public class Todo extends Task {

    /**
     * Constructor for the Todo class.
     *
     * @param description The description of the todo.
     */
    public Todo(String description) {
        super(description);
        assert (description != null);
    }


    /**
     * Static method to create a Todo object from parsing a string.
     *
     * @param s The string to be parsed into a Todo object.
     * @return The Todo object created from the string.
     * @throws InvalidDescriptionException if the description of the todo is empty.
     */
    public static Todo from(String s) throws InvalidDescriptionException {

        //get rid of the command
        String description = Utils.discardFirstWord(s.trim()).trim();

        if (!description.isEmpty()) {
            return new Todo(description);
        } else {
            throw new InvalidDescriptionException("The description of a todo cannot be empty.");
        }
    }

    /**
     * Returns a string representation of the Todo object.
     *
     * @return A string representation of the Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns whether the object is equal to this object.
     *
     * @param object The object to be compared.
     * @return Whether the object is equal to this object.
     */
    @Override
    public boolean equals(Object object) {
        if (object instanceof Todo) {
            Todo t = (Todo) object;
            return t.getDescription().equals(this.getDescription());
        }
        return false;
    }
}
