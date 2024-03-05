package duke.task;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Encapsulates the tasks that the user has.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs the class Task.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns "X" if the task is Done and " " if the task is Undone.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the task as Done.
     */
    public void setDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as Undone.
     */
    public void setUndone() {
        this.isDone = false;
    }

    /**
     * Returns the String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    /**
     * Returns a String that is used to save the details of the task in a file.
     */
    public String fileString() {
        return this.getStatusIcon() + "/" + this.getDescription();
    }

    /**
     * Write the task into a file.
     *
     * @param filePath The path of the file to store the task.
     */
    public void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath,true);
        String fileString = fileString();
        fw.write(fileString + System.lineSeparator());
        fw.close();
    }
}
