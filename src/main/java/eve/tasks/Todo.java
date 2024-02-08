package eve.tasks;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, String isDone) {
        super(description);
        if (isDone.equals("0")) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }

    @Override
    public String toString() {
        return "[T]" + "[" + super.getStatusIcon() + "] " + super.toString();
    }

    @Override
    public String toStore() {
        return "T" + " | " + super.getStatusInteger() + " | " + super.toString() + "\n";
    }

}
