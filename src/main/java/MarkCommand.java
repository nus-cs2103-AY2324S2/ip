public class MarkCommand extends Command {
    private final String message;
    public MarkCommand(String message) {
        super();
        this.message = message;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String number = message.split(" ")[1];
            int n = Integer.parseInt(number);
            taskList.mark(n);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showForgetTaskNumber();
            ui.showMarkFormat();
        } catch (NumberFormatException e) {
            ui.showWrongFormat();
            ui.showMarkFormat();
        }
    }
    public  boolean isExit() {
        return false;
    }
}
