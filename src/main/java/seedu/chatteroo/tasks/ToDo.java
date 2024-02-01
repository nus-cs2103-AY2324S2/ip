package seedu.chatteroo.tasks;

public class ToDo extends Task {
    protected String taskType = "T";
    public ToDo (String description) {
        super(description);
    }
    //Overridden toString method to print type of task and description
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
