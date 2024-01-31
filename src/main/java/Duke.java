import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        BotManager manager = new BotManager("Jerry");
        Scanner sc = new Scanner(System.in);
        manager.greeting();
        while (true) {
            String prompt = sc.nextLine();
            if (prompt.equals("bye")) {
                break;
            } else {
                manager.answer(prompt);
            }
        }
        manager.exit();
        sc.close();
    }
}
