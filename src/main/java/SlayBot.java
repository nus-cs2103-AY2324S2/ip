import entity.*;
import java.util.*;

public class SlayBot {
    public static final String DIVIDER = "____________________________________________________________";
    public static final String WELCOME_TEXT = "Hello! I'm SlayBot\nWhat can I do for you?";
    public static final String BYE_TEXT = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        boolean flag = true;

        System.out.println(DIVIDER + "\n" + WELCOME_TEXT + "\n" + DIVIDER);

        while (flag) {
            String input = sc.nextLine();
            String[] splitWords = input.split(" ");
            String command = splitWords[0];

            switch (command) {
                case "bye":
                    flag = false;
                    System.out.println(DIVIDER + "\n" + BYE_TEXT + "\n" + DIVIDER);
                    break;

                case "list":
                    System.out.println(DIVIDER);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }
                    System.out.println(DIVIDER);
                    break;

                case "todo":
                    String todo_title = "";
                    for (int i = 1; i < splitWords.length; i++) {
                        todo_title += splitWords[i] + " ";
                    }
                    ToDos todo = new ToDos(todo_title);
                    list.add(todo);
                    System.out.println(DIVIDER);
                    System.out.println("Todo Task Added: " + todo.toString());
                    System.out.println("You have " + list.size() + " tasks");
                    System.out.println(DIVIDER);
                    break;

                case "deadline":
                    String deadline_title = "";
                    String dateTime = "";
                    for (int i = 1; i < splitWords.length; i++) {
                        if (splitWords[i].equals("/by")) {
                            dateTime = splitWords[i + 1];
                            i++;
                        } else {
                            deadline_title += splitWords[i] + " ";
                        }
                    }
                    Deadlines deadline = new Deadlines(deadline_title, dateTime);
                    list.add(deadline);
                    System.out.println(DIVIDER);
                    System.out.println("Deadline Task Added: " + deadline.toString());
                    System.out.println("You have " + list.size() + " tasks");
                    System.out.println(DIVIDER);
                    break;

                case "event":
                    Event event = eventFormat(splitWords);
                    list.add(event);
                    System.out.println(DIVIDER);
                    System.out.println("Event Task Added: " + event.toString());
                    System.out.println("You have " + list.size() + " tasks");
                    System.out.println(DIVIDER);
                    break;

                case "mark":
                    Task taskToMark = list.get(Integer.parseInt(splitWords[1]) - 1);
                    taskToMark.setMarked(true);
                    System.out.println(DIVIDER + "\nNice! I've marked this task as done:\n" + taskToMark.toString() +
                            "\n" + DIVIDER);
                    break;

                case "unmark":
                    Task taskToUnmark = list.get(Integer.parseInt(splitWords[1]) - 1);
                    taskToUnmark.setMarked(false);
                    System.out.println(DIVIDER + "\nOK, I've marked this task as not done yet:\n" + taskToUnmark.toString() +
                            "\n" + DIVIDER);
                    break;


            }
        }
    }

    private static Event eventFormat(String[] splitWords) {
        String combinedWord = "";
        for (int i = 1; i < splitWords.length; i++) {
            combinedWord += splitWords[i] + " ";
        }
        int indexFrom = combinedWord.indexOf("/from");
        int indexTo = combinedWord.indexOf("/to");

        String beforeFrom = combinedWord.substring(0, indexFrom).trim();
        String afterFrom = combinedWord.substring(indexFrom + "/from".length(), indexTo).trim();
        String afterTo = combinedWord.substring(indexTo + "/to".length()).trim();

        return new Event(beforeFrom, afterFrom, afterTo);
    }
}
