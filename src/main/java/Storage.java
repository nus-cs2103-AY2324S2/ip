import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String FILE_PATH;
    private final ArrayList<Task> tasks = new ArrayList<>();

    public Storage(String filePath) {
        FILE_PATH = filePath;
    }

    public ArrayList<Task> load() throws EarlException {
        try {
            File file = new File(FILE_PATH);
            boolean isFolderMade = file.getParentFile().mkdirs();
            boolean isFileMade = file.createNewFile();
            if (isFolderMade || isFileMade) {
                throw new EarlException("Storage file missing... "
                        + "creating new file.");
            }

            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String entry = sc.nextLine();
                tasks.add(Parser.parseStorageEntry(entry));
            }
        } catch (EarlException e) {
            throw e;
        } catch (Exception e) {
            throw new EarlException("Unknown exception occurred "
                    + "when attempting to create or access "
                    + "storage file: " + e.getMessage());
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) throws EarlException {
        try (FileWriter fw = new FileWriter(FILE_PATH)) {
            for (Task task : tasks) {
                fw.write(task.toStorageString() + "\n");
            }
        } catch (IOException e) {
            throw new EarlException("Fatal error while saving to storage.");
        }
    }
}