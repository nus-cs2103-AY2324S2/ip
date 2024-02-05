package ghbot.executor;

public class DeleteExecutor extends Executor {
    private int lstNo;
    public DeleteExecutor(int lstNo) {
        this.lstNo = lstNo;
    }
    @Override
    public void execute() {
        System.out.println("Noted. I've removed this task:\n" + this.taskList.getTask(lstNo - 1));
        this.taskList.deleteTask(lstNo - 1);
        System.out.println("Now you have " + this.taskList.taskSize() + " tasks in the list.");
    }
}
