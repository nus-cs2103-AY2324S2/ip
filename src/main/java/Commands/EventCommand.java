package Commands;

import Irwyn.Exceptions.CommandException;
import Irwyn.Tasks.TaskList;
import Misc.StorageManager;
import Misc.Ui;
import Irwyn.Tasks.Event;

public class EventCommand extends Command {
    private final String[] event;
    public EventCommand(String input) throws CommandException {
        super(false);
        if (!input.contains(" /from ") || !input.contains(" /to ")) {
            throw new CommandException();
        }
        this.event = input.replaceFirst("event ", "").split(" /from ");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storageManager) {
        String description = event[0];
        String[] fromTo = event[1].split(" /to ");
        String from = fromTo[0];
        String to = fromTo[1];
        Event event = new Event(description, from, to);
        taskList.addTask(event);
        storageManager.save(taskList.getTasks());
        ui.reply(event.replyString(taskList.getTasksSize()));
    }
}
