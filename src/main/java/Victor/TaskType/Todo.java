package victor.tasktype;

/**
 * The Todo class is a child class of the Task class.
 * It inherits the variables from the Task Class, along with their
 * methods, and has overridden several methods in order to
 * add their own specific information to it.
 *
 * @author Dominic Fu Ming Jun
 */
public class Todo extends Task {

    /**
     * The Todo constructor takes in a String description and a boolean isDone
     * which is added to their respective variables of the same name.
     * This is done using the super keyword.
     *
     * @param description The description of the task.
     * @param isDone      States whether the task is done or not.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * The toString method is overridden from the Task toString method to include
     * the additional information of [T] to indicate that this Task type is a
     * Todo.
     *
     * @return A string that contains the extra information the Todo class,
     *         along with the normal information displayed from the Task toString method.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * The saveInput is overridden from the Task class. It is used to
     * write out all the data from the Todo class in the format
     * of the data file before being saved into the data file.
     *
     * @return A string that is in the format to be added to the data file.
     */
    @Override
    public String saveInput() {
        return "T | " + isDone + " | " + description;
    }
}
