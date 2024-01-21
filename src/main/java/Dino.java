import java.util.Scanner;
public class Dino {
    public static void welcome() {
        System.out.println("Hola! I'm Dino.\nWhat are you doing here?");
    }

    public static void goodbye() {
        System.out.println("Goodbye! It was nice meeting you.");
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String chat = sc.nextLine();
        while (!chat.equalsIgnoreCase("bye")) {
            System.out.println(chat);
            chat = sc.nextLine();
        }
        Dino.goodbye();
    }

    public static void main(String[] args) {
        Dino.welcome();
        Dino.echo();
    }
}
