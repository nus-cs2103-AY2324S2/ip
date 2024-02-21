package duke;

import java.time.LocalDate;

/**
 * Class that represents the command to add an event.
 */
public class AddEventCommand extends Command {
    LocalDate start, deadline;

    /**
     * Constructor for the add event command.
     *
     * @param description The description of the event.
     * @param start       The start date of the event.
     * @param deadline    The end date of the event.
     */
    public AddEventCommand(String description, LocalDate start, LocalDate deadline) {
        super(description);
        this.start = start;
        this.deadline = deadline;
    }

    /**
     * Adds an event to the state.
     *
     * @param state The state of the app.
     * @param ui    The user interface of the app.
     * @return Text output of the command
     */
    @Override
    public String execute(State state, Ui ui) {
        Task newEvent = new Event(
                getText(),
                start,
                deadline,
                false);
        state.addTask(newEvent);
        return "I added!–\n" + newEvent + "\n–Mamma-mia!";
    }
}
