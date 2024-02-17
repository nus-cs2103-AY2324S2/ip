package earl.util.parsers;

import earl.exceptions.ParserException;
import earl.logic.Handler;
import earl.logic.HandlerType;

/**
 * Class responsible for interpreting the user input.
 */
public class InputParser implements Parser<Handler> {

    /**
     * Returns a new {@code Handler} object based on user input.
     *
     * @param input             text input by the user
     * @return                  a {@code Handler} object of the relevant type
     * @throws ParserException  if user input is of unexpected format
     */
    public static Handler parse(String input) throws ParserException {
        try {
            // all valid input is expected to be of the format
            // <command> [<arg1>, <arg2>, ...]
            String[] data = input.split("\\s+", 2);
            String command = data[0].toUpperCase();
            String args = (data.length > 1) ? data[1] : "";
            HandlerType handlerType = HandlerType.valueOf(command);
            return handlerType.createHandler(args);
        } catch (Exception e) {
            throw new ParserException(input);
        }
    }
}
