package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private File file;
    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
        this.file = new File(filepath);
    }
    public File load() throws DukeException {
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                writeToFile("");
                throw new DukeException();
            } catch (IOException e) {
                throw new DukeException("Something went wrong: " + e);
            }
        } else {
            return file;
        }
    }


    public void writeToFile(String text) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.write(text);
        fw.close();
    }

}
