package eueu.task;

import java.io.IOException;
import java.io.File;
import java.io.FileWriter;

/**
 * Represents an Todo task that inherits from Task
 */
public class Todo extends Task {
    static final String FILE_NOT_FOUND = "file not found! try again xx";

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
            if (!this.isDone()) {
                fw.write("T/0/" + this.getTask() + "\n");
            } else {
                fw.write("T/1/" + this.getTask() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(FILE_NOT_FOUND);
        }
    }



}
