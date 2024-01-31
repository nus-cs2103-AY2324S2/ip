package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String FILE_PATH;

    public Storage(String file) {
        this.FILE_PATH = file;
    }

    public ArrayList<Task> loadTasksFromFile() throws DukeException {
        String FILE_DIR = "./data";
        File directory = new File(FILE_DIR);
        File file = new File(FILE_PATH);
        try {
            if (!directory.isDirectory()) {
                directory.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            Scanner fileSc = new Scanner(file);
            while (fileSc.hasNext()) {
                String taskCategory = fileSc.nextLine();
                Task task = Parser.parseCategoryFromFile(taskCategory);
                taskList.add(task);
            }
            fileSc.close();
            return taskList;
        } catch (IOException e) {
            throw new DukeException("Error, unable to load tasks from file");
        }
    }

    public void saveTasksToFile(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        StringBuilder msg = new StringBuilder();
        for (Task task : taskList) {
            msg.append(task.toFile()).append("\n");
        }
        fw.write(msg.toString());
        fw.close();
    }
}
