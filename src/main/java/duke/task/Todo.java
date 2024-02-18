package duke.task;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.InputStreamReader;
import java.io.FileReader;
/**
 * Represents an Todo task that inherits from Task
 */
public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }

    /**
     * Overrides Task.add() to specify the Todo task to be added.
     *
     * @return String representation of Todo task to be added.
     */
    @Override
    public String add() {
        return this.getCat() + this.marked() + " " + this.getTask();
    }

    /**
     * Overrides Task.writeToFile() to specify the Todo task to be added to the File.
     *
     * @param filePath Filepath to the file to be written to.
     * @throws IOException When file does not exist.
     */
    @Override
    public void writeToFile(File filePath) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath.getPath(), true);
            fw.write(this.getCat() + this.marked() + " " + this.getTask() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("file not found! try again bb");
        }
    }



}
