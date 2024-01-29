package objects;

import view.SingleLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static objects.FilePath.LINE_PATH;

public class Utils {
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
        // throw error if empty
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
