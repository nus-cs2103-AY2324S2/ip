// package lumiere.tasks;

// import lumiere.*;

public class Todo extends Task {
    public Todo(String item, boolean marked) {
        super(item, marked);
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