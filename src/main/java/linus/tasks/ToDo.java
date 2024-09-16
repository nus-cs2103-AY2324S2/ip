package linus.tasks;

import java.time.LocalDateTime;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public String getTaskType() {
        return TASKTYPE;
    }

    /**
     * Returns the minimum date possible as ToDos have no dates
     */
    @Override
    public LocalDateTime getDate() {
        return LocalDateTime.MIN;
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