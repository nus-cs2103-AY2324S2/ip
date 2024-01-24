import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    final static String HORIZONTAL_LINE = "____________________________________________________________ \n";
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        List<String> userMsgList = new ArrayList<String>();

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello from Kewgy! \n");
        System.out.println("What can I do for you? \n");
        System.out.println("Type \"bye\" to exit! \n");
        System.out.println(HORIZONTAL_LINE);

        String userMsg = reader.next();
        while(true) {
            if (Objects.equals(userMsg, "bye")) break;

            if (Objects.equals(userMsg, "list")) {
                System.out.println(HORIZONTAL_LINE);
                for (int i = 0; i < userMsgList.size(); i++) {
                    System.out.println(i + ": " + userMsgList.get(i));
                }
                System.out.println(HORIZONTAL_LINE);
            } else {
                System.out.println(HORIZONTAL_LINE);
                userMsgList.add(userMsg);
                System.out.println("added: " + userMsg);
                System.out.println(HORIZONTAL_LINE);
            }

            userMsg = reader.next();
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye! Hope to see you again soon! \n");
        System.out.println(HORIZONTAL_LINE);
    }
}
