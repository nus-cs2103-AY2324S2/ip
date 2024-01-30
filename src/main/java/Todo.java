public class Todo extends Task{
    public Todo(String name) {
        super(name);
    }

    @Override
    public String toSavedString() {
        return String.format("T,%s,%s"
                , this.done ? '1' : '0'
                , this.name);
    }

    public String toString() {
        return String.format("[T][%s] %s\n", this.done ? "X" : " ", this.name);
    }
}
