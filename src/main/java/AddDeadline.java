import java.time.LocalDateTime;

public class AddDeadline extends Command{
    private String description;
    private LocalDateTime date;
    public AddDeadline(String description, LocalDateTime date){
        this.description = description;
        this.date = date;
    }
    @Override
    public void run(TaskList taskList){
        Deadline ddl = new Deadline(this.description, this.date);
        taskList.addTask(ddl);
    }
}
