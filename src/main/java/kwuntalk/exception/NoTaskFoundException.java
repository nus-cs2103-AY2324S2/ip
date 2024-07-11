package kwuntalk.exception;


/**
 * Represents an exception when the task ID used does not correspond to any tasks in the database.
 */
public class NoTaskFoundException extends KwunTalkException {
    private String taskId;


    /**
     * Constructor for NoTaskFoundException.
     *
     * @param taskId Task ID that can't be found.
     */
    public NoTaskFoundException(String taskId) {
        this.taskId = taskId;
    }


    /**
     * Return the string representation of the exception.
     *
     * @return String representation of the exception.
     */
    @Override
    public String toString() {
        return String.format("%s The number '%s' does not correspond to any task ;(\nPlease try another number.\n",
                super.toString(),
                taskId);
    }
}
