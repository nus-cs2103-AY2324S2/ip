package duke.tasks;

/**
 * Class for ToDo Task
 */
public class ToDo extends Task {
    private final String TASKTYPE = "ToDo";
    
    public ToDo(String description){
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String getTaskType() {
        return TASKTYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String saveFormat() {
        return String.format("%s;;%s",
                "T", super.saveFormat());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("%s%s", "[T]", super.toString());
    }

}