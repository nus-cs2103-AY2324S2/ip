package Dook;

import Task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Storage {

    private final ArrayList<String> path;

    Storage(ArrayList<String> path) {
        this.path = path;
    }

    public void write(TaskList tasks) throws DookException {
        try {
            File f = new File(String.join("/", path));
            if (!f.exists()){
                f.createNewFile();
            }
            FileWriter writer = new FileWriter(String.join("/", path));
            writer.write(tasks.fileRepresentation());
            writer.close();
        } catch (IOException e) {
            throw new DookException("An error occured when writing to your files...:(" + e);
        }
    }

    public TaskList loadTaskListFromFile() throws IOException, DookException {
        TaskList taskList = new TaskList();
        BufferedReader r;
        r = new BufferedReader(new FileReader(String.join("/", path)));
        String line = r.readLine();
        while (line != null) {
            taskList.addTask(Parser.parseFileLineToTask(line));
            line = r.readLine();
        }
        r.close();
        return taskList;
    }

    public void checkFile() {
        try {
            String filename = String.join(File.separator, path.subList(0, path.size() - 1));
            String workingDirectory = System.getProperty("user.dir");
            String absoluteFilePath = workingDirectory + File.separator + filename;
            File file = new File(absoluteFilePath);
            System.out.println(absoluteFilePath);
            if (file.mkdir()) {
                System.out.println("Meow :3 directory for your data is created!");
            } else {
                System.out.println("Meow :3 existing directory for data found!");
            }
            filename = String.join(File.separator, path);
            workingDirectory = System.getProperty("user.dir");
            absoluteFilePath = workingDirectory + File.separator + filename;
            file = new File(absoluteFilePath);
            if (file.createNewFile()) {
                System.out.println("save file is initialised!");
            } else {
                System.out.println("save file detected!!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
