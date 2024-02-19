package duke.tasks;

import java.util.ArrayList;

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
     * @param tags A list of tags to identify the task.
     */
    public ToDo(String name, ArrayList<String> tags) {
        super(name, TYPE, false, tags);
    }

    /**
     * Constuctor used when we are loading from storage.
     * 
     * @param name      A String value that states the name of the Task.
     * @param isCompleted Boolean of whether Task is completed.
     * @param tags      A list of tags to identify the task.
     */
    public ToDo(String name, boolean isCompleted, ArrayList<String> tags) {
        super(name, TYPE, isCompleted, tags);
    }

    /**
     * Searches for a key in name.
     * 
     * @param key String to find in the name.
     * @return Boolean if key is found or not.
     */
    @Override
    public boolean containsWord(String key) {
        return super.containsWord(key);
    }

    /**
     * Converts the data (ToDo) here into a format to be stored in the file.
     * 
     * @return String representation of the ToDo to be store in local disk.
     */
    @Override
    public String convertToStorageFormat() {
        return super.convertToStorageFormat() + " | " + super.tagsToStorageFormat();
    }

    /**
     * String representation of a ToDo.
     * 
     * @return Returns the String representation of a ToDo.
     */
    @Override
    public String toString() {
        return super.toString() + " Tags:" + super.getTags();
    }
}
