public class Deadline extends Task {
    private String deadline;

    public Deadline(String task, String taskType, String deadline) {
        super(task, taskType);
        this.deadline = deadline;
    }
    public String toString() {
        return this.getTaskType() + this.getStatus() + " " + this.getTask() + this.getPeriod();
    }

    public String getPeriod() {
        return "(by: " + this.deadline + ")";
    }

    public String announcement() {
        return "New Deadline created!";
    }

    public String saveString() {
        return this.getTaskTypeSingle() + "/" + this.getStatusBinary() + "/" + this.getTask() + "/" +
                this.deadline;
    }
}
