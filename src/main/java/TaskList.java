import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    protected void addTask(Ui ui, Storage st, String cmd, String[] args) throws InvalidTaskException {
        Task t = null;
        if (cmd.equals("todo")) {
            t = new Todo(args[0]);
        } else if (cmd.equals("deadline")) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            try {
                LocalDate d = LocalDate.parse(args[1], formatter);
                t = new Deadline(args[0], d);
            } catch (DateTimeParseException de) {
                System.out.println("Date not in format: yyyy-MM-dd, please try again.");
            }
        } else if (cmd.equals("event")) {
            t = new Event(args[0], args[1], args[2]);
        } else {
            throw new InvalidTaskException("Invalid task syntax for " + cmd + ".");
        }

        if (t != null) {
            tasks.add(t);
            st.save(this.tasks);
            ui.echo("Got it. I've added this task:\n  "
                    + t + "\n"
                    + "Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    protected void deleteTask(Ui ui, Storage st, String[] args) {
        Task t = tasks.get(Integer.parseInt(args[0]) - 1);
        tasks.remove(t);
        st.save(this.tasks);
        ui.echo("Noted. I've removed this task:\n  "
                + t + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }

    protected void list(Ui ui) {
        int count = 0;
        System.out.println("Here are the tasks in your list:");
        for (Task t : tasks) {
            count++;
            System.out.println(count + ". " + t);
        }
        ui.showLine();
    }

    protected void mark(Ui ui, Storage st, String[] args) {
        Task t = tasks.get(Integer.parseInt(args[0]) - 1);
        t.markAsDone();
        st.save(this.tasks);
        ui.echo("Nice! I've marked this task as done:\n"
                + t);
    }

    protected void unmark(Ui ui, Storage st, String[] args) {
        Task t = tasks.get(Integer.parseInt(args[0]) - 1);
        t.markUndone();
        st.save(this.tasks);
        ui.echo("OK, I've marked this task as not done yet:\n"
                + t);
    }
}