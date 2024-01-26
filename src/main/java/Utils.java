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

    public static int getIndex(String input) {
        return Integer.parseInt(input.split("\\s+")[1]) - 1;
    }

    public static Deadlines createDeadline(String input){
        String[] parts = input.split("/by", 2);
        String name = parts[0].trim();
        String by = parts.length > 1 ? parts[1].trim() : "";

        return new Deadlines(name, by);
    };

    public static Events createEvent(String input){
        String[] partsFrom = input.split("/from", 2);
        String name = partsFrom[0].trim();

        String[] partsTo = partsFrom[1].split("/to", 2);
        String from = partsTo[0].trim();
        String to = partsTo[1].trim();

        return new Events(name, from, to);
    };
}
