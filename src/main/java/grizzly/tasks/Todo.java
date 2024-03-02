package grizzly.tasks;

import java.util.Hashtable;
import java.util.stream.Stream;

import grizzly.exceptions.MissingInformationException;
import grizzly.exceptions.MissingParameterException;
import grizzly.utils.Parser;

/**
 * This class inplements the Todo task type for the bot.
 *
 * @author delishad21
 */
public class Todo extends Task {

    private static final String[] REQUIRED_PARAMS = {"description"};
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
     * Returns a todo object by taking in user input and parsing it.
     *
     * @param isDone Marks if task is completed.
     * @param params Hashtable of parameters for the generation of task.
     * @throws MissingInformationException Missing parameter information
     * @throws MissingParameterException Missing parameters
     */
    public static Todo todoParse(boolean isDone, Hashtable<String, String> params)
            throws MissingInformationException, MissingParameterException {

        Parser.checkParams(params, REQUIRED_PARAMS);

        String[] filteredParams = Stream.of(REQUIRED_PARAMS).map(x -> params.get(x)).toArray(String[]::new);

        return new Todo(false, filteredParams[0]);
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
