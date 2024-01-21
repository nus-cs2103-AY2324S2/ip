/**
 * Class for task start with deadline
 */
public class Deadline extends Task{
    private String by;

    /**
     * constructor
     * @param descrip the dicription of the task.
     * @param by by field.
     */
    public Deadline(String descrip, String by) {
        super(descrip);
        this.by = by;
    }

    /**
     * Override the abstract class
     * @return T
     */
    @Override
    public String getTaskTypeIcon() {
        return "D";
    }

    /**
     * Override the abstract class
     * @return deadline
     */
    @Override
    public String getTaskType() {
        return "deadline";
    }

    /**
     * Override the toString method
     * @return String representation of the task
     */
    @Override
    public String toString() {
        return String.format("%s(by: %s)",super.toString(), this.by);
    }
}
