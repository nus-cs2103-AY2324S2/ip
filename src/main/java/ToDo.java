public class ToDo extends Task {

    /**
     *
     * @param description: Description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        String taskType = "[T]";
        String toDoString = taskType + super.toString();
        return toDoString;
    }
}
