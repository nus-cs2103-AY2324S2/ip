public class ToDo extends Task {
    public ToDo(String name) {
        super(name, "T");
    }
    public String toString() {
        String status = this.complete ? "[x] " : "[ ]";
        return "[T] " + status + " " + this.name;
    }
}

