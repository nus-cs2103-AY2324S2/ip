import java.util.Objects;
import java.util.Scanner;

public class TalkingJohn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello, I am TalkingJohn\n"
                + "What can I do for you?\n");


        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) {
                System.out.println("Bye, hope to see you soon");
                break;
            } else {
                System.out.println(input);
            }
        }
        scanner.close();
    }
}
