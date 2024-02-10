package bob;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String DATA_DIR = "data";
    private static final String DATA_PATH = DATA_DIR + "/bob.txt";

    private static File dataFile;

    private static File createOrRetrieve() throws IOException {
        Path path = Paths.get(DATA_PATH);
        Path parent = path.getParent();
        Files.createDirectories(parent);
        if (Files.notExists(path)) {
            return Files.createFile(path).toFile();
        }
        return path.toFile();
    }

    // TODO: Once extractParameter is more generalised, we can move this to Parser
    private static void parseStorageLine(String line, int lineIndex) throws InvalidTaskIndexException {
        String[] parameters = line.split(" \\| ");
        String taskType = parameters[0];
        boolean isDone = Boolean.parseBoolean(parameters[1]);
        String description = parameters[2];
        switch (taskType) {
            case Parser.TODO:
                TaskList.addTodo(description);
                break;
            case Parser.DEADLINE:
                LocalDateTime by = LocalDateTime.parse(parameters[3], Parser.INPUT_DATETIME_FORMATTER);
                TaskList.addDeadline(description, by);
                break;
            default:
                LocalDateTime from = LocalDateTime.parse(parameters[3], Parser.INPUT_DATETIME_FORMATTER);
                LocalDateTime to = LocalDateTime.parse(parameters[4], Parser.INPUT_DATETIME_FORMATTER);
                TaskList.addEvent(description, from, to);
        }
        TaskList.mark(lineIndex, isDone);
    }

    public static void load() {
        try {
            dataFile = createOrRetrieve();
            Scanner s = new Scanner(dataFile);

            for (int i = 0; s.hasNext(); i++) {
                parseStorageLine(s.nextLine(), i);
            }
        } catch (Exception e) {
            // Regardless of what went wrong, print the exception name and message, then quit.
            Ui.print(new String[] { Ui.LOADING_ERROR, e.getClass().getName() + ": " + e.getMessage() });
            System.exit(-1);
        }
    }

    public static void save(ArrayList<Task> tasks, boolean isAppend) {
        try {
            FileWriter fw = new FileWriter(dataFile.getAbsoluteFile(), isAppend);
            BufferedWriter bw = new BufferedWriter(fw);
            if (isAppend) {
                bw.write(tasks.get(tasks.size() - 1).toStorageFormat());
                bw.newLine();
            } else {
                for (Task task : tasks) {
                    bw.write(task.toStorageFormat());
                    bw.newLine();
                }
            }
            bw.flush();
        } catch (IOException e) {
            Ui.print(new String[] { Ui.SAVING_ERROR, e.getMessage() });
        }
    }
}
