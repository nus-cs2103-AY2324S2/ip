public class ExitCommand extends Command {
    public ExitCommand(){
    }

    public void execute(TaskList tasks, Storage storage) {
    }
    public static boolean isExit(Command command) {
        return command instanceof ExitCommand;
    }
}
