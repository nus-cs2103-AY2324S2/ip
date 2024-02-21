package venus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is a Storage class that controls data to be stored or loaded from .txt data file.
 *
 * @author peterXGD
 * @since 2024-02-05
 */
public class Storage {
    private String filePath;

    /**
     * Returns Storage that help store or load content from a filepath relative to the project
     * structure.
     *
     * @param filePath Relative filepath of data in project.
     */
    public Storage(String filePath) {
        File folder = new File(filePath.split(File.separator)[0]);
        if (!folder.exists()) { // handling folder does not exist issues
            folder.mkdir();
            System.out.println("Folder does not exist, data folder is created");
        }
        if (!Files.exists(Path.of("data" + File.separator + "venus.txt"))) { // handling file does not exist
            File f = new File("data" + File.separator + "venus.txt");
        }
        this.filePath = filePath;
    }

    /**
     * Store a single line of task into file.
     *
     * @param textToAdd The toString() string of a task that is stored.
     * @throws FileNotFoundException Throws exception when file cannot be found.
     */
    public void saveToFile(String textToAdd) throws FileNotFoundException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            String amendedText = textToAdd.replaceAll("\\[\\s\\]", "|0|")
                    .replaceAll("\\[X\\]", "|1|")
                    .replaceAll("\\[", "")
                    .replaceAll("\\]", "")
                    .replaceAll("\\(by:", "|")
                    .replaceAll("\\)", "")
                    .replaceAll("\\(from:", "|")
                    .replaceAll("\\(to:", "|")
                    .trim();
            fw.write(System.lineSeparator() + amendedText);
            fw.close();
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    /**
     * Adds modified Tasks into the data location.
     *
     * @param data The TaskList. data to be accessed.
     * @throws FileNotFoundException Throws exception when file cannot be found.
     */
    public void saveAllFile(ArrayList<Task> data) throws FileNotFoundException {
        try {
            File f = new File(filePath);
            f.delete();
            f.createNewFile();
            FileWriter fw = new FileWriter(filePath, true);
            for (Task d : data) {
                String amendedText = d.toString().replaceAll("\\[\\s\\]", "|0|")
                        .replaceAll("\\[X\\]", "|1|")
                        .replaceAll("\\[", "")
                        .replaceAll("\\]", "")
                        .replaceAll("\\(by:", "|")
                        .replaceAll("\\)", "")
                        .replaceAll("\\(from:", "|")
                        .replaceAll("\\(to:", "|")
                        .trim();
                fw.write(System.lineSeparator() + amendedText);
            }
            fw.close();
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    /**
     * Loads file content into ArrayList ls.
     * @param ls A place where data from txt file is loaded to.
     * @throws FileNotFoundException Throws exception if file to be loaded cannot be found.
     */
    public void loadFile(ArrayList<Task> ls) throws FileNotFoundException {

        try {
            File f = new File(filePath); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String[] items = s.nextLine().split("\\|");
                String type = items[0].trim();
                if (items.length == 1) { // this is to handle the blank spaces
                    continue;
                }
                if (type.equals("T")) {
                    boolean b = Integer.parseInt(items[1].trim()) == 1;
                    ls.add(new Todo(items[2].trim(), b));
                } else if (type.equals("D")) {
                    boolean b = Integer.parseInt(items[1].trim()) == 1;
                    ls.add(new Deadline(items[2].trim(), b, items[3].trim()));
                } else if (type.equals("E")) {
                    boolean b = Integer.parseInt(items[1].trim()) == 1;
                    String[] times = items[3].split("to:");
                    ls.add(new Event(items[2].trim(), b, times[0].trim(), times[1].trim(), true));
                } else { //continue if file is corrupted or at the first line
                    continue;
                }
            }
        } catch (FileNotFoundException e) {
            OldUi.formatResponse("File is not found! " + e.getMessage());
        } catch (DateTimeParseException e) {
            OldUi.formatResponse("Invalid date time input or format, please try again");
        }
    }

}
