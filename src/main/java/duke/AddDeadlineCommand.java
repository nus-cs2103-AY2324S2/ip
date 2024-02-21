package duke;

import java.time.LocalDate;

/**
 * Class that represents the command to add a deadline.
 */
public class AddDeadlineCommand extends Command {
    LocalDate deadline;

    /**
     * Constructor for the add deadline command.
     *
     * @param description The description of the deadline.
     * @param deadline    The end date of the deadline.
     */
    public AddDeadlineCommand(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    /**
     * Adds a deadline task to the state.
     *
     * @param state The state of the app.
     * @param ui    The user interface of the app.
     * @return Text output of the command
     */
    @Override
    public String execute(State state, Ui ui) {
        Task newDeadline = new Deadline(
                getText(),
                deadline,
                false);
        state.addTask(newDeadline);
        return "I added!–\n" + newDeadline + "\n–Mamma-mia!";
    }
}
