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
        int len = t.size();
        assert len >= 0;
        String str = "";
        for (int i = 0; i < len; i++) {
            str += t.get(i).toString() + "\n";
        }
        fw.write(str);
        fw.close();
    }
}
