package commands;

import task.Task;
import ui.Ui;

import java.util.Objects;

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
     *
     * @return
     */
    @Override
    public String execute() {
        Task task = new Task(name.trim());
        taskList.add(task);
        Ui.printVLine();
        String addedMessage = "Got it! task.Task has been added:\n" + task+ "\nNow you have "
                + taskList.getList().size() + " tasks in the list.";
        System.out.println(addedMessage);
        Ui.printVLine();
        assert Objects.equals(task.getTask(), name.trim()) : "Failed at TodoCommand: Task name is not the same.";
        return addedMessage;
    }
}
