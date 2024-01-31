package commands;

import exceptions.DukeException;
import task.Task;
import ui.Ui;

public class TodoCommand extends Command {

    public static final String COMMAND = "todo";
    private final String name;

    public TodoCommand(String s) {
        this.name = s;
    }

    @Override
    public void execute() throws DukeException {
        if (name.isEmpty()) {
            Ui.emptyTaskMessage();
        } else {
            Task task = new Task(name.trim());
            tasks.add(task);
            Ui.printVLine();
            System.out.println("Got it! task.Task has been added:\n" + task+ "\nNow you have "
                    + tasks.getList().size() + " tasks in the list.");
            Ui.printVLine();
        }
    }
}
