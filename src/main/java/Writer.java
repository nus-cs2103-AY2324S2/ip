import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Writer {
    private String path;
    public Writer(String path) {
        this.path = path;
    }

    public void writeToFile(String content) {
        try {
            String[] dirName = this.path.split("/");
            File dir = new File(dirName[0]);

            if (!dir.exists()) {
                dir.mkdir();
            }

            File file = new File(this.path);
            // Check for existence of file is inherent in this
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content + "\n");

            bw.close();

        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
