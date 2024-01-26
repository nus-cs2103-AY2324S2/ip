public class InputException extends Exception {
    InputException(String message) {
        super(message);
    }

    InputException(String message, Throwable cause) {
        super(message, cause);
    }

    static InputException exceptionCommandProcessing(Command command, String input, Throwable cause) {
        String message = "Something went wrong when processing your " + command + "command: \n"
        + "Check your input again: " + input;
        return new InputException(message, cause);
    }
}