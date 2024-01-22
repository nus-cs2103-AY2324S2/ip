public class Todo extends Action{
    /**
     * Constructor
     * @param description action to be made
     */
    public Todo(String description) {
        super(description);
    }

    /**
     *
     * @return task output
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
