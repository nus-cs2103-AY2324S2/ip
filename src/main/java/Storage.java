import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filePath;
    Storage(String filePath) {
        this.filePath = filePath;
    }

    //Try to load the task
    //And get the items
    public TaskManager loadFile() throws DukeException {
        TaskManager manager = new TaskManager();
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        } else {
            File storage = new File(filePath);
            try {
                if (!storage.createNewFile()) {

                    manager.loadTasksFromFile(new File(filePath));
                }
            } catch (IOException e) {
                throw new DukeException("Stupid thing won't load");
            }
        }
        return manager;
    }

    public void saveFile(TaskManager manager) {
        if (manager.getUpdate()) {
            try (FileWriter fw = new FileWriter(filePath)) {
                fw.write(manager.getTasksSave());
                manager.setUpdate(false);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
