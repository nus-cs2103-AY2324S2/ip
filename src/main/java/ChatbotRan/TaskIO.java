package ChatbotRan;

import java.io.*;
import java.util.ArrayList;

public class TaskIO {
    public final String FILENAME = "ran.txt";

    public ArrayList<Task> findTasks() {
        File dataFolder = new File("data");
        dataFolder.mkdir();
        File taskFile = new File(dataFolder, FILENAME);
        return readTasks(taskFile);
    }

    private ArrayList<Task> readTasks(File taskFile) {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(taskFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] contents = line.split("\\\\");
                boolean completed = Boolean.parseBoolean(contents[1]);
                Task t = null;
                switch (contents[0]) {
                case "D":
                    t = new Deadline(contents[2], contents[3]);
                    break;
                case "E":
                    t = new Event(contents[2], contents[3], contents[4]);
                    break;
                case "T":
                    t = new Todo(contents[2]);
                    break;
                default:
                    throw new IOException("Data is corrupted");
                }
                t.setCompleted(completed);
                tasks.add(t);
            }
        } catch (FileNotFoundException | IndexOutOfBoundsException e) {
            return tasks;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    public void writeTasks(ArrayList<Task> tasks) {
        File dataFolder = new File("data");
        dataFolder.mkdir();
        File taskFile = new File(dataFolder, FILENAME);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(taskFile))) {
            for (Task t : tasks) {
                bw.write(t.writeTask());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
