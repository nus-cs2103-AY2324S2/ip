import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
public abstract class Task {
    private boolean isDone;
    private String name;

    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String checkBox = isDone ? "[X]" : "[ ]";
        return String.format("%s %s", checkBox, name);
    }

    public String dataString() {
        return String.format("%s|%s", this.isDone, this.name);
    }

    public abstract void writeToData(String filePath) throws IOException;
}
