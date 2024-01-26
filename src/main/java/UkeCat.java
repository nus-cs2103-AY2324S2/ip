import java.io.IOException;

public class UkeCat {
    public static void main(String[] args) {
        try {
            FileManager.fetchTasks();
        } catch (IOException eIO) {
            System.out.println("I/O error occurred.");
        }

        try {
            // Welcome msg
            System.out.println(Storage.LINE + Storage.WELCOME + Storage.LINE);

            // Read user input
            while (true) {
                Parser.parse();
                Responder.respond(Storage.words);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            Parser.closeScanner();
        }
    }
}
