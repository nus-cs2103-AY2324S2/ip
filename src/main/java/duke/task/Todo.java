package duke.task;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import duke.task.Task;
/**
 * Represents an Todo task that inherits from Task
 */
public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }


    /**
     * Returns the String that specifies the Todo task
     * @return Todo category.
     */
    public String isTodo() {
        return "[T]";
    }

    /**
     * Overrides Task.add() to specify the Todo task to be added.
     *
     * @return String representation of Todo task to be added.
     */
    @Override
    public String add() {
        return "    " + this.isTodo() + this.marked() + " " + this.getTask();
    }

    /**
     * Overrides Task.writeToFile() to specify the Todo task to be added to the File.
     *
     * @param filePath Filepath to the file to be written to.
     * @throws IOException When file does not exist.
     */
    @Override
    public void writeToFile(File filePath) {
      try {
          FileWriter fw = new FileWriter(filePath.getPath(), true);
          fw.write(this.isTodo() + this.marked() + " " + this.getTask() + "\n");
          fw.close();
      } catch (IOException e) {
          System.out.println("file not found! try again bb");
      }
    }

}
