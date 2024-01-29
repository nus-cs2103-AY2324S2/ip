import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }
    public void writeToFile(TaskList tasks) throws IOException {
        File directory = new File("data");
        directory.mkdir();
        File f = new File("data" + File.separator + "ezra.txt");
        f.createNewFile();
        FileWriter fw = new FileWriter(f);
        StringBuilder builder = new StringBuilder();
        for (Task t : tasks.arrayList) {
            builder.append(t.toString2()).append("\n");
        }
        fw.write(builder.toString());
        fw.close();
    }

    public File load() {
        return new File(filepath);
    }
}
