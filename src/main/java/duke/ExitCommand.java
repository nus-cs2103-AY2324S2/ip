package duke;

class ExitCommand implements Command {

    public String execute(TaskList tasks, Ui ui) throws DukeException {
        return "";
    }

    public boolean isExit() {
        return true;
    }
}
