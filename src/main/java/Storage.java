import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;

public class Storage {

    private static String DATAFILEPATH = "./data";
    private static String STORAGEFILEPATH = "./data/duke.txt";

    private File directory;
    private File storageFile;

    public Storage() throws DukeException,IOException {
        directory = new File(DATAFILEPATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
        storageFile = new File(STORAGEFILEPATH);
        if(!storageFile.exists()) {
            storageFile.createNewFile();
        }
        this.loadStorage();
    }

    private void loadStorage() throws DukeException,IOException {
        Scanner s = new Scanner(storageFile);
        while (s.hasNextLine()) {
            String storedInput = s.nextLine();
            Duke.commandParser(storedInput, true);
        }
    }

    protected void saveStorage(String command) throws IOException {
        FileWriter fw = new FileWriter(STORAGEFILEPATH, true);
        fw.append("\n" + command);
        fw.close();
    }
}
