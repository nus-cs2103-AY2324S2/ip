import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    private void rewriteFile(TaskList taskList) throws IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write("T | 1 | dummy offset\n");
            ArrayList<Task> todoList = taskList.getTodoList();
            for (int i = 0; i < todoList.size(); i++) {
                todoList.get(i).toFileString();
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void appendToFile(String line) {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(line);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
