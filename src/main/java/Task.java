import java.util.UUID;

/**
 * Class representing a user Task.
 */
public class Task {

    protected String description;
    protected boolean done = false;

    protected String uuid;

    public Task(String description) {
        this.description = description;
        this.uuid = UUID.randomUUID().toString();
    }

    public Task setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Task updateStatus(boolean state) {
        this.done = state;
        return this;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public String toSavableFormat() {
        return this.uuid + "|T|" + this.description + "|" + this.done;
    }

    public String getStatus() {
        return "[" + (done ? "X" : " ") + "]";
    }

    public String getType() {
        return "[T]";
    }
}
