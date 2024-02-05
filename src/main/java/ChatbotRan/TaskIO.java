package ChatbotRan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
                switch (contents[0]) {
                case "D":
                    tasks.add(new Deadline(contents[1],contents[2]));
                    break;
                case "E":
                    tasks.add(new Event(contents[1],contents[2],contents[3]));
                    break;
                case "T":
                    tasks.add(new Todo(contents[1]));
                    break;
                }
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
            for (Task t: tasks) {
                bw.write(t.writeTask());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
