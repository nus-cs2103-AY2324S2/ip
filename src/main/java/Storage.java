import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.lang.AutoCloseable;

public class Storage implements AutoCloseable {
    private final String path;
    private final File file;
    private BufferedWriter writer;

    public Storage(String path) throws IOException {
        this.path = path;
        this.file = new File(path);
        this.file.getParentFile().mkdirs(); // create parent directories if not exists
        this.file.createNewFile(); // create file if not exists
        this.writer = new BufferedWriter(new FileWriter(this.file, true));
    }

    public void empty() throws IOException {
        close();
        try (FileWriter fileWriter = new FileWriter(this.file, false)) {
            fileWriter.write("");
        }
        this.writer = new BufferedWriter(new FileWriter(this.file));
    }

    public void writeLine(String line) throws IOException {
        writer.write(line);
        writer.newLine();
    }

    public String readAll() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.file));
        StringBuilder builder = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            builder.append(line);
            builder.append("\n");
            line = reader.readLine();
        }
        reader.close();
        return builder.toString();
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
