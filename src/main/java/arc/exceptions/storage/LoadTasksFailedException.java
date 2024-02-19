package arc.exceptions.storage;

import arc.exceptions.ArcException;

/**
 * Represents an exception that is thrown when the application encounters an error
 * while attempting to load tasks from the tasks file. This could be due to issues
 * such as file corruption, formatting errors, or other problems that prevent the
 * successful parsing of the task data.
 * <p>
 * This class extends {@link ArcException} to provide a unified framework for handling
 * exceptions in the application, ensuring that all errors related to task loading
 * are reported in a consistent manner. It includes the original exception message
 * to provide detailed insight into the cause of the failure.
 */
public class LoadTasksFailedException extends ArcException {
    /**
     * Constructs a new LoadTasksFailedException with a detailed error message
     * that includes the message from the original exception. This constructor
     * is typically used when catching a lower-level exception (such as an
     * IOException or a parsing exception) and wrapping it into a
     * LoadTasksFailedException to be handled at a higher level.
     *
     * @param exception The exception that triggered this exception, containing
     *                  details about the specific error encountered during task loading.
     */
    public LoadTasksFailedException(Exception exception) {
        super(String.format("Failed to load tasks. %s", exception.getMessage()));
    }

    /**
     * Constructs a new LoadTasksFailedException with a detailed error message
     * that includes the message from the original exception. This constructor
     * is typically used when catching a lower-level exception (such as an
     * IOException or a parsing exception) and wrapping it into a
     * LoadTasksFailedException to be handled at a higher level.
     *
     * @param message The message containing details about the specific error encountered
     *                during task loading.
     */
    public LoadTasksFailedException(String message) {
        super(String.format("Failed to load tasks. %s", message));
    }
}
