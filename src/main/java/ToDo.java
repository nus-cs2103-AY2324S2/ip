public class ToDo extends Task {
    /**
     * Constructor for ToDo class.
     *
     * @param description Description of ToDo object.
     */
    public ToDo(String description) {
        super(description);
        this.type = 'T';
    }
    public String toString() {
        return "[T]" + super.toString();
    }
}
