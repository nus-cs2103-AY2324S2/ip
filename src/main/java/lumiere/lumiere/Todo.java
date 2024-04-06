package lumiere.lumiere;

public class Todo extends Task {

    /**
     * Constructor for Todo object
     * 
     * @param item   The item that describes what the todo is about, i.e. what needs
     *               to be done
     * @param marked A boolean that indicates whether this todo is marked as done or
     *               not.
     * @param fun    A boolean that indicates whether this todo is marked as fun or
     *               not.
     * @return Nothing, it is a constructor.
     */
    public Todo(String item, boolean marked, boolean fun) {
        super(item, marked, fun);
    }

    @Override
    public String stringify() {
        String m = "";
        if (super.isMarked())
            m = "[X]";
        else
            m = "[ ]";

        if (super.isFun())
            return "[T]" + m + " " + super.stringify() + " #fun";
        else
            return "[T]" + m + " " + super.stringify();
    }
}