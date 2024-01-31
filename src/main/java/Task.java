import java.util.Arrays;

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
                String by = taskString.split(":")[1].split("\\)")[0].trim();
                String description = taskString.split("\\(")[0].substring(7).trim();
                Deadline d = new Deadline(description, by);
                if (taskString.substring(4, 5).equals("X")) {
                    d.markAsDone();
                } else {
                    d.markAsUndone();
                }
                return d;
            case "E":
                String from = taskString.split(":")[1].trim();
                String to = taskString.split(":")[2].split("\\)")[0].trim();
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
