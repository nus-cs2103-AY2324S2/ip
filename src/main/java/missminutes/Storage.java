package missminutes;

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

    public void saveTasks(TaskList taskList) throws MissMinutesException {
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(taskList);
            out.close();
            fileOut.close();
        } catch (IOException err) {
            throw new MissMinutesException("Failed to save to storage to " + filePath + " : " + err.getMessage());
        }
    }

    public TaskList loadTasks() throws MissMinutesException {
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream out = new ObjectInputStream(fileIn);

            TaskList tasks = (TaskList) out.readObject();

            out.close();
            fileIn.close();
            return tasks;
        } catch (Exception err) { // Not just IOException and ClassNotFound, as MissMinutes.Task definition might've changed
            throw new MissMinutesException("Failed to load storage from " + filePath);
        }
    }
}
