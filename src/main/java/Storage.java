import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Storage {

    protected Path filePath;

    public Storage(Path newFilePath) {
        this.filePath = newFilePath;
    }

    private static File getFile(Path path) throws DukeException {
        File file = path.toFile();
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new DukeException("Error creating file");
            }
        }
        return file;
    }

    public void updateFile(String[] tasks) throws DukeException {
        File file;

        file = getFile(filePath);

        try {
            List<String> lines = Files.readAllLines(filePath);
            lines.clear();
            lines.addAll(Arrays.asList(tasks));
            Files.write(filePath, lines);
        } catch (IOException e) {
            throw new DukeException("Error updating file");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        File file;

        file = getFile(filePath);

        ArrayList<Task> tasks = new ArrayList<Task>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                switch (parts[0]) {
                    case "T" -> tasks.add(new Todo(parts[2]));
                    case "D" -> tasks.add(new Deadline(parts[2], parts[3]));
                    case "E" -> tasks.add(new Event(parts[2], parts[3], parts[4]));
                }
                if (parts[1].equals("X")) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
        return tasks;
    }
}
