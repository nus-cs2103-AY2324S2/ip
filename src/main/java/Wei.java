import java.util.Objects;
import java.util.Scanner;

public class Wei {
    public static void main(String[] args) {
        String greet = "Hello! I'm Wei.\n" + "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!\n";
        String split = "______________________________";
        System.out.println(greet + split);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();

            if(Objects.equals(input, "bye")) {
                break;
            }

            //echo
            System.out.println(input + "\n" + split);
        }

        System.out.println(exit + split);
    }
}
