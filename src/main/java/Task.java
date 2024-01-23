public class Task {
    protected String description;
    protected boolean isDone;
    protected String startDate;
    protected String endDate;
    protected char taskType;

    // To-do
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = 'T';
    }

    // Deadline
    public Task(String description, String end) {
        this.description = description;
        this.isDone = false;
        this.endDate = end;
        this.taskType = 'D';
    }

    // Event
    public Task(String description, String start, String end) {
        this.description = description;
        this.isDone = false;
        this.taskType = 'E';
        this.startDate = start;
        this.endDate = end;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getFullStatus() {
        String status = ". [" + this.getStatusIcon() + "] ["
                + this.taskType + "] "
                + this.description;
        if (this.taskType == 'E') {
            status += "; from: " + this.startDate;
        }
        if (this.taskType == 'D' || this.taskType == 'E') {
            status += "; end: " + this.endDate;
        }
        return status;
    }

    public void setDone(boolean doneness) {
        this.isDone = doneness;
    }

}

