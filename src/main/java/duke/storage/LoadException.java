package duke.storage;

/**
 * Class to raise errors when loading from Storage.
 *
 * @author KohGuanZeh
 */
public class LoadException extends Exception {
    public LoadException(String errorMsg) {
        super(errorMsg);
    }
}
