package damon.exceptions;

/**
 * Represents Exception corresponding to that there is no existing storage file to load.
 */
public class StorageFileLoadingException extends DamonExceptions{

    /**
     * Constructs a new StorageFileLoadingException object.
     */
    public StorageFileLoadingException() {
        this.message = "Sorry, there is no existing storage file to load :(";
    }
}
