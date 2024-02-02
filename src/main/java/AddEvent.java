import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddEvent extends Command{
    private String description;
    private LocalDateTime fromDate;

    private LocalDateTime toDate;
    public AddEvent(Parser.Cmd type, String description, LocalDateTime fromDate, LocalDateTime toDate){
        super(type);
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    @Override
    public void run(TaskList taskList){
        Event event = new Event(this.description, this.fromDate, this.toDate);
        String fromTime = this.fromDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
        String toTime = this.toDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"));
        String[] data = {this.description, fromTime, toTime};
        taskList.addTask(event, "event", data);
    }
}
