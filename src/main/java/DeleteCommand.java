public class DeleteCommand extends Command {
    private int index;
    private TaskList taskList;

    public DeleteCommand(TaskList taskList, int index) {
        this.taskList = taskList;
        this.index = index;
    }

    public static String getUsage() {
        return Command.getUsage() + " delete <taskIndex>";
    }

    @Override
    public void execute() throws MeanDukeException {
        try {
            Ui.printMessage("deleted task:\n" + this.taskList.delete(index));
        } catch (IndexOutOfBoundsException e) {
            throw new MeanDukeException("Dude... you don't even have a task " + (this.index + 1));
        }
    }
}
