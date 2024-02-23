package kwuntalk.exception;


/**
 * Represents an exception when trying to access or write with the tasks file.
 */
public class TasksFileException extends KwunTalkException {


    /**
     * Return the string representation of the exception.
     *
     * @return String representation of the exception.
     */
    @Override
    public String toString() {
        return super.toString() + "An error occurred with the tasks file.\n";
    }
}
