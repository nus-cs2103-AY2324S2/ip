import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from);
        this.to = LocalDate.parse(to);
    }

    public static void addTask(String description) throws ArrayIndexOutOfBoundsException {
        String[] tokens = description.split("/", 3);
        String[] tokens2 = tokens[0].split(" ", 2);
        String taskName = "";

        if (tokens2.length == 1)
            throw new ArrayIndexOutOfBoundsException("The description of a deadline cannot be empty.");

        for (int i = 1; i < tokens2.length; i++) {
            taskName += tokens2[i] + " ";
        }
        Event curr = new Event(taskName.strip(), tokens[1].split(" ")[1],
                tokens[2].split(" ")[1]);
        Duke.taskList.add(curr);
        Database.writeFile(description);
        if (!Duke.initialize) {
            System.out.println("Yer task has been added: \n  " + curr);
            Task.getNumberOfTasks();
        }
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + " to " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")" ;
    }
}
