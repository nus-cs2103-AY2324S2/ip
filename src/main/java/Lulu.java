import java.util.Scanner;

public class Lulu {
    public Lulu() {

    }

    public void start() {
        System.out.println("Hello! I'm Lulu \nWhat can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void respond() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("bye")) {
                break;
            }
            System.out.println(input);
        }
    }

    public static void main(String[] args) {
        Lulu chatbot = new Lulu();
        chatbot.start();
        chatbot.respond();
        chatbot.exit();
    }
}
