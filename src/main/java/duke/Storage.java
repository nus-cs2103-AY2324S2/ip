package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        ArrayList<Task> loadedTasks = new ArrayList<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            Task task = Parser.decodeTask(line);
            if (task != null) {
                loadedTasks.add(task);
            }
        }
        scanner.close();
        return loadedTasks;
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        new File("." + File.separator + "data").mkdirs();
        PrintWriter writer = new PrintWriter(new File(filePath));
        for (Task task : tasks) {
            writer.println(task.toFileFormat());
        }
        writer.close();
    }
}
