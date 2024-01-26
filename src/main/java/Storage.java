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
    private BufferedReader reader;
    private BufferedWriter writer;

    public Storage(String path) throws IOException {
        this.path = path;
        this.file = new File(path);
        file.getParentFile().mkdirs(); // create parent directories if not exists
        file.createNewFile(); // create file if not exists
        this.reader = new BufferedReader(new FileReader(path));
        this.writer = new BufferedWriter(new FileWriter(path));
    }

    public BufferedReader getReader() {
        return reader;
    }

    public BufferedWriter getWriter() {
        return writer;
    }

    public void empty() throws IOException {
        this.writer.close();
        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write("");
        }
        this.writer = new BufferedWriter(new FileWriter(this.path));
    }

    public void writeLine(String line) throws IOException {
        writer.write(line);
        writer.newLine();
    }

    @Override
    public void close() throws IOException {
        reader.close();
        writer.close();
    }
}
