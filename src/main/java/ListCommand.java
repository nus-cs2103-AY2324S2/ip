public class ListCommand extends Command{
    @Override
    public String execute(TaskList tasks, StateFile file) {
        return "Here are the tasks in your list:\n" + tasks.toString();
    }
}
