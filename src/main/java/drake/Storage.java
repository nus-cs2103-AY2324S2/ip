package drake;
import java.io.*;
import java.util.ArrayList;

import drake.task.Task;

public class Storage {
    private final String FILE_PATH;

    public Storage(String FILE_PATH) {
        this.FILE_PATH = FILE_PATH;
    }

    public void saveTasks(ArrayList<Task> tasks) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Error saving list!: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Task> loadTasks() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (ArrayList<Task>) ois.readObject();
            } catch (IOException e) {
                return new ArrayList<>();
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found!: " + e.getMessage());
            }
        }
        return new ArrayList<>();
    }
}