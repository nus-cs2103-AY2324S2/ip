public class UnmarkCommand extends Command {
    public String message;
    public UnmarkCommand(String message) {
        super();
        this.message = message;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            String number = message.split(" ")[1];
            int n = Integer.parseInt(number);
            taskList.unmark(n);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showForgetTaskNumber();
            ui.showUnmarkFormat();
        } catch (NumberFormatException e) {
            ui.showUnmarkFormat();
        }
    }
    public  boolean isExit() {
        return false;
    }
}
