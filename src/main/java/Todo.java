public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public void updateStatus(boolean status) {
        super.status = status;
    }

    public String getName() {
        return super.name;
    }

    public boolean getStatus() {
        return super.status;
    }

    @Override
    public String toString() {
        return "[T]" + (super.status ? "[X] " : "[ ] ") + super.name;
    }
}
