import java.io.*;
import java.util.ArrayList;

public class Database {
    private static final String DATABASE_DIRECTORY = "./data/";
    private static final String DATABASE_PATH = "./data/task_list.txt";

    private ArrayList<Task> taskList;

    public Database() {
        this.taskList = readTasks();
    }

    public void add(Task task) {
        taskList.add(task);
        this.writeTasks();
    }

    public void mark(int i, boolean isDone) throws ConvoBotException {
        if (i < 0 || i >= taskList.size()) {
            throw new ConvoBotException("Invalid input. You must specify a valid index.");
        }
        if (isDone) {
            taskList.get(i).markAsDone();
        } else {
            taskList.get(i).markAsNotDone();
        }
        this.writeTasks();
    }

    public void remove(int i) throws ConvoBotException {
        if (i < 0 || i >= taskList.size()) {
            throw new ConvoBotException("Invalid input. You must specify a valid index.");
        }
        taskList.remove(i);
        this.writeTasks();
    }

    public int size() {
        return taskList.size();
    }

    public String getTaskString(int i) {
        return taskList.get(i).toString();
    }

    private static ArrayList<Task> readTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File directory = new File(DATABASE_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            File file = new File(DATABASE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTaskFromLine(line);
                taskList.add(task);
            }
            reader.close();
        } catch (IOException e) {
        }
        return taskList;
    }

    private static Task parseTaskFromLine(String line) throws IllegalArgumentException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3 || parts.length > 5) {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }
        boolean isDone = (parts[1] == "1" ? true : false);
        String description = parts[2];
        Task task;
        try {
            switch (parts[0]) {
            case "T":
                task = new ToDo(description, isDone);
                break;
            case "D":
                task = new Deadline(description, isDone, DateTime.stringToDate(parts[3]));
                break;
            case "E":
                task = new Event(description, isDone, DateTime.stringToDate(parts[3]), DateTime.stringToDate(parts[4]));
                break;
            default:
                throw new IllegalArgumentException("Invalid line format: " + line);
            }
        } catch (ConvoBotException e) {
            throw new IllegalArgumentException("Invalid line format: " + line);
        }
        
        return task;
    }

    private void writeTasks() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(DATABASE_PATH));
            for (Task task : taskList) {
                writer.write(task.toFile());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
        }
    }
}
