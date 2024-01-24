import Exceptions.DukeException;
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
                    System.out.println(HORIZONTAL_LINE);

                    try {
                        ToDo newTodo = new ToDo(userMsg);
                        userTaskList.add(newTodo);

                        System.out.println(ADD_TASK);
                        System.out.println(newTodo);
                        System.out.println("Now you have " + userTaskList.size() + " tasks in your list.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println(HORIZONTAL_LINE);
                    userMsg = reader.nextLine();
                    continue;
                case "deadline":
                    System.out.println(HORIZONTAL_LINE);

                    try {
                        Deadline newDeadline = new Deadline(userMsg);
                        userTaskList.add(newDeadline);

                        System.out.println(ADD_TASK);
                        System.out.println(newDeadline);
                        System.out.println("Now you have " + userTaskList.size() + " tasks in your list.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println(HORIZONTAL_LINE);

                    userMsg = reader.nextLine();
                    continue;
                case "event":
                    System.out.println(HORIZONTAL_LINE);

                    try {
                        Event newEvent = new Event(userMsg);
                        userTaskList.add(newEvent);

                        System.out.println(ADD_TASK);
                        System.out.println(newEvent);
                        System.out.println("Now you have " + userTaskList.size() + " tasks in your list.");
                    } catch (DukeException e) {
                        System.out.println(e.getMessage());
                    }

                    System.out.println(HORIZONTAL_LINE);

                    userMsg = reader.nextLine();
                    continue;
                default:
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("Unknown command!g");
                    System.out.println(HORIZONTAL_LINE);

                    userMsg = reader.nextLine();
            }
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
}
