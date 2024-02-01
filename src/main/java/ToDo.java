public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }
    @Override
    public String toString() {
        return String.format("[T][%s] %s", getStatusIcon(), this.description);
    }
    @Override
    public String getSaveLine() {
        return "T " + (this.isDone ? "1 " : "0 ") + this.description + "\n";
    }
}
