public class DudeNoStorageException extends RuntimeException {
    public DudeNoStorageException() {
        super("Storage could not be created or found.");
    }

}
