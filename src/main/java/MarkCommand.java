public class MarkCommand extends Command {
    private int index;

    public MarkCommand (int index) {
        this.index = index;
    }

    @Override
    public void execute (Ui ui) {
        try {
            if (index < 0 || index >= super.taskList.getSize()) {
                throw new DukeException("Oh no!!! Invalid task index!");
            }
            super.taskList.markTask(index);
            ui.printMarked(super.taskList.getTask(index));
        } catch (DukeException e) {
            ui.printError(e);
        }
        
    }
}