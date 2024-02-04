package duke;

import java.time.LocalDate;

public class AddEventCommand extends Command {
    LocalDate start, deadline;
    public AddEventCommand(String description,  LocalDate start, LocalDate deadline) {
        super(description, CommandType.ADD);
        this.start = start;
        this.deadline = deadline;
    }

    @Override
    public void execute(State state, Ui ui) {
        Task newEvent = new Event(
                getText(),
                start,
                deadline,
                false
        );
        state.addTask(newEvent);
        ui.say("I added!–\n" + newEvent +  "\n–Mamma-mia!");
    }
}
