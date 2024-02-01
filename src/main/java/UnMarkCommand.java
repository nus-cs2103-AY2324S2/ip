public class UnMarkCommand extends Command {
    private int zeroItem;

    UnMarkCommand(int oneItem) {
        this.zeroItem = oneItem - 1;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int oneItem = zeroItem - 1;
        if (oneItem < 1 || oneItem > tasks.getSize() || tasks.get(oneItem - 1) == null) {
            throw new DukeException("\nError! Task number '" + oneItem + "' does not exist.\n");
        }
        tasks.unMarkAsDone(zeroItem);
        System.out.println("\nOK, I've marked this task as not done yet:\n\t" + tasks.get(zeroItem) + "\n");
        storage.saveList(tasks.getTasks());
    }
}
