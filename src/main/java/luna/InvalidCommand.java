package luna;

/**
 * Represents a invalid command which will shows the user what it the error they have committed
 */
public class InvalidCommand extends Command {
    private String errorMessage;

    /**
     * Construct a Command to have invalid command type and assign its default error default
     */
    public InvalidCommand() {
        super(CommandType.INVALID);
        this.errorMessage = "Unrecognised Command. Please Try Again";
    }

    /**
     * Overloaded the constructor to replace with a new error message
     *
     * @param errMsg new error message
     */
    public InvalidCommand(String errMsg) {
        super(CommandType.INVALID);
        this.errorMessage = errMsg;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        ui.shiftedPrint(this.errorMessage);
    }

}
