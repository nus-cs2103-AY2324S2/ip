import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    private static final String LINE_PATH = "./src/main/Line.txt";

    public static void printLine() {
        String character = Utils.getFile(LINE_PATH);
        String line = String.valueOf(character).repeat(60);

        System.out.println(line);
    }
    public static void encaseLines(String string) {
        Utils.printLine();
        System.out.println(string);
        Utils.printLine();
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
