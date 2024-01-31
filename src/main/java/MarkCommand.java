public class MarkCommand extends Command {
    private final int index;
    public MarkCommand(int index) {
        super("", CommandType.MARK);
        this.index = index;
    }

    @Override
    public void execute(State state) {
        int taskNo = this.index - 1;
        Task task = state.getTask(taskNo);
        if (task == null) {
            System.out.println("Mamma-mia! This task no exist-o!");
            return;
        }
        task.setDone(true);
        System.out.println("Mamma-mai! I've marked it done!");
        System.out.println(task);
    }
}
