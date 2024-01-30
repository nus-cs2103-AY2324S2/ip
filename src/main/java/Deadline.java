import java.time.LocalDate;
import java.time.LocalDateTime;

public class Deadline extends Task{
    private LocalDateTime date;

    public Deadline(String description, String date){
        super.description = description;
        this.date = LocalDateTime.parse(date);
    }

    public Deadline(String description, String date, boolean isDone){
        super.description = description;
        super.isDone = isDone;
        this.date = LocalDateTime.parse(date);
    }

    @Override
    public String getTaskIcon() {
        return "D";
    }

    @Override
    public String toWrite(){
        return "D | " + super.toWrite() + " | " + this.date;
    }

    @Override
    public String toString(){
        return this.description + " (by: "+ this.date + ")";
    }
}
