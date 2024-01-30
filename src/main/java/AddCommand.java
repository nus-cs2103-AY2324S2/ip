public class AddCommand extends Command {

    private final Parser.TaskType tasktype;
    private final String description;

    AddCommand(Parser.TaskType tasktype, String description) {
        this.tasktype = tasktype;
        this.description = description;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DookException {
        Task toAdd = Parser.getTask(tasktype, description);
        tasks.addTask(toAdd);
        ui.println("Oki! I've added this task:");
        ui.println(toAdd.toString());
        tasks.printStatus();
        storage.write(tasks);
    }
}
