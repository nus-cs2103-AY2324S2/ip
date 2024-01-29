public class UnmarkCommand extends Command {
    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws WeiException {
        try {
            int order = Integer.parseInt(input.substring(7)) - 1;
            String unmarkedTask = tasks.unmark(order);
            ui.showUnmarkedMessage(unmarkedTask);
        } catch (NumberFormatException e) {
            throw new WeiException("which task do you want to mark?");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}