import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public Scanner load() throws FileNotFoundException {
        File f = new File(this.filePath);
        return new Scanner(f);
    }

    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String text = "";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task instanceof Event) {
                text += "E,";
                text += task.isDone()? "X,":" ,";
                text += task.getName() + ",";
                text += ((Event) task).getFrom() + ",";
                text += ((Event) task).getTo() + "\n";
            } else if (task instanceof Deadline) {
                text += "D,";
                text += task.isDone()? "X,":" ,";
                text += task.getName() + ",";
                text += ((Deadline) task).getBy() + "\n";
            } else {
                text += "T,";
                text += task.isDone()? "X,":" ,";
                text += task.getName();
                text += "\n";
            }
        }
        fw.write(text);
        fw.close();
    }
}
