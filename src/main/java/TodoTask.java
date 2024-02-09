public class TodoTask extends Task {
    private String type;

    /**
     * Constuctor for Task object of type "todo"
     *
     * @param what description of the task
     */
    public TodoTask(String what) {
        super(what);
        this.type = "[T]";
    }

    /**
     * Returns string showing task type, completion status and description.
     *
     * @return string of task type, marked/unmarked status and description
     */
    public String showAll() {
        return this.type + super.showAll();
    }
}
