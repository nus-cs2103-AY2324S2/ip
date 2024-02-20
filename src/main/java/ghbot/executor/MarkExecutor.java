package ghbot.executor;

/**
 * MarkExecutor Class.
 * Executes "mark" command.
 */
public class MarkExecutor extends Executor {
    private int lstNo;
    private String executeStr;

    /**
     * MarkExecutor Constructor.
     * @param lstNo The index of the task to mark.
     */
    public MarkExecutor(int lstNo) {
        this.lstNo = lstNo;
        this.executeStr = "";
    }

    /**
     * Returns a string to let user know that the selected task is marked.
     * @return A string to let user know that the selected task is marked.
     */
    @Override
    public String execute() {
        for (int i = 0; i < this.taskList.taskSize(); i++) {
            if (i + 1 == this.lstNo) {
                this.taskList.getTask(i).isCompleted();
                this.executeStr = "Nice! I've marked this task as done:\n" + this.taskList.getTask(i);
                return this.executeStr;
            }
        }
        return "Sorry, you may have select a task that is out of scope!\n"
                + "Please select a number from 1 to " + taskList.taskSize();
    }
}
