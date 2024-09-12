package luna.command;

import java.time.LocalDate;

import luna.Storage;
import luna.TaskList;
import luna.Ui;
import luna.entry.ListEntryEvent;

/**
 * Represents an event command which to add an event task to the task list
 */

public class EventCommand extends Command {

    private String taskName;
    private LocalDate startDate;
    private LocalDate endDate;

    /**
     * Construct a Command to have event command type and assign its task name, start date and end date
     *
     * @param tn task name
     * @param sd start date
     * @param ed end date
     */

    public EventCommand(String tn, LocalDate sd, LocalDate ed) {
        super(CommandType.EVENT);
        this.taskName = tn;
        this.startDate = sd;
        this.endDate = ed;
    }

    /**
     * Attach the reference tasklist, ui and storage to execute the command on.
     * Adds a new event task to the tasklist
     *
     * @param tl the tasklist
     * @param ui the program ui
     * @param storage listfile storage
     */
    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        super.execute(tl, ui, storage);
        tl.add(new ListEntryEvent(taskName, false, startDate, endDate));
        ui.shiftedPrint("Added Deadline task");
    }



}
