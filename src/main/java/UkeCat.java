import java.awt.*;
import java.util.Scanner;
import java.lang.StringBuilder; // handle string concat

public class UkeCat {
    public static void main(String[] args) {
        // Welcome msg
        System.out.println(Storage.LINE + Storage.WELCOME + Storage.LINE);

        // Read user input
        while (true) {
            Parser.parse();
            Responder.respond(Storage.input, Storage.words);
        }
    }
}
