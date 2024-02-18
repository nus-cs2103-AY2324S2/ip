package dave.tasks;

public class Todo extends Task {

    /**
     * Creates new Todo object.
     * 
     * @param desc Name or description of Todo object.
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * @{inheritDoc}
     */
    @Override
    public boolean isTaskWithDueDate() {
        return false;
    }

    /**
     * Formats the printing of the Todo object when shown to user.
     * 
     * @return Printing of the Todo object.
     */
    @Override
    public String toString() {
        return String.format("[To-do]%s", super.toString());
    }

    /**
     * Formats the output of the Todo object in output file.
     * 
     * @return The output to be written in the output file.
     */
    @Override
    public String fileString() {
        return String.format("TODO | %s", super.fileString());
    }

}
