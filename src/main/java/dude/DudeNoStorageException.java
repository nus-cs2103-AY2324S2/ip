package dude;

/**
 * Dude storage exception, thrown when unable to create storage.
 */
public class DudeNoStorageException extends RuntimeException {
    /**
     * Class constructor.
     */
    public DudeNoStorageException() {
        super("dude.Storage could not be created or found.");
    }

}
