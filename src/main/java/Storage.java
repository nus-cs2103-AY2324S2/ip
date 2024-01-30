import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private String filePath;
    public Storage (String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadList() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filePath);
        ObjectInputStream out = new ObjectInputStream(fileIn);
        @SuppressWarnings("unchecked")
        ArrayList<Task> task = (ArrayList<Task>) out.readObject();
        out.close();
        fileIn.close();
        return task;
    }

    public void saveTasks(ArrayList<Task> taskList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(taskList);
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
            e.printStackTrace();
        }
    }
}
