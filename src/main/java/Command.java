public abstract class Command {
    void execute(TaskList tasks, Ui ui) throws Exception {
    }

    boolean isExit() {
        return false;
    }
}
