import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    public static void print(String string) {
        System.out.println("____________________________________________________________");
        System.out.println(string);
        System.out.println("____________________________________________________________");
    }
    public static String getFile(String Path) {
        try {
            return Files.readString(Paths.get(Path));

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
    }
}
