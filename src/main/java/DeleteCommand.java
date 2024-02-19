public class DeleteCommand extends Command {
    DeleteCommand(String command) {
        super(command);
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {
        int index = Integer.parseInt(this.command.split(" ")[1]) - 1;
        ui.showDeleteTask(index, tasks);
        tasks.deleteTask(index);
        storage.writeFile(tasks);
    }
}
