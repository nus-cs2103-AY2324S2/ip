/**
 * Todo class
 * This class contains informations about the todo.
 */
public class Todo extends Task {

    /**
     * Todo Constructor
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * toString Method
     * @return a string that describe the todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
