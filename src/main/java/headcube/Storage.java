package headcube;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Storage {
    private Ui ui;
    public Storage(Ui ui) {
        this.ui = ui;
    }

    public void save(TaskList taskList) {
        try {
            String directoryPath = "./data";
            String filePath = directoryPath + "/HeadCube.txt";
            Files.createDirectories(Paths.get(directoryPath));
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            for (Task task : taskList.getList()) {
                fw.write(task.toFileFormat() + System.lineSeparator());
            }
            fw.close();
            ui.saveMessage();
        } catch (IOException e) {
            ui.error("An error occurred while saving tasks" + e.getMessage());
        }
    }

    public void load(TaskList tasklist) {
        File file = new File("./data/HeadCube.txt");
        if (!file.exists()) {
            return;
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                Task task = parse(line);
                if (task != null) {
                    tasklist.add(task);

                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            ui.error("No tasks to load" + e.getMessage());
        }
    }
    private static Task parse(String input) {
        String[] parts = input.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }

        String event = parts[0];
        boolean isDone = parts[1].trim().equals("1");
        String description = parts[2].trim();

        Task task = null;

        if ("T".equals(event)) {
            task = new ToDos(description);
        } else if ("D".equals(event)) {
            if (parts.length > 3) {
                String by = parts[3].replace("(by: ", "").replace(")", "");
                task = new Deadlines(description,by);
            }
        } else {
            if (parts.length > 3) {
                String timeInfo = parts[3].replace("(from: ", "").replace(")", "");
                String[] times = timeInfo.split(" to: ");
                String start = times[0];
                String end = times.length > 1 ? times[1] : "";
                task = new Events(description, start, end);
            }
        }

        if (isDone) {
            task.done();
        }
        return task;
    }
}
