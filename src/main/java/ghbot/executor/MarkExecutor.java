package ghbot.executor;

public class MarkExecutor extends Executor {
    private int lstNo;
    public MarkExecutor(int lstNo) {
        this.lstNo = lstNo;
    }

    @Override
    public void execute() {
        for (int i = 0; i < this.taskList.taskSize(); i++) {
                if (i + 1 == this.lstNo) {
                    this.taskList.getTask(i).isCompleted();
                    System.out.println("Nice! I've marked this task as done:\n" + this.taskList.getTask(i));
                }
            }
    }
}
