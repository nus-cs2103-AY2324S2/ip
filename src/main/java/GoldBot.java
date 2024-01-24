import java.util.Scanner;

public class GoldBot {
    public static void main(String[] args) {
        ChatSession session = new ChatSession();
        Scanner scanner = new Scanner(System.in);

        while (session.continueSession) {
            String input = scanner.nextLine();
            session.handleMessage(input);
        }
        scanner.close();
    }
}
