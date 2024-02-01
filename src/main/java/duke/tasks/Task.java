package duke.tasks;

public class Task {
    protected final String description;

    private boolean status;

    public Task(String description) {
        this.description = description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String toStorageString() {
        return "";
    }

    @Override
    public String toString() {
        String checkBox = "[ ]";

        if (status)
            checkBox = "[X]";

        return checkBox + " " + description;
    }
}
