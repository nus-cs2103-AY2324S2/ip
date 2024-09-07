package ghbot.executor;

/**
 * MarkExecutor Class.
 * Executes "mark" instruction.
 */
public class MarkExecutor extends Executor {
    private int listNo;
    private String executeStr;

    /**
     * MarkExecutor Constructor.
     * @param listNo The index of the task to mark.
     */
    public MarkExecutor(int listNo) {
        this.listNo = listNo;
        this.executeStr = "";
    }

    /**
     * Returns a string to let user know that the selected task is marked.
     * @return A string to let user know that the selected task is marked.
     */
    @Override
    public String execute() {
        for (int i = 0; i < taskList.taskSize(); i++) {
            if (i + 1 == this.listNo) {
                taskList.getTask(i).isCompleted();
                this.executeStr = "Nice! I've marked this task as done:\n" + taskList.getTask(i);
                return this.executeStr;
            }
        }
        return "Sorry, you may have select a task that is out of scope!\n"
                + "Please select a number from 1 to " + taskList.taskSize();
    }
}
