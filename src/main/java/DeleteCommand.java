public class DeleteCommand extends Command{

    private final String index;

    public DeleteCommand(String index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        if (this.index.isBlank() || !this.index.matches("\\d+")) {
            ui.printErrInvalidIndex();
            return;
        }

        int idx = Integer.parseInt((index));

        if (idx > tasks.size() || idx <= 0) {
            ui.printErrInvalidIndex();
            return;
        }

        String taskDesc = tasks.get(idx - 1).toString();
        tasks.remove(idx - 1);
        ui.printDeleteTask(taskDesc, idx);
    }
}
