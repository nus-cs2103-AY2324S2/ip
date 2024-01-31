public class ListCommand extends Command {

    public static final String COMMAND = "list";

    @Override
    public void execute() {
        Ui.printAllTask(tasks.getList());
    }

}
