import java.io.*;
public class Storage {
    protected static String dataPath = "./data/duke.txt";
    protected static TaskList loadTasks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataPath))) {
            return (TaskList) ois.readObject();
        } catch (IOException | ClassCastException | ClassNotFoundException e) {
            // do nothing
        }
        return new TaskList();
    }

    protected static void saveTasks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataPath))) {
            oos.writeObject(Duke.lst);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
