public class AddEventCommand extends Command {
    String start, deadline;
    public AddEventCommand(String description,  String start, String deadline) {
        super(description, CommandType.ADD);
        this.start = start;
        this.deadline = deadline;
    }

    @Override
    public void execute(State state) {
        Task newEvent = new Event(
                getText(),
                start,
                deadline,
                false
        );
        state.addTask(newEvent);
        System.out.println("I added!–\n" + newEvent +  "\n–Mamma-mia!");
    }
}
