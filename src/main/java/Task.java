import java.time.LocalDateTime;
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

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public static Task parseTask(String taskString) {
        String cat = taskString.substring(1, 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        switch (cat) {
            case "T":
                Todo t = new Todo(taskString.substring(7).trim());
                if (taskString.substring(4, 5).equals("X")) {
                    t.markAsDone();
                } else {
                    t.markAsUndone();
                }
                return t;
            case "D":
                String byString = taskString.split(":")[1].split("\\)")[0].trim();
                LocalDateTime by = LocalDateTime.parse(byString, formatter);
                String description = taskString.split("\\(")[0].substring(7).trim();
                Deadline d = new Deadline(description, by);
                if (taskString.substring(4, 5).equals("X")) {
                    d.markAsDone();
                } else {
                    d.markAsUndone();
                }
                return d;
            case "E":
                int fromIndex = taskString.indexOf("from: ");
                int toIndex = taskString.indexOf("to: ");
                String fromString = taskString.substring(fromIndex + 5, toIndex).trim();
                LocalDateTime from = LocalDateTime.parse(fromString, formatter);
                String toTimeString = taskString.substring(toIndex + 3).split("\\)")[0].trim();
                LocalDateTime to = LocalDateTime.parse(toTimeString, formatter);
                String des = taskString.split("\\(")[0].substring(7).trim();
                Event e = new Event(des, from, to);
                if (taskString.substring(4, 5).equals("X")) {
                    e.markAsDone();
                } else {
                    e.markAsUndone();
                }
                return e;
            default:
                System.out.println("Cannot resolve task.");
                return null;
        }
    }
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
