public class ToDo extends Task {
    ToDo(String description) {
        super(description);
    }

    @Override
    public String toFileString() {
        return String.format("T | %s | %s",this.getStatusIcon(), this.getDescription());
    }

    @Override
    public String toString() {
        return String.format("[T]%s",super.toString());
    }
}
