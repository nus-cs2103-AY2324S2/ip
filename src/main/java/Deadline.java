
//Deadline: tasks that need to be done before a specific date/time
//e.g., submit report /by 11/10/2019 5pm
public class Deadline extends Task{
    private String dueDate;

    public Deadline (String description, String dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    @Override
    public String listTaskString() {
        return "[D]" + super.listTaskString()
                + " (by: " + this.dueDate + ")";
    }

}
