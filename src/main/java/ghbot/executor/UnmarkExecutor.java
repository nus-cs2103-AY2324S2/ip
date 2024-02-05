package ghbot.executor;

public class UnmarkExecutor extends Executor {
    private int lstNo;

    public UnmarkExecutor(int lstNo) {
        this.lstNo = lstNo;
    }

    @Override
    public void execute() {
        for (int i = 0; i < this.taskList.taskSize(); i++) {
            if (i + 1 == lstNo) {
                this.taskList.getTask(i).isNotCompleted();
                System.out.println("OK, I've marked this task as not done yet:\n" + this.taskList.getTask(i));
            }
        }
    }
}
