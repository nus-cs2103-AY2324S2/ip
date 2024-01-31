import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Storage {
    private File storageFile;

    public boolean isOccupied;

    public Storage(String FilePath) throws DukeException,IOException {
        storageFile = new File(FilePath);
        if (!storageFile.getParentFile().exists()) {
            storageFile.getParentFile().mkdirs();
        }
        if(!storageFile.exists()) {
            storageFile.createNewFile();
        }
        if(storageFile.length() == 0) {
            isOccupied = false;
        } else {
            isOccupied = true;
        }
    }

    protected ArrayList<Task> loadStorage() throws DukeException,IOException {
        Scanner s = new Scanner(storageFile);
        ArrayList<Task> loadedList = new ArrayList<>();
        while (s.hasNextLine()) {
            String storedInput = s.nextLine();
            Task currTask = Parser.parseFileLine(storedInput);
            loadedList.add(currTask);
        }
        return loadedList;
    }

    protected void saveStorage(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(storageFile, false);
        for (Task t : taskList) {
            fw.append(t.toString() + "\n");
        }
        fw.close();
    }
}
