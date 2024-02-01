package taskList;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void saveTasks(TaskList theList) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.filePath))) {
            objectOutputStream.writeObject(theList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TaskList loadTasks() {
        if (!Files.exists(Paths.get(filePath))) {
            try {
                Files.createDirectories(Paths.get(filePath).getParent());
                Files.createFile(Paths.get(filePath));
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("An unexpected error has occurred. \n" + e.getMessage() + "\nPlease contact the admininstrator"); 
            }
        }
        
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            Object obj = objectInputStream.readObject();
            if (obj instanceof TaskList) {
                return (TaskList) obj;
            }
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
        
        return new TaskList(new ArrayList<>());

    }

    
}
