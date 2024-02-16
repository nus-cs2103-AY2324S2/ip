package task;

/**
 * Represents a ToDo Task.
 *
 * This class is the representation of a Todo task.
 * It extends from its parent class the Task class.
 */
public class ToDo extends Task {
    public static final String TASK_TYPE = "todo";

    /**
     * Creates a ToDo object.
     * Will call the super constructor with the task name variable.
     *
     * @param name The name of the task.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Creates a ToDo object with a mark indicator to mark if it's completed.
     * Will call the super constructor with the task name variable.
     *
     * @param name The name of the task.
     * @param mark To indicate if this is marked or not.
     */
    public ToDo(String name, String mark) {
        super(name);
        if (mark.equals("1")) {
            super.mark();
        }
    }

    /**
     * Returns a string representation of this Todo object for storage in DataWriter.
     * This includes the formating required for the reader to split and read it.
     *
     * @return a formatted string representation of this object.
     */
    @Override
    public String formatDataLine() {
        return "ToDo|" + super.getCompleted() + "|" + super.getCommand();
    }

    /**
     * Returns a string representation of this ToDo.
     * This includes an indicator that this is a ToDo object.
     *
     * @return a string representation of this ToDo object.
     */
    @Override
    public String toString() {
        String totalString = "[T]" + super.toString();
        return totalString;
    }

    /**
     * Executes the necessary action created from the parsed results.
     * In this case, will add the ToDo object to the TaskStorage of the application.
     *
     * @param taskStorage The storage space where the action will take place.
     */
    @Override
    public String execute(TaskStorage taskStorage) {
        taskStorage.addTask(this);
        String printMessage = "Gotchu! I've added this task:";
        printMessage += "\n" + this + "\n";
        printMessage += "You now have " + taskStorage.size() + " tasks in the list.";
        return printMessage;
    }
}
