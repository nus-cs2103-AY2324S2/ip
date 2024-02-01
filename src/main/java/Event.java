import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public void writeTask(String path) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path, true))){
            out.write("E |" + (this.isDone ? " 1 | " : " 0 | ") + this.getDescription()
                    + " | " + this.from + " | " + this.to);
            out.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}