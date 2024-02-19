public class MarkCommand extends Command {
    MarkCommand(String command) {
        super(command);
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {
        int index = Integer.parseInt(this.command.split(" ")[1]) - 1;
        tasks.markTask(index);
        ui.showMarkTask(tasks, index);
        storage.writeFile(tasks);
    }
}
