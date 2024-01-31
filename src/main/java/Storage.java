import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Storage {

    private static String DATAFILEPATH = "./data";
    private static String STORAGEFILEPATH = "./data/duke.txt";

    private File directory;
    private File storageFile;

    public boolean isOccupied;

    public Storage() throws DukeException,IOException {
        directory = new File(DATAFILEPATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
        storageFile = new File(STORAGEFILEPATH);
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
            Task currTask = Duke.parseFileLine(storedInput);
            loadedList.add(currTask);
        }
        return loadedList;
    }

    protected void saveStorage(ArrayList<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(STORAGEFILEPATH, false);
        for (Task t : taskList) {
            fw.append(t.toString() + "\n");
        }
        fw.close();
    }
}
