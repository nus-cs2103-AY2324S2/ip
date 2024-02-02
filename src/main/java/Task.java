import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void markDone() {
        this.isDone = true;
    }
    
    public void markUndone() {
        this.isDone = false;
    }
    
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
    
    public static String formatDateForPrinting(LocalDate date) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return date.format(outputFormatter);
    }
}
