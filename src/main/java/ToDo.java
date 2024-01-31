public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getSaveFormat() {
        return "todo | " + (this.isDone ? "1 " : "0 | ") + this.description;
    }
}
