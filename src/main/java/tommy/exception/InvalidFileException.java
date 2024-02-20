package tommy.exception;

/**
 * Represents the exceptions thrown in the program when file is corrupted, missing or invalid.
 */
public class InvalidFileException extends TommyException {

    /**
     * Constructor for an InvalidFileException,
     * which initialises it with its error message.
     *
     * @param message Description of the file error.
     */
    public InvalidFileException(String message) {
        super(message);
    }

    @Override
    public String getErrorMessage() {
        String invalidFilePrefix = "File error: ";

        return  invalidFilePrefix+ getMessage();
    }
}
