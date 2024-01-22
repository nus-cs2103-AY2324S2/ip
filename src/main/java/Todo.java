public class Todo extends Task {
    public Todo(String name) {
        super(name);
    }

    public static Todo fromStr(String input) {
        return new Todo(input);
    }

    @Override
    public String toString() {
        return  "[T]" + super.toString();
    }
}
