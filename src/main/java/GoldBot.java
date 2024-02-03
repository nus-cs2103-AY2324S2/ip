import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GoldBot {
    public static void main(String[] args) {
        System.out.println("test");
        Storage storage = new Storage("./data/data.txt");
        TaskList taskList;
        try {
            taskList = storage.createTaskList();
        } catch (IOException e) {
            System.out.println("Unexpected error occured.");
            return;
        } catch (FileCorruptionException e) {
            System.out.println("File corrupted.");
            return;
        }
        ChatSession session = new ChatSession(taskList);
        session.initChat();
        
        Scanner scanner = new Scanner(System.in);

        while (session.continueSession) {
            String input = scanner.nextLine();
            session.handleMessage(input);
        }
        scanner.close();
    }
}
