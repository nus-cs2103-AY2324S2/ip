package arc.exceptions.resource;

import java.io.IOException;

import arc.exceptions.ArcException;

/**
 * The LoadResourceFailedException class represents an exception thrown when a particular resource file fails to load.
 * This exception typically occurs when an I/O error occurs while attempting to load a resource file,
 * such as an FXML file for the Arc GUI application.
 */
public class LoadResourceFailedException extends ArcException {
    /**
     * Constructs a new LoadResourceFailedException with the specified I/O exception.
     *
     * @param ioException The IOException that caused the resource file loading to fail.
     */
    public LoadResourceFailedException(IOException ioException) {
        super(String.format("Failed to load resource file. %s", ioException.getMessage()));
    }
}
