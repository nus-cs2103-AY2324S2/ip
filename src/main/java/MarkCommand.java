public class MarkCommand extends Command{
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) throws WeiException {
        try {
            int order = Integer.parseInt(input.substring(5)) - 1;
            String markedTask = tasks.mark(order);
            ui.showMarkedMessage(markedTask);
        }
        catch (NumberFormatException e) {
            throw new WeiException("which task do you want to mark?");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
