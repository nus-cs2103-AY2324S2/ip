package Tasks;

/**
 * Handles a task of type "Deadline".
 * Deadline tasks contains both the description and deadline of the task.
*/
public class Deadline extends Task {

    private String deadline;

    public Deadline(String description) {
        super("");
        this.formatInput(description);
    }

    public Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    // Get deadline.
    public String getDeadline() {
        return this.deadline;
    }

    @Override
    protected void formatInput(String description) {
        String errorMessage = 
            "Unable to identify the start and/or end date. Make sure to follow the format:\n"
            + "'event DESCRIPTION /from START /to END'";

        try {
            this.deadline = description.split("/by ")[1].trim();

        } catch (ArrayIndexOutOfBoundsException e){
            throw new ArrayIndexOutOfBoundsException(errorMessage);
        }

        if (this.deadline.equals("")) {
            throw new IllegalArgumentException(errorMessage);
        }

        this.name = description.split("/by")[0].trim();
    }

    @Override
    public String toCSV() {
        char status = this.isDone
                            ? '1'
                            : '0';

        return String.format("D,%c,%s,%s\n",
                    status,
                    this.name,
                    this.deadline
                    );
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }
}
