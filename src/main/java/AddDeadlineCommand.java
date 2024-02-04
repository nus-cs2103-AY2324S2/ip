import java.time.LocalDate;

public class AddDeadlineCommand extends Command {
    LocalDate deadline;
    public AddDeadlineCommand(String description, LocalDate deadline) {
        super(description, CommandType.ADD);
        this.deadline = deadline;
    }

    @Override
    public void execute(State state, Ui ui) {
        Task newDeadline = new Deadline(
                getText(),
                deadline,
                false
        );
        state.addTask(newDeadline);
        ui.say("I added!–\n" + newDeadline +  "\n–Mamma-mia!");
    }
}
