public class MarkCommand extends Command {
    private String inputs;

    public MarkCommand(String command) {
        inputs = command;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (inputs.isEmpty()) {
            throw new DukeMissingArgument(1,"mark");
        }
        try {
            int index = Integer.valueOf(inputs);
            ui.sendReply(tasks.mark(index));
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgument(inputs);
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
