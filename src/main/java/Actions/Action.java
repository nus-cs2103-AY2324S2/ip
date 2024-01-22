package Actions;

public class Action {
    private String marking;
    private String execute;

    /**
     * Constructor for Actions.Action (User input their task)
     *
     * @param execute task noted
     */
    public Action(String execute) {
        this.execute = execute;
        this.marking = " ";
    }

    /**
     * Mark the task with X
     */
    public void markIt() {
        this.marking = "X";
    }

    /**
     * Unmark the task by leaving it blank
     */
    public void unMark() {
        this.marking = " ";
    }

    /**
     * Override toString to print the task
     *
     * @return task and its status
     */
    @Override
    public String toString() {
        return "[" + this.marking + "] " + this.execute;
    }
}
