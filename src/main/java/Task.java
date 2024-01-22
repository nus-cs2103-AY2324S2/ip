public class Task {
    private String name;
    private boolean status;

    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    public void updateStatus(boolean status) {
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
        if (this.status) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

}
