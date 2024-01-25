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
        super(name, TYPE);
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
