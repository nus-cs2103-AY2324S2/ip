public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        this.statusMsg = "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
