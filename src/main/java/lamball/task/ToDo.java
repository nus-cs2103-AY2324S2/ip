package lamball.task;

public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }


    @Override
    public String command() {
        return "todo " + description;
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
