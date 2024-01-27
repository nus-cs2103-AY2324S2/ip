public class InvalidCommand extends Command {
    public InvalidCommand(String[] commandArr) {
        super(commandArr);
    }

    @Override
    public void runCommand(TaskList taskList, SaveFile saveFile, Ui ui) {
        ui.printInvalidCmdMsg();
    }
}
