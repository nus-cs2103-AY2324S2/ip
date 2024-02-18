package parser;

import exception.InvalidTaskFormatException;
import task.ToDo;

/**
 * Represents a parser that is used to parse todo tasks.
 * <p>
 * This class is used to parse todo tasks from the user input.
 * </p>
 */
public class ToDoParser {
    /**
     * Parses the user input and returns a todo task.
     * 
     * @param input The user input
     * @return The todo task
     * @throws InvalidTaskFormatException If the user input is in an invalid format
     */
    public static ToDo parseToDo(String input) throws InvalidTaskFormatException {
        String description = input.substring("todo ".length()).trim();
        if (description.isEmpty()) {
            throw new InvalidTaskFormatException("The description of a todo cannot be empty.");
        }
        return new ToDo(description);
    }
}
