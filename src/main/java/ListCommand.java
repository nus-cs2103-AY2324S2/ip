public class ListCommand extends Command{

    public ListCommand() {


    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println(ui.getBot() + "Here are your tasks:");
        for (int i = 1; i < tasks.size()+1; i++) {
            Task task = tasks.get(i-1);
            System.out.println("    " + i + ". " + task.toString());
        }

    }

    public boolean isExit() {
        return false;
    }
}
