import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task() {
        this.isDone = false;
    }

    public Task(String description) {
        this.description = description;
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

    public static Task fromString(String task) {
        String[] inputs = task.split(" \\| ", 4);
        String type = inputs[0];
        String status = inputs[1];
        String description = inputs[2];
        String date = "";
        if (inputs.length > 3) {
            date = inputs[3];
        }


        switch (type) {
            case "T":
                ToDo todo = new ToDo(description);
                if (status.equals("X")) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy 'at' hh:mma");
                LocalDateTime d = LocalDateTime.parse(date, formatter);
                DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mma");
                String formatted = customFormat.format(d);
                Deadline dl = new Deadline(description, formatted);
                if (status.equals("X")) {
                    dl.markAsDone();
                }
                return dl;
            case "E":
                String[] time = date.split("-");
                Event e = new Event(description, time[0], time[1]);
                if (status.equals("X")) {
                    e.markAsDone();
                }
                return e;
            default:
                return null;
        }

    }

    @Override
    public String toString() {
        return getStatusIcon() + " | " + description;
    }
}
