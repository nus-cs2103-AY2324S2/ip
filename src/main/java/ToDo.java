public class ToDo extends Task {
    public ToDo (String description) {
        super(description);
    }
    //Overridden toString method to print type of task and description
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
