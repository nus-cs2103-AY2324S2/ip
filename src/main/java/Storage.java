import entity.Task;
import entity.ToDo;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private static String PATH = "./data/duke.txt";

    public Storage() {
        try {
            Path filePath = Paths.get(PATH);

            Files.createDirectories(filePath.getParent());

            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void saveTasks(List<Task> list) {
        try {
            FileWriter fw = new FileWriter(PATH);
            for (Task t : list) {
                fw.write(t.save() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
