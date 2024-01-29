public class CheckDateCommand extends Command {
    private String[] commandArr;

    public CheckDateCommand(String[] commandArr) {
        this.commandArr = commandArr;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.checkDate(commandArr.length > 1 ? commandArr[1] : "");
    }
}
