import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;


public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileOutputStream fileOut = new FileOutputStream("tasks.bin");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(tasks);
        out.close();
        fileOut.close();
    }

    public ArrayList<Task> loadTasks() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filePath);
        ObjectInputStream out = new ObjectInputStream(fileIn);

        @SuppressWarnings("unchecked")
        ArrayList<Task> tasks = (ArrayList<Task>) out.readObject();

        out.close();
        fileIn.close();

        return tasks;
    }
}
