package duke;

import model.Task;

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
            System.out.println("Unable to create new file.");
        }
    }

    public void update(ArrayList<Task> taskList) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(this.file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(taskList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Task> load() {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            @SuppressWarnings("unchecked")
            ArrayList<Task> taskList = (ArrayList<Task>) objectInputStream.readObject();
            return taskList;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
