public class Todo extends Task{
    public Todo(String name) {
        super(name);
    }

    public String toString() {
        return String.format("[T][%s] %s\n", this.done ? "X" : " ", this.name);
    }
}
