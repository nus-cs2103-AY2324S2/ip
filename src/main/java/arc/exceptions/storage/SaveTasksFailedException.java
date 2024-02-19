package arc.exceptions.storage;

import java.io.IOException;

import arc.exceptions.ArcException;

/**
 * Represents an exception that is thrown when the application fails to save
 * the tasks to the tasks file. This exception is typically thrown in response
 * to {@link IOException}s that occur during the write operation, such as when
 * the file is not accessible, there is insufficient disk space, or the file
 * system permissions prevent writing.
 * <p>
 * Extending {@link ArcException}, this class provides a consistent error handling
 * approach within the Arc application, ensuring that all file save-related errors
 * are encapsulated within a specific type of exception. It enriches the exception
 * with a detailed message from the underlying {@link IOException}, offering
 * insights into the cause of the failure.
 */
public class SaveTasksFailedException extends ArcException {
    /**
     * Constructs a new SaveTasksFailedException with an error message that
     * includes information about the cause of the failure to save tasks.
     * This constructor captures and relays the message from the underlying
     * {@link IOException}, providing context for the error and facilitating
     * troubleshooting.
     *
     * @param ioException The {@link IOException} that triggered this exception,
     *                    containing details about the specific I/O error encountered.
     */
    public SaveTasksFailedException(IOException ioException) {
        super(String.format("Failed to save tasks. %s", ioException.getMessage()));
    }
}
