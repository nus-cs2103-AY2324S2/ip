package duke.tasks;

import duke.exceptions.TaskCreationException;

/**
 * This class inplements the Todo task type for the bot.
 * 
 * @author delishad21
 */
public class Todo extends Task {

    /**
     * Creates Todo object.
     * 
     * @param isDone Marks if task is completed.
     * @param description Description of the task.
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Returns a Todo object by taking in user input and parsing it.
     * 
     * @param isDone Marks if task is completed.
     * @param input User input to be parsed.
     * @return Todo object.
     * @throws TaskCreationException
     */
    public static Todo todoParse(boolean isDone, String input) throws TaskCreationException {
        String description = input.split(" ", 2)[1];
        if (description.equals("")) {
            throw new TaskCreationException("Missing Information: \"description\"" );
        }

        Todo t = new Todo(false, description);
        return t;
    }

    /** 
     * Returns Todo as a viewable String.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /** 
     * Method for converting Todo into a String for saving in save file.
     * 
     * @return String
     */
    @Override
    public String toSave() {
        return "[T]|" + super.toSave();
    }
}
