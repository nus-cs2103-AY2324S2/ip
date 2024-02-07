abstract class Command {
    abstract void execute(Storage storage, TaskList taskList) throws DukeException;

    boolean isExit() {
        return false;
    }
}