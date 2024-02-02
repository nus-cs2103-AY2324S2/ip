import java.io.FileWriter;
import java.io.IOException;
import java.util.ListIterator;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void load() throws IOException {
        // Check for directory / file existence
        String home = System.getProperty("user.home");
        java.nio.file.Path directoryPath = java.nio.file.Paths.get(home, "data");
        boolean directoryExists = java.nio.file.Files.exists(directoryPath);

        if (!directoryExists) {
            java.nio.file.Files.createDirectory(directoryPath);
        }

        java.nio.file.Path filePath = java.nio.file.Paths.get(home, "data", "Bond.txt");
        boolean fileExists = java.nio.file.Files.exists(filePath);

        if (!fileExists) {
            java.nio.file.Files.createFile(filePath);
        }
    }

    public void storeTask(Task newTask, TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(this.filePath, true); // create a FileWriter in append mode
        fw.write(newTask.toString());
        fw.write(System.lineSeparator());
        fw.close();
    }

    public void overwritePreviousSave(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath, false); // create a FileWriter in overwrite mode
        ListIterator<Task> toWrite = taskList.getTasks();

        while (toWrite.hasNext()) {
            // System.out.println("I have reached Loop in " + "updateFile");
            fw.write(toWrite.next().toString());
            fw.write(System.lineSeparator());
        }

        fw.close();
    }

    public void save() {
        System.out.println("Saving data to file...");

    }
}
