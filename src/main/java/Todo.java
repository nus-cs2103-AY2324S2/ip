public class Todo extends Task {
    private String name;
    private boolean done;
    private static String identifier = "[T]";
    public Todo(String name) {
        super(name);
        this.name = name;
        this.done = false;
    }
    public Todo(String name, boolean done) {
        super(name);
        this.name = name;
        this.done = done;

    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X]" + identifier + " " + this.name;
        } else {
            return "[ ]" + identifier + " " + this.name;
        }
    }

    public String getInput() {
        String mark;
        if (this.done) {
            mark = "1";
        } else {
            mark = "0";
        }
        String str = String.format("%s:todo:%s", mark, name);
        return str;
    }
}
