public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui) {
        try {
            if (this.index < 0 || this.index >= super.taskList.getSize()) {
                throw new DukeException("Oh no!!! Invalid task index!");
            }
            Task taskDeleted = super.taskList.getTask(this.index);
            super.taskList.deleteTask(this.index);
            ui.printToUser("\tNoted. I've removed this task:",
                            "\t" + taskDeleted,
                            super.taskList.getListCounter());
        } catch (DukeException e) {
            ui.printError(e);
        }
    }    
}