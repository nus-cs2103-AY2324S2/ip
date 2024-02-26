import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.nio.file.Files.*;

public class Storage {
    private String filePath="";

    public Storage(){
    }

    public Storage(String filepath){
        this.filePath = filepath;
    }

    public List<String> load(){
        try {
            List<String> lines = Files.readAllLines(Path.of(filePath));
            return lines;
        }catch (IOException io){
            throw new RuntimeException("Error while loading data file");
        }
    }

    public void Store(String content) throws DukeException {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write(content);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new DukeException(e.getMessage());
        }
    }
}
