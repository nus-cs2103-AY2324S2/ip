public class Todo extends Task {
    public Todo(String item) {
        super(item);
    }

    @Override
    public String stringify() {
        String m = "";
        if (super.isMarked())
            m = "[X]";
        else
            m = "[ ]";
        return "[T]" + m + " " + super.stringify();
    }
}
