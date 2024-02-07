package commands;

import task.Task;
import ui.Ui;

/**
 * Encapsulates a todo command.
 */
public class TodoCommand extends Command {

    public static final String COMMAND = "todo";
    private final String name;

    public TodoCommand(String s) {
        this.name = s;
    }

    /**
     * Adds a Task into TaskList.
     */
    @Override
    public void execute() {
        Task task = new Task(name.trim());
        tasks.add(task);
        Ui.printVLine();
        System.out.println("Got it! task.Task has been added:\n" + task+ "\nNow you have "
                + tasks.getList().size() + " tasks in the list.");
        Ui.printVLine();

    }
}
