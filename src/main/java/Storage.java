import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Storage {

    private final String path;

    public Storage(String path) {
        this.path = path;
        File f = new File(path);
        System.out.println(path);

        if (f.exists()) {
            return;
        }

        try {
            File parentDir = f.getParentFile();
            parentDir.mkdirs();
            FileReader fr = new FileReader(f);
            fr.close();

        } catch (IOException e) {
            throw new DukeIOException("error creating file!");
        }
    }

    public String loadFileData() {
        try {
            Path p = Paths.get(path);
            return Files.readString(p);
        } catch (IOException e) {
            throw new DukeIOException("error locating file");
        }
    }

    public void saveToFile(String dataToSave) {
        try {
            Path p = Paths.get(path);
            Files.writeString(p, dataToSave);
        } catch (IOException e) {
            throw new DukeIOException("error saving to file");
        }
    }

}
