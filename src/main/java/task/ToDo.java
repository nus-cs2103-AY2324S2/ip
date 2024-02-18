/**
 * This is a "ToDo" task.
 * It is the most basic version of a task, simply having a description.
 */

package task;

public class ToDo extends Task{
    public ToDo(String description) throws InvalidInputException {
        super(description);
        if (description.isEmpty()) {
            throw new InvalidInputException("OOPS!!! The description of a todo cannot be empty.");
        }
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
