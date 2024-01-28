import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String name;
    private boolean isDone;
    private String type;
    private String[] times;
    private static DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("d/M/yyyy EEEE, ha");
    private static DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public void changeIsDone() {
        this.isDone ^= true;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getType() {
        return this.type;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String[] getTimes() {
        return this.times;
    }

    public String getName() {
        return this.name;
    }

    public String outputDateAsString(LocalDateTime dateTime) {
        return dateTime.format(outputFormat);
    }

    public String changeDateToString(LocalDateTime dateTime) {
        return dateTime.format(inputFormat);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
