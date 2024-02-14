package awex;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public File load() throws IOException {
        return new File(this.filepath);
    }

    public void store(TaskList t) throws IOException {
        FileWriter fw = new FileWriter(this.filepath);
        t.listSaver(fw);
        fw.close();
    }
}
