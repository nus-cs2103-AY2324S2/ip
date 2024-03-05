package ChatbotRan;

import java.io.*;
import java.util.ArrayList;

public class TaskIo {
    public final String name;

    public TaskIo(String s) {
        this.name = s;
        File taskFile = new File(name);
        taskFile.getParentFile().mkdir();
    }

    public ArrayList<Task> findTasks() {
        File taskFile = new File(name);
        return readTasks(taskFile);
    }

    private ArrayList<Task> readTasks(File taskFile) {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(taskFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] contents = line.split("\\\\");
                boolean completed = Boolean.parseBoolean(contents[1]);
                Task t;
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
        File taskFile = new File(name);
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
