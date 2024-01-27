import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {
    Ui ui;
    TaskList taskList;
    String dataPath;
    String fileName;
    public Storage(Ui ui, TaskList taskList, String dataPath, String fileName) {
        this.ui = ui;
        this.taskList = taskList;
        this.dataPath = dataPath;
        this.fileName = fileName;
    }
    public void loadData() {
        try {
            Files.createDirectories(Paths.get(dataPath));
            File data = new File(dataPath + fileName);
            if (!data.createNewFile()) {
                BufferedReader br = new BufferedReader(new FileReader(data));
                String line;
                while ((line = br.readLine()) != null) {
                    boolean currTaskDone;
                    currTaskDone = !line.substring(line.lastIndexOf(' ') + 1).equals("incomplete");
                    line = line.substring(0, line.lastIndexOf(' '));
                    Task task = taskList.taskCreator(line);
                    task.setTaskDone(currTaskDone);
                    this.taskList.add(task);
                }
            }
        } catch (IOException e) {
            ui.printError("\tUnfortunately, an error occurred. Please try again! We try our utmost best to satisfy you. UwU :3");
        }
    }

    public void saveData() {
        try {
            PrintWriter writer = new PrintWriter(dataPath + fileName);
            for (Task t : taskList.tasks) {
                writer.println(t.inputItem + t.completed);
            }
            writer.close();
        } catch (IOException e) {
            ui.printError("\tUnfortunately, an error occurred. Please try again! We try our utmost best to satisfy you. UwU :3");
        }
    }
}
