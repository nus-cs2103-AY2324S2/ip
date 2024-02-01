import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GoldBot {
    public static void main(String[] args) {
        File data;
        try {
            data = FileOperations.loadFile();
        } catch (IOException e) {
            System.out.println("Unexpected error occured.");
            return;
        }
        ChatSession session;
        try {
            session = FileOperations.createChatSession(data);
        } catch (FileCorruptionException e) {
            System.out.println("File corrupted.");
            return;
        }
        
        Scanner scanner = new Scanner(System.in);

        while (session.continueSession) {
            String input = scanner.nextLine();
            session.handleMessage(input);
        }
        scanner.close();
    }
}
