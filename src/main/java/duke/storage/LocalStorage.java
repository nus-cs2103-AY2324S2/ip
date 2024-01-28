package duke.storage;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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
    private final boolean createdNewFile;
    private PrintWriter printWriter;
    private final Scanner scan;
    private final File saveFile;

    /**
     * Constructs a new `LocalStorage` object with the specified file location.
     *
     * @param fileLocation The file location where tasks are stored.
     * @throws IOException If an I/O error occurs while creating or accessing the file.
     */
    public LocalStorage(String fileLocation) throws IOException {
        this.saveFile = new File(fileLocation);
        this.createdNewFile = saveFile.createNewFile();
        this.printWriter = new PrintWriter(new FileOutputStream(saveFile, true));
        this.scan = new Scanner(saveFile);
    }

    public boolean createdNewFile() {
        return createdNewFile;
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
        clearAll();
        printWriter = new PrintWriter(new FileOutputStream(saveFile, true));
        List<String> encodedTasksList = encodedTasks.collect(Collectors.toList());
        for (String string : encodedTasksList) {
            printWriter.println(string);
        }
        printWriter.close();
    }

    /**
     * Clears all data in the local storage file.
     *
     * @throws IOException If an I/O error occurs while clearing the file.
     */
    public void clearAll() throws IOException {
        printWriter = new PrintWriter(new FileOutputStream(saveFile, false));
        printWriter.close();
    }

}
