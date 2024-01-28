import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {
    private String content;
    private String path;
    public Writer(String content, String path) {
        this.content = content;
        this.path = path;
    }

    public void writeToFile() {
        try {
            File file = new File(path);
            // Check for existence of file is inherent in this
            file.createNewFile();

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(this.content);

            bw.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
