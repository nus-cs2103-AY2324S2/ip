import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AddDeadline extends Command{
    private String description;
    private LocalDateTime date;
    public AddDeadline(Parser.Cmd type, String description, LocalDateTime date){
        super(type);
        this.description = description;
        this.date = date;
    }
    @Override
    public void run(TaskList taskList){
        Deadline ddl = new Deadline(this.description, this.date);
        String date = this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String[] data = {this.description, date};
        taskList.addTask(ddl, "deadline", data);
    }
}
