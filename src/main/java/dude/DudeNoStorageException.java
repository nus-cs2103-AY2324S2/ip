package dude;

public class DudeNoStorageException extends RuntimeException {
    public DudeNoStorageException() {
        super("dude.Storage could not be created or found.");
    }

}
