package teletubbi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Represents a storage file to store content from the task list.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Initializes and loads file of given file path.
     *
     * @return File content.
     * @throws TeletubbiException If unable to initialize file.
     */
    public String load() throws TeletubbiException {
        File f = new File(filePath);
        try {
            boolean isDirCreated = f.getParentFile().mkdirs();
            boolean isFileCreated = f.createNewFile();
            if (!isFileCreated) {
                Scanner fileScanner = new Scanner(f);
                String content = "";
                while (fileScanner.hasNext()) {
                    String task = fileScanner.nextLine();
                    content = content + task + "\n";
                }
                return content;
            }
        } catch (IOException e) {
            throw new TeletubbiException("Unable to initialize storage file.");
        }
        return "";
    }

    /**
     * Writes task list content to duke.txt.
     *
     * @param taskList Task content to save.
     */
    public void save(TaskList taskList) throws TeletubbiException {
        try {
            FileWriter fw = new FileWriter("./data/teletubbi.txt");
            for (int i = 0; i < taskList.size(); i++) {
                Task curr = taskList.get(i);
                if (curr instanceof ToDo) {
                    ToDo temp = (ToDo) curr;
                    fw.write(temp.getFileFormat());
                } else if (curr instanceof Deadline) {
                    Deadline temp = (Deadline) curr;
                    fw.write(temp.getFileFormat());
                } else if (curr instanceof Event) {
                    Event temp = (Event) curr;
                    fw.write(temp.getFileFormat());
                }
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        } catch (TeletubbiException a) {
            throw a;
        }
    }

}
