package arc.exceptions.gui;

import arc.exceptions.ArcException;

/**
 * Represents an exception that is thrown when the application encounters an error
 * while attempting to load the javafx gui.
 * <p>
 * This class extends {@link ArcException} to provide a unified framework for handling
 * exceptions in the application, ensuring that all errors related to gui loading
 * are reported in a consistent manner. It includes the original exception message
 * to provide detailed insight into the cause of the failure.
 */
public class LoadGuiFailedException extends ArcException {
    /**
     * Constructs a new LoadGuiFailedException with a detailed error message
     * that includes the message from the original exception.
     *
     * @param exception The exception that triggered this exception, containing
     *                  details about the specific error encountered during gui loading.
     */
    public LoadGuiFailedException(Exception exception) {
        super(String.format("Failed to load gui. %s", exception.getMessage()));
    }
}
