package duke.tasks;

public class Task {
    protected String description;
    protected Boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String saveFormat() {
        String isDoneSave = (isDone ? "1" : "0");
        return String.format("%s;;%s",
                isDoneSave, description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s",
            this.getStatusIcon(),
            this.description);
    }

}
