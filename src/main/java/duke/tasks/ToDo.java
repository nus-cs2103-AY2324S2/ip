package duke.tasks;

/**
 * A class that encapsulates the ToDo tasks, a type of Task.
 * 
 * @author: CHEN WENLONG
 * @version: CS2103T AY23/24 Semester 2
 */
public class ToDo extends Task {
    /** A String value that represent the type of Task, in this case T for ToDo */
    private static final String TYPE = "T";

    /**
     * Constructor for the ToDo.
     * 
     * @param name A String value that states the name of the Task.
     */
    public ToDo(String name) {
        super(name, TYPE, false);
    }

    /**
     * Constuctor used when we are loading from storage.
     * 
     * @param name      A String value that states the name of the Task.
     * @param completed Boolean of whether Task is completed.
     */
    public ToDo(String name, boolean completed) {
        super(name, TYPE, completed);
    }

    /**
     * Searches for a key in name.
     * 
     * @param key String to find in the name.
     * @return Boolean if key is found or not.
     */
    public boolean find(String key) {
        return super.find(key);
    }

    /**
     * Converts the data (ToDo) here into a format to be stored in the file.
     * 
     * @return String representation of the ToDo to be store in local disk.
     */
    @Override
    public String convertToStorageFormat() {
        return super.convertToStorageFormat();
    }

    /**
     * String representation of a ToDo.
     * 
     * @return Returns the String representation of a ToDo.
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
