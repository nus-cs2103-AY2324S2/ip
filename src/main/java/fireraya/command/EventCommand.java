package fireraya.command;

import fireraya.exception.FirerayaException;
import fireraya.main.Storage;
import fireraya.main.TaskList;
import fireraya.main.Ui;
import fireraya.task.Event;

/**
 * Class to handle an Event Command in the program.
 *
 * This class is extended from the Command superclass.
 */
public class EventCommand extends Command{

    private String description;
    private String from;
    private String to;

    /**
     * Constructor for the Event Command.
     *
     * @param description String description of the event.
     * @param from String description of starting date/time of event.
     * @param to String description of ending date/time of event.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Overrides the format to execute the command.
     *
     * @param tasks the Tasklist of program.
     * @param ui the Ui of the program.
     * @param storage the storage of the program.
     * @return String representing the message to be displayed to the user.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FirerayaException {
        Event e = new Event(description, from, to);
        tasks.add(e);
        String a = ui.taskAddedMsg(e, tasks.size());
        storage.saveToFile(tasks.getTasks());
        return a;
    }

}
