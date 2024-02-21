package luke.exception;

/**
 * Thrown when the tasks in save file are in the incorrect format, due to possibly corruption or user editing.
 */
public class SaveFileCorruptedException extends Throwable {
    @Override
    public String toString() {
        return "Save file could not be read. If you have edited the save file, please undo the edits \n"
                + "so that it could be read. Otherwise, it could be due to corruption.";
    }
}
