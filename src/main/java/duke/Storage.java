package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Storage {

    private TaskList taskList;

    public Storage(TaskList taskList) {
        this.taskList = taskList;
    }

    public void setupTaskList(TaskList tasks) {
        this.taskList = tasks;
    }
    
    protected void loadTasks() {
        File file = new File(Duke.DATA_FILE);

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = Parser.createTaskFromFile(line);
                this.taskList.addTask(task);
            }
        } catch (FileNotFoundException e) {
            ErrorHandler.handleFileNotFoundException(Duke.DATA_FILE);
        }
    }

    protected void saveTasks() {
        try (PrintWriter writer = new PrintWriter(Duke.DATA_FILE)) {
            for (Task task : this.taskList.getTasks()) {
                writer.println(task.toFileFormat());
            }
        } catch (IOException e) {
            ErrorHandler.handleIOException();
        }
    }
}
