import java.awt.*;
import java.util.Scanner;
import java.lang.StringBuilder; // handle string concat

public class UkeCat {
    public static void main(String[] args) {
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
            // Close resources if needed
            Parser.closeScanner();
        }
    }
}
