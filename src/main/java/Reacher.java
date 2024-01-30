import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Reacher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> memory = new ArrayList<>();
        System.out.println("Hello!\n" +
                "I'm Reacher.\n" +
                "What do u want?\n" +
                "Pls say bye");
        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input,"bye")) {
                System.out.println("bye");
                break;
            }
            if (Objects.equals(input, "list")) {
                for(String task:memory) {
                    System.out.println(task);
                }
            } else {
                memory.add(input);
                System.out.println("I've added " + input);
            }
        }
        scanner.close();
    }
}
