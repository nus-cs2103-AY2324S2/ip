class ListCommand implements Command {

    public void execute(TaskList tasks, Ui ui) throws DukeException {
        ui.listMessage(tasks.toString());
    }

    public boolean isExit() {
        return false;
    }
}
