public class UnMarkCommand extends Command {
    UnMarkCommand(String command) {
        super(command);
    }

    void execute(TaskList tasks, Ui ui, Storage storage) {
        int index = Integer.parseInt(this.command.split(" ")[1]) - 1;
        tasks.unMarkTask(index);
        ui.showUnMarkTask(tasks, index);
        storage.writeFile(tasks);
    }
}
