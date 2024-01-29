public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui) {
        System.out.println("Here are your tasks!");
        System.out.println(tasks);
    }
}
