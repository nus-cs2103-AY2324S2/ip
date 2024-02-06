package lindi.storage;

/**
 * Represents an Exception related to loading data from file into Storage
 */
public class StorageLoadException extends Exception {
    public StorageLoadException(String s) {
        super(s);
    }
}
