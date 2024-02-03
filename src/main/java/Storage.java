import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Storage {
    private File saveFile;
    private TaskList tasks;

    Storage(String filePath) throws IOException {
        saveFile = new File(filePath);

        // Attempt to load TaskList from save file
        if (saveFile.exists()) {
            try {
                FileInputStream fis = new FileInputStream(saveFile);
                ObjectInputStream ois = new ObjectInputStream(fis);
                tasks = (TaskList) ois.readObject();
                saveTaskList(); // Rewrite task data as readObject deletes save data
                ois.close();
                fis.close();
            } catch (IOException | ClassNotFoundException | ClassCastException e) {
                // Read failed due to corrupt data
                tasks = new TaskList();
            }
        } else {
            // Create the necessary directories and file
            saveFile.getParentFile().mkdirs();
            saveFile.createNewFile(); // Throws Exception if unable to create, terminate the program
            tasks = new TaskList();
        }
    }

    TaskList getTaskList() {
        return tasks;
    }

    // Updates the save file with the task data
    // Reopen and close the ObjectOutputStream each time to override the file and update properly
    void saveTaskList() throws IOException {
        try{
            FileOutputStream fos = new FileOutputStream(saveFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.flush();
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
