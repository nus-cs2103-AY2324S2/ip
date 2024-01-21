public class Action {
    private String marking;
    private String execute;

    /**
     * Constructor for Action (User input their task)
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
     * @return return the task queried
     */
    public String getExecute() {
        return this.execute;
    }

    /**
     *
     * @return return the status of the task
     */
    public String getMarking() {
        return this.marking;
    }
}
