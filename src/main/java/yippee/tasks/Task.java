package yippee.tasks;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents tasks instantiated by user.
 */
public class Task {
    private boolean isDone;
    private String name;

    /**
     * Instantiates a new task.
     * @param name String representation of name of the new task.
     */
    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }
    public String getName() {
        return this.name;
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

    /**
     * Writes current task details to storage file.
     * @param filePath
     * @throws IOException
     */
    public void writeToData(String filePath) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        String writeData = dataString();
        fileWriter.write(writeData);
        fileWriter.close();
    }
}
