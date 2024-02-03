//// Solution below adapted by week2 iP Level-3 Partial solution
package Ping;

/**
 * This class is used to create a todo task
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
