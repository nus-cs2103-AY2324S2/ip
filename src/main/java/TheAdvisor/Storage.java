package TheAdvisor;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class Storage implements Serializable {
    private String filePath;
    public Storage (String filePath) {
        this.filePath = filePath;
    }

    public TaskList loadList() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream(filePath);
        ObjectInputStream out = new ObjectInputStream(fileIn);
        @SuppressWarnings("unchecked")
        TaskList task = (TaskList) out.readObject();
        out.close();
        fileIn.close();
        return task;
    }

    public void saveTasks(TaskList taskList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(taskList);
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
            e.printStackTrace();
        }
    }
}
