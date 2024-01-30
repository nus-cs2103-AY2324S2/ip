import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Todo extends Task {
    public Todo(String task) {
        super(task);
    }



    public String isTodo() {
        return "[T]";
    }

    public String addTodo() {
        return "Got it. I've added this task: \n"
                + "    " + this.isTodo() + this.marked() + " " + this.getTask();
    }

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
