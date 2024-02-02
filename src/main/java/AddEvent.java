import java.time.LocalDateTime;

public class AddEvent extends Command{
    private String description;
    private LocalDateTime fromDate;

    private LocalDateTime toDate;
    public AddEvent(String description, LocalDateTime fromDate, LocalDateTime toDate){
        this.description = description;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }
    @Override
    public void run(TaskList taskList){
        Event event = new Event(this.description, this.fromDate, this.toDate);
        taskList.addTask(event);
    }
}
