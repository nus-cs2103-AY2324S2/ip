import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuilder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    // Data structure to store text entered by user
    private List<Task> items = new ArrayList<>();

    public void add(Task task) {
        items.add(task);
    }

    public List<Task> getItems() {
        return items;
    }

    public Task getItem(int index) {
        return items.get(index);
    }

    public void markDone(int index) {
        items.get(index).markDone();
    }

    public void unmarkDone(int index) {
        items.get(index).unmarkDone();
    }

    public void delete(int index) {
        items.remove(index);
    }

    // Return 0 for success
    // Return 1 for error
    public int setup() {
        String home = System.getProperty("user.dir");
        String folderName = "data";
        Path folderPath = Paths.get(home, folderName);

        // Check whether folder exist
        if (!Files.exists(folderPath) || !Files.isDirectory(folderPath)) {
            // Create the folder
            try {
                Files.createDirectory(folderPath);
            } catch (Exception e) {
                System.out.println("Error accessing / creating the folder: " + e.getMessage());
                return 1;
            }
        }

        String fileName = "duke.txt";
        Path filePath = Paths.get(home, folderName, fileName);

        // Check whether file exist
        if (!Files.exists(filePath)) {
            // Create the folder
            try {
                Files.createFile(filePath);
            } catch (Exception e) {
                Ui.print("Error accessing / creating the file: " + e.getMessage());
                return 1;
            }
        }

        // Read from file
        try {
            Scanner scanner = new Scanner(new File(filePath.toString()));
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                if (data == "") {
                    break;
                }

                String[] tasks = data.split("\n");
                for (String task: tasks) {
                    String[] splited = task.split("`");
                    switch (splited[0]) {
                        case "Deadline": {
                            this.add(new Deadline(splited[1], splited[2].equals("Y"), splited[3]));
                            break;
                        }
                        case "Event": {
                            this.add(new Event(splited[1], splited[2].equals("Y"), splited[3], splited[4]));
                            break;
                        }
                        default: {
                            this.add(new Todo(splited[1], splited[2].equals("Y")));
                        }
                    }
                }
            }
        } catch (Exception e) {
            Ui.print("Error accessing / creating the file: " + e.getMessage());
            return 1;
        }


        return 0;
    }

    // Return 0 for success
    // Return 1 for error
    public int saveChanges() {
        String home = System.getProperty("user.dir");
        String folderName = "data";
        String fileName = "duke.txt";
        Path filePath = Paths.get(home, folderName, fileName);

        StringBuilder textToAdd = new StringBuilder();
        for (Task t: items) {
            switch (t.getClass().getName()) {
                case "Deadline": {
                    textToAdd.append(t.getClass().getName() + "`" + t.getFields()[0] + "`" + t.getFields()[1]
                            + "`" + t.getFields()[2] + "\n");
                    break;
                }
                case "Event": {
                    textToAdd.append(t.getClass().getName() + "`" + t.getFields()[0] + "`" + t.getFields()[1]
                            + "`" + t.getFields()[2] + "`" + t.getFields()[3] + "\n");
                    break;
                }
                default: {
                    textToAdd.append(t.getClass().getName() + "`" + t.getFields()[0] + "`" + t.getFields()[1] + "\n");
                }
            }
        }

        try {
            FileWriter fw = new FileWriter(filePath.toString());
            fw.write(textToAdd.toString());
            fw.close();
        } catch (IOException e) {
            Ui.print("Error saving changes: " + e.getMessage());
            return 1;
        }
        return 0;
    }
}
