package johnny.tasks;

/**
 * Represents a todo.
 */
public class ToDo extends Task {

    /**
     * Constructor for Todo.
     * Calls super class constructor to store name of task.
     *
     * @param name Name of the Todo.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Formats Todo for Ui to print.
     *
     * @return String representation of Todo for printing.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Formats Todo for storing in Storage.
     *
     * @return String representation of Todo to store.
     */
    @Override
    public String addToFile() {
        return "T | " + super.addToFile() + "\n";
    }

}
