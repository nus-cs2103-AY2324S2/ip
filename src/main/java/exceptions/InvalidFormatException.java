package exceptions;

/**
 * The InvalidFormatException class represents an exception that is thrown when a data format is
 * incompatible with the expected format.
 */
public class InvalidFormatException extends InvalidInputException {
    public InvalidFormatException(String message) {
        super(message);
    }

    /**
     * Throws InvalidFormatException with different error messages based on the task type.
     *
     * @param task type of task with the wrong format.
     * @throws InvalidFormatException Thrown with different error messages depending on the task type.
     */
    public static void callInvalidFormatException(ErrorType task) throws InvalidFormatException {
        switch (task) {
        case TODO:
            throw new InvalidFormatException("   To record your task, enter:\n"
                    + "   todo <task>");
        case DEADLINE:
            throw new InvalidFormatException("   To set a deadline, enter:\n"
                    + "   deadline <task> /by <yyyy-MM-dd HH:mm>\n");
        case EVENT:
            throw new InvalidFormatException("   To record an event, enter:\n"
                    + "   event <event> /from <date and time> /to <date and time>\n");
        case FIND:
            throw new InvalidFormatException("   To search for your task, enter\n"
                    + "   find <keyword>\n");
        case DELETE:
            throw new InvalidFormatException("   To delete a task, enter\n"
                    + "   delete <task number in list>\n");
        case MARK:
            throw new InvalidFormatException("   To mark a task as done, enter\n"
                    + "   mark <task number in list>\n");
        case UNMARK:
            throw new InvalidFormatException("   To un-mark a task as done, enter\n"
                    + "   unmark <task number in list>\n");
        case UPDATE:
            throw new InvalidFormatException("   To edit a task, enter\n"
                    + "   update <task number in list> /[task] <task> [details]\n"
                    + "   e.g. - update 1 /deadline/ finish assignment /by/ 2024-02-03 23:00\n"
                    + "        - update 1 /todo/ finish assignment\n"
                    + "        - update 3 /event/ finish assignment /from/ 3pm /to/ 11pm\n");
        }
    }
}
