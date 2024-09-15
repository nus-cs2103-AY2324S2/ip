package raphael.format;

/**
 * Interface for object that is convertable into task file format.
 */
public interface FileFormattable {
    /**
     * Returns the string representation of the object that respect the task file format
     *
     * @return the string that match the format of the task file
     */
    String toFileFormat();
}
