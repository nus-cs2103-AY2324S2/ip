package arc.exceptions.storage;

import java.io.IOException;

import arc.exceptions.ArcException;

/**
 * Represents an exception that is thrown when the application fails to create
 * the tasks file. This can occur due to various I/O related issues such as lack
 * of write permissions, disk space insufficiency, or other system errors that
 * prevent the file from being created.
 * <p>
 * This class extends {@link ArcException} and encapsulates an {@link IOException},
 * providing more detailed information about the failure cause to the user or the
 * system administrator.
 */
public class CreateTasksFileFailedException extends ArcException {
    /**
     * Constructs a new CreateTasksFileFailedException with a detailed error message
     * that includes the original {@link IOException} message.
     *
     * @param ioException The IOException that triggered this exception, containing
     *                    the details of the failure.
     */
    public CreateTasksFileFailedException(IOException ioException) {
        super(String.format("Failed to create tasks file. %s", ioException.getMessage()));
    }
}
