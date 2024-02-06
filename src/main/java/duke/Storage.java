package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a storage file to store content from the task list.
 */
public class Storage {
    private String filePath;
    private Ui ui = new Ui();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates a data directory and duke.txt file if not found.
     */
    public String load() throws DukeException {
        File f = new File(filePath);
        try {
            f.getParentFile().mkdirs();
            boolean isDirCreated = f.createNewFile();
            boolean isFileCreated = f.createNewFile();
            if (!isFileCreated) {
                Scanner fileScanner = new Scanner(f);
                String content = "";
                while (fileScanner.hasNext()) {
                    String task = fileScanner.nextLine();
                    content = content + task.replaceAll(" ", "") + " ";
                }
                return content;
            }
        } catch (IOException e) {
            throw new DukeException("Unable to initialize storage file.");
        }
        return "";
    }

    /**
     * Writes task list content to duke.txt.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter("./data/duke.txt");
            for (int i = 0; i < tasks.size(); i++) {
                Task curr = tasks.get(i);
                if (curr instanceof ToDo) {
                    ToDo temp = (ToDo) curr;
                    fw.write(temp.getFileFormat());
                } else if (curr instanceof Deadline) {
                    Deadline temp = (Deadline) curr;
                    fw.write(temp.getFileFormat());
                } else if (curr instanceof Event){
                    Event temp = (Event) curr;
                    fw.write(temp.getFileFormat());
                }
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        } catch (DukeException a) {
            ui.showMessage(a.getMessage());
        }
    }

}
