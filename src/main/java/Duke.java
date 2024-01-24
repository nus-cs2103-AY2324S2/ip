import java.util.Objects;
import java.util.Scanner;

public class Duke {

    final static String HORIZONTAL_LINE = "____________________________________________________________ \n";
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello from Kewgy! \n");
        System.out.println("What can I do for you? \n");
        System.out.println("Type \"bye\" to exit! \n");
        System.out.println(HORIZONTAL_LINE);

        String userMsg = reader.next();
        while(!Objects.equals(userMsg, "bye")) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println(userMsg);
            System.out.println(HORIZONTAL_LINE);
            userMsg = reader.next();
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye! Hope to see you again soon! \n");
        System.out.println(HORIZONTAL_LINE);
    }
}
