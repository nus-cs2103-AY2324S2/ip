import java.time.LocalDate;
import java.time.LocalTime;

public class AddEventCommand extends AddCommand {
    private String description;
    private LocalDate fromDate;
    private LocalTime fromTime;
    private LocalDate toDate;
    private LocalTime toTime;

    public AddEventCommand(TaskList taskList, String description,
                           LocalDate fromDate, LocalTime fromTime,
                           LocalDate toDate, LocalTime toTime) {
        super(taskList);
        this.description = description;
        this.fromDate = fromDate;
        this.fromTime = fromTime;
        this.toDate = toDate;
        this.toTime = toTime;

    }

    public static String getUsage() {
        return Command.getUsage() + " add event <description> /from <YYYY-MM-DD> [HH:MM] /to <YYYY-MM-DD> [HH:MM]";
    }

    @Override
    public void execute() {
        super.getTaskList().add(new Event(description, false, fromDate, fromTime, toDate, toTime));
        Ui.printMessage("Added Event task: " + description
                + "\n(from: " + fromDate + (fromTime == null ? "" : " " + fromTime)
                + "\n   to: " + toDate + (toTime == null ? "" : " " + toTime) + ")");
    }
}
