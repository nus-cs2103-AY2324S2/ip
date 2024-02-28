public class ListCommand extends Command{
    public ListCommand() {
        super();
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("\t" + "Read it yourself.");
        for (int i = 0; i < TaskList.list.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + TaskList.list.get(i).notPrint());
        }
    }
}
