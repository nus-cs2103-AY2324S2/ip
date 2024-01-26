public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(String isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s%s", this.getStatusIcon(), super.toString());
    }
}
