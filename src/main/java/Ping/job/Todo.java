//// Solution below adapted by week2 iP Level-3 Partial solution
package ping.job;

/**
 * This class is used to create a todo task
 */
public class Todo extends Task {
    protected String description;

    public Todo(String description) {
        super(description);
    }



    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
