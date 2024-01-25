import java.io.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ClassEscapesDefinedScope")
public class FileHandler {
    private final String filePath;

    public FileHandler(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadTasks() throws IOException {
        List<Task> tasks = new ArrayList<>();
        File file = getFile();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                Task task;
                switch (parts[0]) {
                    case "T":
                        task = new Todo(parts[2]);
                        break;
                    case "D":
                        task = new Deadline(parts[2], parts[3]);
                        break;
                    case "E":
                        task = new Event(parts[2], parts[3], parts[4]);
                        break;
                    default:
                        continue;
                }
                if (parts[1].equals("1")) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new IOException("Error reading from file: " + filePath, e);
        }

        return tasks;
    }

    private File getFile() throws IOException {
        File file = new File(filePath);

        File parentDir = file.getParentFile();
        if (!parentDir.exists()) {
            boolean dirsCreated = parentDir.mkdirs();
            if (!dirsCreated) {
                throw new IOException("Unable to create directories: " + parentDir.getAbsolutePath());
            }
        }

        if (!file.exists()) {
            boolean fileCreated = file.createNewFile();
            if (!fileCreated) {
                throw new IOException("Unable to create new file: " + file.getAbsolutePath());
            }
        }
        return file;
    }

    public void saveTasks(List<Task> tasks) throws IOException {
        try (PrintWriter pw = new PrintWriter(filePath)) {
            for (Task task : tasks) {
                String line = taskToFileString(task);
                pw.println(line);
            }
        } catch (IOException e) {
            throw new IOException("Error writing to file: " + filePath, e);
        }
    }

    private String taskToFileString(Task task) {
        String type = task instanceof Todo ? "T" :
                task instanceof Deadline ? "D" :
                        task instanceof Event ? "E" : "";
        String status = task.isDone() ? "1" : "0";
        String description = task.description;

        String details = type + " | " + status + " | " + description;
        if (task instanceof Deadline) {
            details += " | " + ((Deadline) task).by;
        } else if (task instanceof Event) {
            details += " | " + ((Event) task).from + " | " + ((Event) task).to;
        }
        return details;
    }
}