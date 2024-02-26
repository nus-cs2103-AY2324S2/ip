package awex;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filepath;

    /**
     * Constructor for Storage object
     *
     * @param filepath path of file TaskList is saved to
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Retrieves saved TaskList information from given filepath
     *
     * @return File object from filepath
     * @throws IOException
     */

    public File load() throws IOException {
        return new File(this.filepath);
    }

    /**
     * Saves updated TaskList into file from given filepath
     *
     * @param t list of tasks
     * @throws IOException
     */
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
