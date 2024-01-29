import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     *
     * @param description
     * @throws ArrayIndexOutOfBoundsException
     */
    public static void addTask(String description) throws ArrayIndexOutOfBoundsException {
        String[] tokens = description.split("/", 2);
        String[] tokens2 = tokens[0].split(" ", 2);
        String taskName = "";
        if (tokens2.length == 1)
            throw new ArrayIndexOutOfBoundsException("The description of a deadline cannot be empty.");

        for (int i = 1; i < tokens2.length; i++) {
            taskName += tokens2[i] + " ";
        }
        Deadline curr = new Deadline(taskName.strip(), tokens[1].split(" ")[1]);
        Duke.taskList.add(curr);
        Database.writeFile(description);
        if (!Duke.initialize) {
            System.out.println("Yer task has been added: \n  " + curr);
            Task.getNumberOfTasks();
        }
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}

