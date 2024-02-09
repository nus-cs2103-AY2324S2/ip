package headcube;
/**
 * Represents exceptions specific to the HeadCube application.
 * This custom exception class is used for handling application-specific error scenarios.
 */
public class HeadCubeException extends Exception {
    /**
     * Constructs a new HeadCubeException with the specified detail message.
     *
     * @param message The detail message.
     */
    public HeadCubeException(String message) {
        super(message);
    }
}


