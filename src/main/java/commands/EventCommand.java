package commands;

import irwyn.exceptions.CommandException;
import irwyn.tasks.Event;
import irwyn.tasks.TaskList;
import misc.StorageManager;
import misc.Ui;

/**
 * This class encapsulates the class EventCommand.
 * It executes the creation of an Event object.
 *
 * @author Irwyn Liong
 * @version Week-3
 */
public class EventCommand extends Command {
    private final String[] event;

    /**
     * Constructor for a EventCommand object.
     * @param input The input by the user to parse into a command.
     * @throws CommandException if the Event command is invalid.
     */
    public EventCommand(String input) throws CommandException {
        super(false);
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new CommandException();
        }
        this.event = input.replaceFirst("event ", "").split(" /from ");
    }

    /**
     * Executes the event command.
     * This method creates an Event object.
     *
     * @param taskList TaskList handles the tasks list.
     * @param ui Ui handles output.
     * @param storageManager Storage manager handles storing & deleting of tasks.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        String description = event[0];
        String[] fromTo = event[1].split(" /to ");
        String from = fromTo[0];
        String to = fromTo[1];
        Event event = new Event(description, from, to);
        if (!isDuplicate(event, taskList)) {
            taskList.addTask(event);
        } else {
            ui.reply("This task already exists in the task list.");
        }
        storageManager.save(taskList.getTasks());
        ui.reply(event.replyString(taskList.getTasksSize()));
    }

    /**
     * Executes the event command.
     * This method creates an Event object.
     *
     * @param storageManager Storage manager handles storing & deleting of tasks.
     * @param ui Ui handles output.
     * @param taskList TaskList handles the tasks list.
     * @return Response String.
     */
    @Override
    public String execute(StorageManager storageManager, Ui ui, TaskList taskList) {
        String description = event[0];
        String[] fromTo = event[1].split(" /to ");
        String from = fromTo[0];
        String to = fromTo[1];
        Event event = new Event(description, from, to);
        if (!isDuplicate(event, taskList)) {
            taskList.addTask(event);
        } else {
            return ui.getReply("This task already exists in the task list.");
        }
        storageManager.save(taskList.getTasks());
        return ui.getReply(event.replyString(taskList.getTasksSize()));
    }
}
