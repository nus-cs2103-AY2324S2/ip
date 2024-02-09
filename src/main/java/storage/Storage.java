package storage;

import parser.Parser;
import task.Task;
import tasklist.TaskList;
import ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/** Class represents storage to load and save tasks*/
public class Storage {
    /** TaskList shared with SirDuke */
    private TaskList tasks;
    /** Ui shared with Sir Duke*/
    private Ui ui;
    /** where data would be laoded from and saved to*/
    private File file;
    /** relative path to file from root dir*/
    private String filePath;
    /** Scanner object containing the file */
    private Scanner data;

    public Storage(String filePath, TaskList tasks, Ui ui) throws IOException {
        this.tasks = tasks;
        this.ui = ui;
        this.filePath = filePath;
        file = new File("data/sirDuke.txt");
        createNewFileIfNeeded();
        load(data);
    }

    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    /**
     * Set tasks for Storage to call
     * @param tasks
     */
    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    public void createNewFileIfNeeded() throws IOException {
        try {
            data = new Scanner(file);
        } catch (FileNotFoundException e) {
            File dir = new File("data");
            if (dir.exists()) {
                file.createNewFile();
            } else {
                dir.mkdir();
                file.createNewFile();
            }
            data = new Scanner(file);
        }
    }

    public void load(Scanner data) {
        while (data.hasNext()) {
            String line = data.nextLine();
            Task task = Parser.parseDataFormat(line);
            tasks.load(task);
        }
        ui.loadingDone();
    }

    public void save() throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(tasks.toDataFormat());
        fw.close();
    }
}
