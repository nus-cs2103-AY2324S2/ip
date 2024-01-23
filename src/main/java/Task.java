public class Task {
    protected String description;
    protected int id;
    protected boolean isDone;
    protected String startDate;
    protected String endDate;
    protected char taskType;

    // To-do
    public Task(String description, int id) {
        this.description = description;
        this.isDone = false;
        this.id = id;
        this.taskType = 'T';
    }

    // Deadline
    public Task(String description, int id, String end) {
        this.description = description;
        this.isDone = false;
        this.id = id;
        this.endDate = end;
        this.taskType = 'D';
    }

    // Event
    public Task(String description, int id, String start, String end) {
        this.description = description;
        this.isDone = false;
        this.id = id;
        this.taskType = 'E';
        this.startDate = start;
        this.endDate = end;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getFullStatus() {
        String status = this.id + ". [" + this.getStatusIcon() + "] ["
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

