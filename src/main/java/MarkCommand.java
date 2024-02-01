public class MarkCommand extends Command{
    private int zeroItem;

    MarkCommand(int oneItem) {
        this.zeroItem = oneItem - 1;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int oneItem = zeroItem - 1;
        if (oneItem < 1 || oneItem > tasks.getSize() || tasks.get(oneItem - 1) == null) {
            throw new DukeException("\nError! Task number '" + oneItem + "' does not exist.\n");
        }
        tasks.markAsDone(zeroItem);
        System.out.println("\nNice! I've marked this task as done:\n" + tasks.get(zeroItem) + "\n");
        storage.saveList(tasks.getTasks());
    }
}
