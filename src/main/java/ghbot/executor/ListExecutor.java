package ghbot.executor;

/**
 * ListExecutor Class.
 * Executes "list" command.
 */
public class ListExecutor extends Executor {
    private String executeStr;

    /**
     * ListExecutor Constructor.
     */
    public ListExecutor() {
        this.executeStr = "";
    }

    /**
     * Returns all the saved tasks.
     * @return A string containing all the saved tasks.
     */
    @Override
    public String execute() {
        if (taskList.taskSize() < 1) {
            return "Currently there is no task!";
        }
        this.executeStr = "Here are the tasks in your list:";
        for (int i = 0; i < this.taskList.taskSize(); i++) {
            this.executeStr = this.executeStr + "\n" + (i + 1) + "." + this.taskList.getTask(i);
        }
        return this.executeStr;
    }
}
