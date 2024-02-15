package bond.main;

/**
 * The BondException class is used to handle exceptions in the Bond task
 * management program.
 *
 * @author Benny Loh
 * @version 0.1
 */
public class BondException extends Exception {

    private enum ExceptionType {
        EMPTY_DESCRIPTION, INVALID_COMMAND_TYPE, MISSING_INDEX, EXTRA_DETAILS,
                INVALID_INDEX, EMPTY_LIST, STORE_FAILURE, LOAD_FAILURE, INVALID_DATE_FORMAT,
                        INVALID_KEYWORD
    }

    /**
     * Constructor for the BondException class.
     *
     * @param message The message to be displayed when the exception is raised.
     */
    private BondException(String message) {
        super(message);
    }

    /**
     * Raises an exception based on the task name and the exception type.
     *
     * @param taskName      The name of the task that the exception is raised for.
     * @param exceptionType The reason why the exception that is raised.
     * @throws BondException The exception that is raised.
     */
    public static void raiseException(String taskName, String exceptionType) throws BondException {

        String message = "";

        if (exceptionType.equals(ExceptionType.INVALID_COMMAND_TYPE.toString())) {
            message = "WHAT do you MEAN???????????";
        } else if (exceptionType.equals(ExceptionType.EMPTY_DESCRIPTION.toString())) {
            message = "Are you for REAL??? No info for a " + taskName;
        } else if (exceptionType.equals(ExceptionType.MISSING_INDEX.toString())) {
            message = "WHY did you not give me an INDEX to " + taskName + " a task!!!";
        } else if (exceptionType.equals(ExceptionType.EXTRA_DETAILS.toString())) {
            message = "I see, you are SO EXTRA and saying " + taskName + " needs MORE!!!";
        } else if (exceptionType.equals(ExceptionType.INVALID_INDEX.toString())) {
            message = "WHY did you not give me a PROPER INDEX to " + taskName + " a task!!!";
        } else if (exceptionType.equals(ExceptionType.EMPTY_LIST.toString())) {
            message = "ADD something to the list first, BEFORE you " + taskName + " something!!!";
        } else if (exceptionType.equals(ExceptionType.STORE_FAILURE.toString())) {
            message = String.format("I COULD NOT %s your tasks!!!", taskName.toUpperCase());
        } else if (exceptionType.equals(ExceptionType.LOAD_FAILURE.toString())) {
            message = String.format("I COULD NOT %s your tasks!!!", taskName.toUpperCase());
        } else if (exceptionType.equals(ExceptionType.INVALID_DATE_FORMAT.toString())) {
            message = "Give the DATE(s) in the CORRECT FORMAT!!!";
        } else if (exceptionType.equals(ExceptionType.INVALID_KEYWORD.toString())) {
            message = "Give me a PROPER KEYWORD to search for!!!";
        }

        throw new BondException(message);
    }

}
