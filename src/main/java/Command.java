public abstract class Command {
    private final String[] commandArr;

    public Command(String[] commandArr) {
        this.commandArr = commandArr;
    }

    public abstract runCommand(TaskList taskList, SaveFile saveFile, Ui ui);
}
