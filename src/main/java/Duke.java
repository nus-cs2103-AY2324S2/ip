import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    final static String HORIZONTAL_LINE = "____________________________________________________________ \n";

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        List<Task> userTaskList = new ArrayList<>();

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello from Kewgy! \n");
        System.out.println("What can I do for you? \n");
        System.out.println("Type \"bye\" to exit! \n");
        System.out.println(HORIZONTAL_LINE);

        String userMsg = reader.nextLine();
        while (!Objects.equals(userMsg, "bye")) {
            if (Objects.equals(userMsg, "list")) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("Here are the tasks in your list: ");
                for (int i = 1; i < userTaskList.size() + 1; i++) {
                    System.out.println(i + ": " + userTaskList.get(i - 1));
                }
                System.out.println(HORIZONTAL_LINE);

                userMsg = reader.nextLine();
                continue;
            }
            String[] userMsgParsed = userMsg.split(" ", 2);

            if (userMsgParsed[0].equals("mark")) {
                if (checkValidMarkCommand(userMsgParsed, userTaskList)) {
                    System.out.println(HORIZONTAL_LINE);
                    userTaskList.get(Integer.parseInt(userMsgParsed[1]) - 1).setDone(true);
                    System.out.println(userTaskList.get(Integer.parseInt(userMsgParsed[1]) - 1));
                    System.out.println(HORIZONTAL_LINE);

                    userMsg = reader.nextLine();
                    continue;
                }
            } else if (userMsgParsed[0].equals("unmark")) {
                if (checkValidMarkCommand(userMsgParsed, userTaskList)) {
                    System.out.println(HORIZONTAL_LINE);
                    userTaskList.get(Integer.parseInt(userMsgParsed[1]) - 1).setDone(false);
                    System.out.println(userTaskList.get(Integer.parseInt(userMsgParsed[1]) - 1));
                    System.out.println(HORIZONTAL_LINE);

                    userMsg = reader.nextLine();
                    continue;
                }
            }

            System.out.println(HORIZONTAL_LINE);
            userTaskList.add(new Task(userMsg));
            System.out.println("added: " + userMsg);
            System.out.println(HORIZONTAL_LINE);

            userMsg = reader.nextLine();
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye! Hope to see you again soon! \n");
        System.out.println(HORIZONTAL_LINE);
    }

    public static boolean checkValidMarkCommand(String[] userMsgParsed, List<Task> userTaskList) {
        return userMsgParsed.length > 1 &&
                userMsgParsed[1].chars().allMatch(Character::isDigit) &&
                Integer.parseInt(userMsgParsed[1]) < userTaskList.size() &&
                Integer.parseInt(userMsgParsed[1]) > 0;
    }
}
