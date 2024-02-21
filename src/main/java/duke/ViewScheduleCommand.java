package duke;

import java.time.LocalDate;

/**
 * Class that represents the View Schedule command.
 */
public class ViewScheduleCommand extends Command {
    private final LocalDate date;
    /**
     * Constructor for the view schedule command.
     */
    public ViewScheduleCommand(LocalDate date) {
        super("");
        this.date = date;
    }

    /**
     * Lists all the tasks from the provided state
     * based on the given date.
     *
     * @param state The state of the app.
     * @param ui    The user interface of the app.
     * @return Text output of the command
     */
    @Override
    public String execute(State state, Ui ui) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= state.getTasks().size(); i++) {
            Task task = state.getTask(i - 1);
            boolean shouldAddTask = false;
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getByWhen().isEqual(date)) {
                    shouldAddTask = true;
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (date.isAfter(event.start) && date.isBefore(event.deadline)) {
                    shouldAddTask = true;
                }
            }
            if (shouldAddTask) {
                sb.append(String.format("%d: %s\n", i, task));
            }
        }
        if (sb.length() == 0) {
            sb.append("No Tasks falls within that date! Oopsie!");
        }
        assert sb.length() != 0;
        return sb.toString();
    }
}
