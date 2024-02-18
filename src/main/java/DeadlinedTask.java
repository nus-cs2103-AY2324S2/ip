import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlinedTask extends Task {

    private LocalDateTime deadline;

    public DeadlinedTask(String name, LocalDateTime deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {

        String taskString = "[D] ";

        String temp = "[ ] ";

        if(this.isDone()){
            temp = "[X] ";
        }

        return taskString + temp + this.getName() + "(by: " + this.deadline.format(DateTimeFormatter.ofPattern("d MMMM yyyy h:mm a")) + ")";
    }
}
