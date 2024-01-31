public class ExitCommand extends Command{
    @Override
    public String execute(TaskList tasks, StateFile file) {
        return "Bye. Hope to see you again soon!";
    }
}
