package lumiere.lumiere;

public class Todo extends Task {

    /**
     * Constructor for Todo object
     * 
     * @param item   The item that describes what the todo is about, i.e. what needs
     *               to be done
     * @param marked A boolean that indicates whether this todo is marked as done or
     *               not.
     * @return Nothing, it is a constructor.
     */
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