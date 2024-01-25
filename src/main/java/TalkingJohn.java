import java.util.Objects;
import java.util.Scanner;

public class TalkingJohn {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] stringList = new String[100];

        System.out.println("Hello, I am TalkingJohn\n"
                + "What can I do for you?\n");

        int j = 0;
        while (true) {
            String input = scanner.nextLine();
            if (Objects.equals(input, "bye")) {
                System.out.println("Bye, hope to see you soon");
                break;
            } else if (Objects.equals(input, "list")){
                for (int i = 0; i < j; i++) {
                    String nthElement = stringList[i];
                    System.out.println((i + 1) + ". " + nthElement + "\n");
                }
            } else {
                stringList[j] = input;
                j++;
                System.out.println("added: " + input);
            }
        }
        scanner.close();
    }
}
