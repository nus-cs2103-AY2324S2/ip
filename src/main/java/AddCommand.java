import java.time.LocalDate;

public class AddCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ReacherException {
        ui.print("What is the name of the task?");
        String name = ui.readString();
        ui.print("What type of task is this?(Deadline, Event, Todo)");
        String type = ui.readString();
        if (type.equalsIgnoreCase("todo")) {
            Todos t = new Todos(name);
            tasks.addTask(t);
            ui.print("I've added " + t.toString());
        } else if (type.equalsIgnoreCase("deadline")) {
            ui.print("When is the deadline?");
            LocalDate deadline = LocalDate.parse(ui.readString());
            Deadline t = new Deadline(name, deadline);
            tasks.addTask(t);
            ui.print("I've added " + t.toString());
        } else if (type.equalsIgnoreCase("event")) {
            ui.print("When is the start?");
            LocalDate start = LocalDate.parse(ui.readString());
            ui.print("When is the end?");
            LocalDate end = LocalDate.parse(ui.readString());
            if (start.isAfter(end)) {
                throw new ReacherException("End cannot be before start.");
            }
            Events t = new Events(name, start, end);
            tasks.addTask(t);
            ui.print("I've added " + t.toString());
        } else {
            throw new ReacherException("That is not a type of task.");
        }
        storage.storeList(tasks.getTasks());
    }
}
