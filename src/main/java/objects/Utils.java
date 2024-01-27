package objects;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utils {
    public static void printLine() {
        String character = Utils.getFile(FilePath.LINE_PATH);
        String line = String.valueOf(character).repeat(60);

        System.out.println(line);
    }

    public static void encaseLines(String string) {
        Utils.printLine();
        System.out.println(string);
        Utils.printLine();
    }

    public static String getFile(String path) {
        try {
            if (System.getProperty("user.dir").contains("text-ui-test")) {
                path = "." + path;
            }

            return Files.readString(Paths.get(path));

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        }
    }

    public static int parseIndex(String input) {
        String[] parts = input.split("\\s+");

        if (parts.length >= 2) {
            return Integer.parseInt(parts[1]) - 1;
        }

        return -1;
    }

    public static String getCommandType(String input) {
        return input.split(" ", 2)[0];
    }

    public static Deadlines createDeadline(String input) throws InvalidDeadlineException {
        if (!input.contains("/by")) {
            throw new InvalidDeadlineException();
        }

        String[] parts = input.split("/by", 2);
        String name = parts[0].trim();
        String by = parts.length > 1 ? parts[1].trim() : "";

        return new Deadlines(name, by);
    };

    public static Events createEvent(String input) throws InvalidEventException{
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new InvalidEventException();
        }

        String[] partsFrom = input.split("/from", 2);
        String name = partsFrom[0].trim();

        String[] partsTo = partsFrom[1].split("/to", 2);
        String from = partsTo[0].trim();
        String to = partsTo[1].trim();

        return new Events(name, from, to);
    };
}
