package exception;

/**
 * EmptyInputException are Exceptions when user did not
 * input any description for the task.
 */
public class EmptyInputException extends DukeException {
    private String message;

    /**
     * The constructor of EmptyInputException.
     *
     * @param message Task name.
     */
    public EmptyInputException(String message) {
        this.message = message;
    }

    /**
     * Returns the exception message.
     *
     * @return The error message of the exception.
     */
    @Override
    public String getMessage() {
        String str;
        if (message.equals("find")) {
            str = "buzz buzz~~ No keyword was provided.\n";
        } else if (message.equals("update position")) {
            str = "buzz buzz~~ The task number was not provided.\n";
        } else if (message.equals("update description")) {
            str = "buzz buzz~~ No new description was provided.\n";
        } else if (message.equals("mark position")) {
            str = "buzz buzz~~ The task number was not provided.\n";
        } else {
            str = "buzz buzz~~ The description of a " + this.message + " cannot be empty.\n";
        }

        return str;
    }
}
