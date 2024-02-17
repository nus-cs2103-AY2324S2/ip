package shuheng.tasks;

/**
 * This class represents a task without a deadline.
 */
public class ToDo extends Task {
    public ToDo(String description, PriorityLevel priority) {
        super(description, "[T]", priority);
    }

    @Override
    public String getTimeData() {
        return "";
    }

    @Override
    public String getLogRepresentation() {
        String completeStatus = "F";
        if (this.isDone) {
            completeStatus = "T";
        }
        return "T" + "," + completeStatus + "," + this.description + "," + this.getPriority();
    }

    @Override
    public String getFullStatus() {
        return this.getStatusIcon() + " " + this.getDescription() + " [" + this.getPriority() + "]";
    }
}
