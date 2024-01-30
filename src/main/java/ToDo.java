public class ToDo extends Task{
    public ToDo(String description, int isDone) {
        super(description, isDone);
    }

    public String toString() {
        return "[T]" + "[" + getStatusIcon() + "] " + this.description;
    }
}
