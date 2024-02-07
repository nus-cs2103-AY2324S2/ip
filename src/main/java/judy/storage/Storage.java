package judy.storage;
import judy.task.*;

import java.io.*;
import java.util.ArrayList;


public class Storage {
    private final File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public boolean isFileExists() {
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            System.out.println("Error checking file existence: " + e);
            return false;
        }
    }

    public void createNewFile() {
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to create new file");
        }
    }

    public ArrayList<Task> loadTasks() {
        if (file.length() == 0) {
            return new ArrayList<>(); // Handle empty file or non-existing file
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            @SuppressWarnings("unchecked")
            ArrayList<Task> taskList = (ArrayList<Task>) ois.readObject();
            return taskList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void save(ArrayList<Task> taskList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.file))) {
            oos.writeObject(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
