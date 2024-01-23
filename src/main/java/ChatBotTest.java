import java.util.Scanner;

public class ChatBotTest {
    public static void main(String[] args) {
        Ping p = new Ping();
        String name = p.name;

        // The Welcome Part
        System.out.println("Hello! I'm "+name+
                "\nWhat can I do for you?");

        // The Scanner Part
        Scanner sc = new Scanner(System.in);
        while (true) {
            String commands = sc.nextLine();
            if (commands.equals("bye")) {
                p.goodBye();
                break;
            } else if (commands.equals("list")) {
                p.listTasks();
            } else {
                p.addTask(commands);
            }
        }
    }
}
