public class ListCommand extends Command {
    public ListCommand(String[] commandArr) {
        super(commandArr);
    }

    @Override
    public void runCommand(TaskList taskList, SaveFile saveFile, Ui ui) {
        System.out.println(taskList);
    }
}
