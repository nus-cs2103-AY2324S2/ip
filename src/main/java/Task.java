public class Task {
    protected String description;
    protected int id;
    protected boolean isDone;
    protected String startDate;
    protected String endDate;
    protected char type;

    // To-do
    public Task(String description, int id) {
        this.description = description;
        this.isDone = false;
        this.id = id;
        this.type = 'T';
    }

    // Deadline
    public Task(String description, int id, String end) {
        this.description = description;
        this.isDone = false;
        this.id = id;
        this.endDate = end;
        this.type = 'D';
    }

    // Event
    public Task(String description, int id, String start, String end) {
        this.description = description;
        this.isDone = false;
        this.id = id;
        this.type = 'E';
        this.startDate = start;
        this.endDate = end;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getFullStatus() {
        return this.id + ". [" + this.getStatusIcon() + "] ["
                + this.type + "] "
                + this.description;
    }

    public void setDone(boolean doneness) {
        this.isDone = doneness;
    }

}

