import java.time.LocalDateTime;

public class Deadline extends Task{
    private LocalDateTime date;

    public Deadline(String description, LocalDateTime date){
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date + ")";
    }
}
