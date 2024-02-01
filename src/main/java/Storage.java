import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected Path filePath;
    private boolean warningPrinted = false;
    public Storage(String filePath) {
       this.filePath = Paths.get(filePath);
    }

    private void printWarning() {
        if (!warningPrinted) {
            System.err.println("Warning: Invalid input will be ignored and overwritten after new task is added.\n" +
                    "To prevent overwriting, type 'bye' to quit.");
            warningPrinted = true;
        }
    }

    private void prepareFile() throws DukeException{
        try {
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath.getParent());
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            throw new DukeException("Unable to create task file in " + this.filePath);
        }
    }

    public ArrayList<Task> loadTasks() throws DukeException{
        prepareFile();
        ArrayList<Task> tasks = new ArrayList<>();

        try (Scanner myScannerObj = new Scanner(filePath)) {
            int lineNumber = 1;

            while (myScannerObj.hasNext()) {
                String line = myScannerObj.nextLine();
                try {
                    Task t = this.load(line);
                    tasks.add(t);
                } catch (DukeException e) {
                    printWarning();
                    System.err.println(lineNumber + ": " + e.getMessage());
                }
                lineNumber++;
            }

        } catch (IOException e) {
            throw new DukeException("Unable to load tasks from task file.");
        }

        return tasks;
    }

    public void saveTasks(ArrayList<Task> tasks) throws DukeException{
        prepareFile();
        try {
            ArrayList<String> lines = new ArrayList<>();
            for (Task t : tasks) {
                lines.add(t.toFileString());
            }
            Files.write(filePath, lines, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new DukeException("Unable to save tasks to task file.");
        }
    }


    public Task load(String line) throws DukeException{
        String[] parts = line.split(" \\| ");

        if (parts.length < 3 || !parts[1].matches("[01]")) {
            throw new DukeException("Invalid task format. Please check the task file.");
        }

        String taskType = parts[0];
        boolean isDone = Integer.parseInt(parts[1]) == 1;
        String description = parts[2];

        switch (taskType) {
            case "T":
                Task tT = new Todo(description);
                tT.setDone(isDone);
                return tT;
            case "D":
                Task tD = new Deadline(description, parts[3]);
                tD.setDone(isDone);
                return tD;
            case "E":
                String[] time = parts[3].split(" - ");
                if (!(time.length == 2)) {
                    throw new DukeException("Invalid time format. " +
                            "Please use <datetime> - <datetime>" );
                }

                Task tE = new Event(description,time[0], time[1]);
                tE.setDone(isDone);
                return tE;
            default:
                throw new DukeException("Invalid task type " + taskType);
        }
    }
}
