public class ToDo extends Task {
    public ToDo (String description) {
        super(description);
    }

    public ToDo (String description, boolean done) {
        super(description);
        super.updateIsDone(done);
    }

    @Override
    public String getSaveTask() {
        return "T | " + super.getSaveTask();
    }

    @Override
    public String toString() {
        return "[T][" + this.getStatusIcon() + "] " + this.description;
    }
}
