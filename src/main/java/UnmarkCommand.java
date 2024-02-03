public class UnmarkCommand  extends Command {
    private int index;

    public UnmarkCommand (int index) {
        this.index = index;
    }

    @Override
    public void execute (Ui ui) {
        try {
            if (index < 0 || index >= super.taskList.getSize()) {
                throw new DukeException("Oh no!!! Invalid task index!");
            }
            super.taskList.unmarkTask(index);
            ui.printUnmarked(super.taskList.getTask(index));
        } catch (DukeException e) {
            ui.printError(e);
        }
        
    }
}
