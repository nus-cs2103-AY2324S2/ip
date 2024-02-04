import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static final String FILE_PATH = "./data/duke.txt";
<<<<<<< HEAD:src/main/java/Storage.java

    public Storage(String filePath) {
        
    }

    private static void ensureFolderAndFileExists() {
        File folder = new File("./data");
        File file = new File(folder, "/duke.txt");
=======

    private static void ensureFolderAndFileExists() {
        File folder = new File("./data");
        File file = new File(folder, "duke.txt");
>>>>>>> branch-Level-7:src/main/java/SaveTask.java

        if(!folder.exists()) {
            // Creates the directory named by this abstract pathname, 
            // including any necessary but nonexistent parent directories
            folder.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();

            } catch (IOException e) {
                System.err.println("Error creating new file; " + e.getMessage());
            }
        }
    }
    
    public static void saveTasks(ArrayList<Task> tasks) {
        ensureFolderAndFileExists();
        // OOS writes primitive data types and graphs of Java objects to an OutputStream
        // Both FileInputStream and FileOutputStream create byte streams linked to files
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            // Open an OOS for writing to the files
            oos.writeObject(tasks);

        } catch (IOException e) {
            // Catch any IOException that might occur during the file writing process
            System.err.println("Error saving tasks:" + e.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = null;

        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            tasks = (ArrayList<Task>) ois.readObject();
            
        } catch (FileNotFoundException e) {
            System.out.println("No existing data file found. Starting with an empty task list");

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading tasks: Corrupted data file. Starting with an empty task list.");
        }

        return tasks;
    }

}
