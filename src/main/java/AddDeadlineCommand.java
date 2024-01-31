public class AddDeadlineCommand extends Command {
    String deadline;
    public AddDeadlineCommand(String description, String deadline) {
        super(description, CommandType.ADD);
        this.deadline = deadline;
    }

    @Override
    public void execute(State state) {
        Task newDeadline = new Deadline(
                getText(),
                deadline,
                false
        );
        state.addTask(newDeadline);
        System.out.println("I added!–\n" + newDeadline +  "\n–Mamma-mia!");
    }
}
