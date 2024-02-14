package commands;

import task.Deadline;
import ui.Ui;

/**
 * Encapsulates a deadline command.
 */
public class DeadlineCommand extends Command {

    public static final String COMMAND = "deadline";
    private final String name;
    private final String by;

    public DeadlineCommand(String name, String by) {
        this.name = name;
        this.by = by;
    }

    /**
     * Creates a Deadline and adds into TaskList.
     *
     * @return
     */
    @Override
    public String execute() {
        Deadline deadline = new Deadline(name, by.trim());
        taskList.add(deadline);
        Ui.printVLine();
        System.out.println("Got it! Deadline has been added:\n" + deadline + "\nNow you have "
                + taskList.getList().size() + " tasks in the list.");
        Ui.printVLine();
        assert deadline.getTask().equals(name.trim()) : "Failed at DeadlineCommand: Task name is not the same.";
        return "Got it! Deadline has been added:\n" + deadline + "\nNow you have "
                + taskList.getList().size() + " tasks in the list.";
    }
}
