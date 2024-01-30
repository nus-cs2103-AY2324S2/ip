package duke;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
public class Storage {
    private String FILE_PATH;
    public Storage(String file) {
        this.FILE_PATH = file;
    }

    public void saveTasks(ArrayList<Task> store) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Create parent directories if they don't exist
                file.createNewFile(); // Create the file if it doesn't exist
            }

            FileWriter fileWriter = new FileWriter(FILE_PATH);
            for (Task task : store) {
                fileWriter.write(task.toFileString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            //System.out.println("b");
            Scanner fileScanner = new Scanner(file);
            ArrayList<Task> tasks = new ArrayList<>();

            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                //System.out.println(line);
                Task task = Parser.parseTaskFromLine(line);
                tasks.add(task);
                //System.out.println("line");
            }

            fileScanner.close();
            return tasks;
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file: " + e.getMessage());
        }
    }
}
