import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util {
    public static String getFile(String Path) {
        try {
            return Files.readString(Paths.get(Path));

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
    }
}
