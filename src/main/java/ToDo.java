/**
 * ToDos: tasks without any date/time attached to it.
 *
 * @author Titus Chew
 */
public class ToDo extends Task {
    /**
     * Constructor for a to-do
     * @param name the name of the to-do
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
