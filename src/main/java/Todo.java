public class Todo extends Task {
    private String name;
    private boolean done;
    private static String identifier = "[T]";
    public Todo(String name) {
        super(name);
        this.name = name;
        this.done = false;
    }

    @Override
    public String toString() {
        String str = identifier + super.toString();
        return str;
    }

    public String getInput() {
        String str = String.format("todo %s", name);
        return str;
    }
}
