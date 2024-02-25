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
        File folder = new File(filePath);
        if (!folder.exists()) { // handling folder does not exist issues
            folder.mkdirs(); // Use mkdirs() to create parent directories if necessary
            System.out.println("Folder does not exist, data folder is created.\n"
                    + "Do not worry if system say there is error, it will fix itself.:)");
        }
        if (!Files.exists(Path.of("data", "venus.txt"))) { // handling file does not exist
            // Create the file if it doesn't exist
            File f = new File("data", "venus.txt");
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
            String amendedText = changeToSaveString(textToAdd);
            fw.write(System.lineSeparator() + amendedText);
            fw.close();
        } catch (IOException e) {
            throw new FileNotFoundException();
        }
    }

    private static String changeToSaveString(String textToAdd) {
        String amendedText = textToAdd.replaceAll("\\[\\s\\]", "|0|")
                .replaceAll("\\[X\\]", "|1|")
                .replaceAll("\\[", "")
                .replaceAll("\\]", "")
                .replaceAll("\\(by:", "|")
                .replaceAll("\\)", "")
                .replaceAll("\\(from:", "|")
                .replaceAll("\\(to:", "|")
                .trim();
        return amendedText;
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
                String amendedText = changeToSaveString(d.toString());
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
    public void loadFile(ArrayList<Task> ls) {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] items = s.nextLine().split("\\|");
                String type = items[0].trim();
                if (items.length == 1) { // this is to handle the blank spaces
                    continue;
                }
                if (type.equals("T")) {
                    handleTodo(ls, items);
                } else if (type.equals("D")) {
                    handleDelete(ls, items);
                } else if (type.equals("E")) {
                    handleEvent(ls, items);
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

    private static void handleEvent(ArrayList<Task> ls, String[] items) {
        boolean b = Integer.parseInt(items[1].trim()) == 1;
        String[] times = items[3].split("to:");
        ls.add(new Event(items[2].trim(), b, times[0].trim(), times[1].trim(), true));
    }

    private static void handleDelete(ArrayList<Task> ls, String[] items) {
        boolean b = Integer.parseInt(items[1].trim()) == 1;
        ls.add(new Deadline(items[2].trim(), b, items[3].trim()));
    }

    private static void handleTodo(ArrayList<Task> ls, String[] items) {
        boolean b = Integer.parseInt(items[1].trim()) == 1;
        ls.add(new Todo(items[2].trim(), b));
    }

}
