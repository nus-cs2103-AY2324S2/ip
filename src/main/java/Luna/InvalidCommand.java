package Luna;

public class InvalidCommand extends Command {
    String errorMessage;

    public InvalidCommand() {
        super(CommandType.INVALID);
        this.errorMessage = "Unrecognised Command. Please Try Again";
    }
    public InvalidCommand(String errMsg) {
        super(CommandType.INVALID);
        this.errorMessage = errMsg;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        ui.shifted_print(this.errorMessage);
    }

}
