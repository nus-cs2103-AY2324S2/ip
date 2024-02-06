import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;
    protected File storageFile;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.storageFile = new File(filePath);
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            Scanner sc = new Scanner(this.storageFile);
            int index = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.isBlank()) {
                    continue;
                }
                Parser.parseFile(line, list, index);
                index++;
            }
        } catch (FileNotFoundException e) {
            File parentDir = storageFile.getParentFile();
            if (!parentDir.exists()) {
                if (parentDir.mkdir()) {
                    System.out.println("I've created a new directory for your tasks, my dear.\n");
                } else {
                    throw new DukeException("I'm afraid I've encountered an error while creating a directory for your tasks, my dear.\n");
                }
            }
            try {
                if (storageFile.createNewFile()) {
                    System.out.println("I've created a new file for your tasks, my dear.\n");
                } else {
                    throw new DukeException("I'm afraid the file I tried creating already exists, my dear.\n");
                }
            } catch (IOException newException) {
                throw new DukeException("I'm afraid I've encountered an error while creating a file for your tasks, my dear.\n");
            }
        }
        return list;
    }

    public void saveAdd(Task task) throws DukeException {
        try {
            FileWriter writer = new FileWriter(this.filePath, true);
            writer.write(task.toFile() + "\n");
            writer.close();
        } catch (IOException e) {
            throw new DukeException("I'm afraid I've encountered an error while saving your tasks, my dear.\n");
        }
    }

    public void saveRewrite(TaskList tasks) throws DukeException {
        try {
            FileWriter writer = new FileWriter(this.filePath);
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).toFile() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("I'm afraid I've encountered an error while saving your tasks, my dear.\n");
        }
    }
}