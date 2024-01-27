package kaiyap;

import java.io.File;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Storage {
    private final Ui ui;
    private final TaskList taskList;
    private final String dataPath;
    private final String fileName;

    /**
     * Constructor for creating a Storage object.
     *
     * @param ui        The UI object to interact with.
     * @param taskList  The TaskList object to store tasks from.
     * @param dataPath  The file path for storing data.
     * @param fileName  The file name for storing data.
     */
    public Storage(Ui ui, TaskList taskList, String dataPath, String fileName) {
        this.ui = ui;
        this.taskList = taskList;
        this.dataPath = dataPath;
        this.fileName = fileName;
    }

    /**
     * Loads data from the storage file into the task list.
     * This method reads tasks from the file and adds them to the task list.
     */
    public void loadData() {
        try {
            Files.createDirectories(Paths.get(dataPath));
            File data = new File(dataPath + fileName);
            if (!data.createNewFile()) {
                BufferedReader br = new BufferedReader(new FileReader(data));
                String line;
                while ((line = br.readLine()) != null) {
                    boolean isTaskDone;
                    isTaskDone = !line.substring(line.lastIndexOf(' ') + 1).equals("incomplete");
                    line = line.substring(0, line.lastIndexOf(' '));
                    Task task = taskList.taskCreator(line);
                    task.setTaskDone(isTaskDone);
                    this.taskList.add(task);
                }
            }
        } catch (IOException e) {
            ui.printError("\tUnfortunately, an error occurred. Please try again! We try our utmost best to satisfy you. UwU :3");
        }
    }

    /**
     * Saves the current tasks in the task list to the storage file.
     * This method writes each task into the file in a structured format.
     */
    public void saveData() {
        try {
            PrintWriter writer = new PrintWriter(dataPath + fileName);
            for (int i = 0; i < taskList.size(); i++) {
                writer.println(taskList.get(i).getInputItem() + taskList.get(i).isTaskDone());
            }
            writer.close();
        } catch (IOException e) {
            ui.printError("\tUnfortunately, an error occurred. Please try again! We try our utmost best to satisfy you. UwU :3");
        }
    }
}
