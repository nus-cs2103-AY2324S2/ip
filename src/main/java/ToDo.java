public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String toFileFormat() {
        String completed = this.isDone ? "1" : "0";
        return "T | " + completed + " | " + this.description;
    }
}