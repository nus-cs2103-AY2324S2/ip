package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;
import java.util.ArrayList;


public class Storage {
    private String directoryPath;
    private String filePath;

    public Storage (String directoryPath, String filePath) {
        this.directoryPath = directoryPath;
        this.filePath = filePath;
    }

    public ArrayList<String> readTaskListData() throws IOException {
        File read = new File(this.filePath);
        Path directory = Paths.get(this.directoryPath);
        if (!Files.exists(directory)) {
            Files.createDirectories(directory);
        }
        if (!read.exists()) {
            read.createNewFile();
        }
        Scanner sc = new Scanner(read);
        ArrayList<String> tasks = new ArrayList<>();
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            tasks.add(data);
        }
        return tasks;
    }

    public void writeTaskListData(TaskList taskList) throws IOException {
        File save = new File(this.filePath);
        try {
            if (!save.exists()) {
                save.createNewFile();
            }
            FileWriter fw = new FileWriter(this.filePath);
            for (int i = 0; i < taskList.getTaskCount(); i++) {
                fw.write(taskList.getTask(i).toTaskSaveString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
            e.getStackTrace();
        }
    }
}
