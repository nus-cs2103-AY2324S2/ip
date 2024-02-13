import java.io.IOException;

public class LiteException {
    public static void InvalidInput() {
        System.out.println("Invalid input .\n" +
                "Please begin your input with either one of these keywords: \n" +
                "todo, list, deadline, event, mark, unmark, bye");
    }

    public static void LoadFileException() {
        System.out.println("Unable to load data from local file");
        System.out.println("File may be corrupted");
    }

    public static void SaveException(IOException e) {
        System.out.println("Failed to save to a local file");
        System.out.println(e);
    }
}
