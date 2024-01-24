import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Duke {

    final static String HORIZONTAL_LINE = "____________________________________________________________";
    final static String ADD_TASK = "Got it. I've added this task to your list.";
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        List<Task> userTaskList = new ArrayList<>();

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello from Kewgy!");
        System.out.println("What can I do for you?");
        System.out.println("Type \"bye\" to exit!");
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

            switch (userMsgParsed[0]) {
                case "mark":
                    if (checkValidMarkCommand(userMsgParsed, userTaskList)) {
                        System.out.println(HORIZONTAL_LINE);
                        userTaskList.get(Integer.parseInt(userMsgParsed[1]) - 1).setDone(true);
                        System.out.println(userTaskList.get(Integer.parseInt(userMsgParsed[1]) - 1));
                        System.out.println(HORIZONTAL_LINE);

                        userMsg = reader.nextLine();
                        continue;
                    }
                    break;
                case "unmark":
                    if (checkValidMarkCommand(userMsgParsed, userTaskList)) {
                        System.out.println(HORIZONTAL_LINE);
                        userTaskList.get(Integer.parseInt(userMsgParsed[1]) - 1).setDone(false);
                        System.out.println(userTaskList.get(Integer.parseInt(userMsgParsed[1]) - 1));
                        System.out.println(HORIZONTAL_LINE);

                        userMsg = reader.nextLine();
                        continue;
                    }
                    break;
                case "todo":
                    if (checkValidCommand(userMsgParsed)) {
                        System.out.println(HORIZONTAL_LINE);
                        System.out.println(ADD_TASK);
                        ToDo newTodo = new ToDo(userMsg);
                        userTaskList.add(newTodo);
                        System.out.println(newTodo);
                        System.out.println("Now you have " + userTaskList.size() + " tasks in your list.");
                        System.out.println(HORIZONTAL_LINE);

                        userMsg = reader.nextLine();
                        continue;
                    }
                    break;
                case "deadline":
                    if (checkValidCommand(userMsgParsed)) {
                        System.out.println(HORIZONTAL_LINE);
                        System.out.println(ADD_TASK);
                        Deadline newDeadline = new Deadline(userMsg);
                        userTaskList.add(newDeadline);
                        System.out.println(newDeadline);
                        System.out.println("Now you have " + userTaskList.size() + " tasks in your list.");
                        System.out.println(HORIZONTAL_LINE);

                        userMsg = reader.nextLine();
                        continue;
                    }
                    break;
                case "event":
                    if (checkValidCommand(userMsgParsed)) {
                        System.out.println(HORIZONTAL_LINE);
                        System.out.println(ADD_TASK);
                        Event newEvent = new Event(userMsg);
                        userTaskList.add(newEvent);
                        System.out.println(newEvent);
                        System.out.println("Now you have " + userTaskList.size() + " tasks in your list.");
                        System.out.println(HORIZONTAL_LINE);

                        userMsg = reader.nextLine();
                        continue;
                    }
                    break;
            }

            System.out.println(HORIZONTAL_LINE);
            userTaskList.add(new Task(userMsg));
            System.out.println("added: " + userMsg);
            System.out.println(HORIZONTAL_LINE);

            userMsg = reader.nextLine();
        }

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    public static boolean checkValidMarkCommand(String[] userMsgParsed, List<Task> userTaskList) {
        return userMsgParsed.length > 1 &&
                userMsgParsed[1].chars().allMatch(Character::isDigit) &&
                Integer.parseInt(userMsgParsed[1]) <= userTaskList.size() &&
                Integer.parseInt(userMsgParsed[1]) > 0;
    }

    public static boolean checkValidCommand(String[] userMsgParsed) {
        return userMsgParsed.length > 1;
    }
}
