import java.util.Objects;
import java.util.Scanner;
public class Reacher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello!\n" +
                "I'm Reacher.\n" +
                "What do u want?\n" +
                "Pls say bye");
        while (true) {
            String input = scanner.nextLine();
            System.out.println(input);
            if (Objects.equals(input,"bye")) {
                break;
            }
        }
        scanner.close();
    }
}
