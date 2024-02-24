package hal.storage;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * The `LocalStorage` class manages the local storage and retrieval of tasks in the Duke application.
 * It provides methods for loading, saving, and clearing tasks from a local file.
 */
public class LocalStorage {
    private final boolean hasCreatedNewFile;
    private PrintWriter printWriter;
    private final Scanner scan;
    private final File saveFile;

    /**
     * Constructs a new `LocalStorage` object with the specified file location.
     *
     * @param fileDirectory The file directory where save file is stored
     * @param fileName The name of the file where the tasks are stored
     * @throws IOException If an I/O error occurs while creating or accessing the file.
     */
    public LocalStorage(String fileDirectory, String fileName) throws IOException {
        String filePath = fileDirectory + fileName;
        new File(fileDirectory).mkdir();
        saveFile = new File(filePath);
        hasCreatedNewFile = saveFile.createNewFile();
        printWriter = new PrintWriter(new FileOutputStream(saveFile, true));
        scan = new Scanner(saveFile);
    }

    public boolean hasCreatedNewFile() {
        return hasCreatedNewFile;
    }

    /**
     * Loads encoded tasks from the local storage file.
     *
     * @return A list of encoded task strings loaded from the file.
     */
    public ArrayList<String> load() {
        ArrayList<String> list = new ArrayList<>();
        while (scan.hasNextLine()) {
            String encodedTask = scan.nextLine();
            list.add(encodedTask);
        }
        return list;
    }

    /**
     * Saves encoded tasks to the local storage file, overwriting existing data.
     *
     * @param encodedTasks A stream of encoded task strings to be saved.
     * @throws IOException If an I/O error occurs while saving the data.
     */
    public void save(Stream<String> encodedTasks) throws IOException {
        printWriter = new PrintWriter(Files.newOutputStream(saveFile.toPath()));
        List<String> encodedTasksList = encodedTasks.collect(Collectors.toList());
        for (String string : encodedTasksList) {
            printWriter.println(string);
        }
        printWriter.close();
    }

    /**
     * Closes Scanner and PrintWriter and releases any system resources associated with it
     */
    public void close() {
        scan.close();
        printWriter.close();
    }
}
