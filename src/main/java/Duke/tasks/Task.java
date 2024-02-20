package duke.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Task {
    private static final String hRULER = "____________________________________________________________\n";
    private final String description;
    private String status = "[ ]";
    public abstract boolean hasDate(LocalDateTime toFind);
    public String writeObject() {
        return String.format("| %s | %s", this.status, this.description);
    }
    public void setStatus(String s) {
        this.status = s;
    }
    public Task(String token) {
        this.description = token;
    }
    public Task markDone() {
        this.status = "[X]";
        return this;
    }
    public Task unMarkDone() {
        this.status = "[ ]";
        return this;
    }
    public boolean descriptionHasWord(String toFind) {
        return this.description.indexOf(toFind) != -1;
    }
    @Override
    public String toString() {
        return String.format("%s %s", this.status, this.description);
    }
}
