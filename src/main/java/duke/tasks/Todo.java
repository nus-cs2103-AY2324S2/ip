package duke.tasks;

import duke.exceptions.TaskCreationException;

/**
 * This class inplements the Todo task type for the bot.
 * 
 * @author delishad21
 */
public class Todo extends Task {

    /**
     * Basic constructor for Todo.
     * 
     * @param isDone Marks if task is completed.
     * @param description Description of the task.
     */
    public Todo(boolean isDone, String description) {
        super(isDone, description);
    }

    /**
     * Factory method that takes in user input and parses it to return a Todo task.
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
     * Method for printing Todo as a viewable String.
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
