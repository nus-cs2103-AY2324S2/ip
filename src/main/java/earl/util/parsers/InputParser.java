package earl.util.parsers;

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
     */
    public static Handler parse(String input) {
        try {
            // all valid input is expected to be of the format
            // <command> [<arg1>, <arg2>, ...]
            String[] data = input.trim().split("\\s+", 2);
            assert data.length > 0;
            String command = data[0].toUpperCase();
            if (command.isEmpty()) {
                return HandlerType.HELP.createHandler(input);
            }
            String args = (data.length > 1) ? data[1] : "";
            HandlerType handlerType = HandlerType.valueOf(command);
            return handlerType.createHandler(args);
        } catch (Exception e) {
            return HandlerType.HELP.createHandler(input);
        }
    }
}
