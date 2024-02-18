import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {

    private LocalDateTime start, end;

    public EventTask(String name, LocalDateTime start, LocalDateTime end) {
        super(name);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {

        String taskString = "[E] ";

        String temp = "[ ] ";

        if(this.isDone()){
            temp = "[X] ";
        }

        return taskString + temp + this.getName() + "(from: " + this.start.format(DateTimeFormatter.ofPattern("d MMMM yyyy h:mm a")) + " to: " + this.end.format(DateTimeFormatter.ofPattern("d MMMM yyyy h:mm a")) + ")";
    }

}