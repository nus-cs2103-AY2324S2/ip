package bob;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
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

    public static void load() {
        try {
            dataFile = createOrRetrieve();
            Scanner s = new Scanner(dataFile);

            for (int i = 0; s.hasNext(); i++) {
                String formattedTask = s.nextLine();
                String[] parameters = formattedTask.split(" \\| ");

                TaskList.add(parameters[0], Arrays.copyOfRange(parameters, 2, parameters.length));
                TaskList.mark(i, parameters[1].equals("1"));
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
                bw.write(tasks.get(tasks.size() - 1).format());
                bw.newLine();
            } else {
                for (Task task : tasks) {
                    bw.write(task.format());
                    bw.newLine();
                }
            }
            bw.flush();
        } catch (IOException e) {
            Ui.print(new String[] { Ui.SAVING_ERROR, e.getMessage() });
        }
    }
}
