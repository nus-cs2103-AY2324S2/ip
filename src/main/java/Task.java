import exceptions.InvalidStatusUpdateException;

public class Task {
    protected String name;
    protected boolean status;

    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    public void updateStatus(boolean status) throws InvalidStatusUpdateException {
        if (this.status == status) {
            throw new InvalidStatusUpdateException();
        }
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public boolean getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return "[T]" + (this.status ? "[X] " : "[ ] ") + this.name;
    }

}
