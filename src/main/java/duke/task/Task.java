package duke.task;
import java.io.FileWriter;
import java.io.IOException;

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
        return description;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public String fileString() {
        return this.getStatusIcon() + "/" + this.getDescription();
    }

    public void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        String fileString = fileString();
        fw.write(fileString + System.lineSeparator());
        fw.close();
    }
}
