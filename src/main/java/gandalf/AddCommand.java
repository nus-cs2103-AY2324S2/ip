package gandalf;

/**
 * AddCommand calls the TaskList object's add() method. It also stores the modified list and prints out the total
 * number of tasks so far/
 */
public class AddCommand extends Command {

    private String taskName;
    private String startDate;
    private String endDate;
    public AddCommand(String commandName, TaskList tasks, Storage storage, Ui ui, String... otherInputs) {
        super(commandName, tasks, storage, ui, otherInputs);
        this.taskName = otherInputs[0];
        this.startDate = otherInputs[1];
        this.endDate = otherInputs[2];
    }

    @Override
    public void execute() throws GandalfException {
        tasks.add(commandName, taskName, startDate, endDate);
        storage.store(tasks.getList());
        System.out.println("Total number of tasks so far: " + (tasks.getList().size()));
    }
}
