import entity.*;
import exception.InvalidDeadlineException;
import exception.InvalidEventException;
import exception.InvalidTodoException;
import exception.UnknownCommandException;

import java.util.*;

public class SlayBot {
    public static final String DIVIDER = "____________________________________________________________";
    public static final String WELCOME_TEXT = "Hello! I'm SlayBot\nWhat can I do for you?";
    public static final String BYE_TEXT = "Bye. Hope to see you again soon!";

    enum Command {
        BYE,
        LIST,
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        boolean flag = true;

        System.out.println(DIVIDER + "\n" + WELCOME_TEXT + "\n" + DIVIDER);

        while (flag) {
            String input = sc.nextLine();
            String[] splitWords = input.split(" ");
            Command command = null;

            while (command == null) {
                try {
                    command = parseCommand(splitWords);
                } catch (UnknownCommandException e) {
                    System.out.println(DIVIDER);
                    System.out.println(e.getMessage());
                    System.out.println("Enter a valid command");
                    System.out.println(DIVIDER);

                    input = sc.nextLine();
                    splitWords = input.split(" ");
                }
            }

            switch (command) {
                case BYE:
                    flag = false;
                    System.out.println(DIVIDER + "\n" + BYE_TEXT + "\n" + DIVIDER);
                    break;

                case LIST:
                    System.out.println(DIVIDER);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }
                    System.out.println(DIVIDER);
                    break;

                case TODO:
                    ToDo todo = null;
                    try {
                        todo = parseTodo(splitWords);
                    } catch (InvalidTodoException e) {
                        System.out.println(DIVIDER + "\n" + e.getMessage() + "\nPlease try again" + "\n" + DIVIDER);
                        continue;
                    }

                    list.add(todo);
                    System.out.println(DIVIDER);
                    System.out.println("Todo Task Added: " + todo.toString());
                    System.out.println("You have " + list.size() + " tasks");
                    System.out.println(DIVIDER);
                    break;

                case DEADLINE:
                    Deadline deadline = null;
                    try {
                        deadline = parseDeadline(splitWords);
                    } catch (InvalidDeadlineException e) {
                        System.out.println(DIVIDER + "\n" + e.getMessage() + "\nPlease try again" + "\n" + DIVIDER);
                        continue;
                    }

                    list.add(deadline);
                    System.out.println(DIVIDER);
                    System.out.println("Deadline Task Added: " + deadline.toString());
                    System.out.println("You have " + list.size() + " tasks");
                    System.out.println(DIVIDER);
                    break;

                case EVENT:
                    Event event = null;
                    try {
                        event = parseEvent(splitWords);
                    } catch (InvalidEventException e) {
                        System.out.println(DIVIDER + "\n" + e.getMessage() + "\nPlease try again" + "\n" + DIVIDER);
                        continue;
                    }
                    list.add(event);
                    System.out.println(DIVIDER);
                    System.out.println("Event Task Added: " + event.toString());
                    System.out.println("You have " + list.size() + " tasks");
                    System.out.println(DIVIDER);
                    break;

                case MARK:
                    Task taskToMark = list.get(Integer.parseInt(splitWords[1]) - 1);
                    taskToMark.setMarked(true);
                    System.out.println(DIVIDER + "\nNice! I've marked this task as done:\n" + taskToMark.toString() +
                            "\n" + DIVIDER);
                    break;

                case UNMARK:
                    Task taskToUnmark = list.get(Integer.parseInt(splitWords[1]) - 1);
                    taskToUnmark.setMarked(false);
                    System.out.println(DIVIDER + "\nOK, I've marked this task as not done yet:\n" + taskToUnmark.toString() +
                            "\n" + DIVIDER);
                    break;
                case DELETE:
                    try {
                        int indexToDelete = Integer.parseInt(splitWords[1]);
                        list.remove(indexToDelete - 1);
                        System.out.println(DIVIDER + "\n Successful deletion \n You now have " +
                                list.size() + " tasks\n" + DIVIDER);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(DIVIDER + "\n Please input a valid index\n" + DIVIDER);
                        continue;
                    }

            }
        }
    }

    private static Deadline parseDeadline(String[] arr) throws InvalidDeadlineException {
        String deadline_title = "";
        String dateTime = "";

        for (int i = 1; i < arr.length; i++) {
            if (arr[i].equals("/by")) {
                dateTime = arr[i + 1];
                i++;
            } else {
                deadline_title += arr[i] + " ";
            }
        }

        if (deadline_title.isEmpty() && dateTime.isEmpty()) {
            throw new InvalidDeadlineException("OOPS!!! The description and date of a Deadline cannot be empty.");
        } else if (deadline_title.isEmpty()) {
            throw new InvalidDeadlineException("OOPS!!! The description of a Deadline cannot be empty.");
        } else if (dateTime.isEmpty()) {
            throw new InvalidDeadlineException("OOPS!!! The date of a Deadline cannot be empty.");
        }

        return new Deadline(deadline_title, dateTime);
    }

    private static ToDo parseTodo(String[] arr) throws InvalidTodoException {
        String todo_title = "";

        if (arr.length - 1 == 0) {
            throw new InvalidTodoException("OOPS!!! The description of a Todo cannot be empty.");
        }

        for (int i = 1; i < arr.length; i++) {
            todo_title += arr[i];
            if (i != arr.length - 1) {
                todo_title += " ";
            }
        }

        return new ToDo(todo_title);
    }

    private static Event parseEvent(String[] splitWords) throws InvalidEventException {
        String combinedWord = "";
        for (int i = 1; i < splitWords.length; i++) {
            combinedWord += splitWords[i] + " ";
        }
        int indexFrom = combinedWord.indexOf("/from");
        int indexTo = combinedWord.indexOf("/to");

        if (splitWords.length - 1 == 0) {
            throw new InvalidEventException("OOPS!!! The description of an Event cannot be empty.");
        } else if (indexFrom < 0 || indexTo < 0) {
            throw new InvalidEventException("OOPS!!! The date of an Event cannot be empty.");
        }

        String beforeFrom = combinedWord.substring(0, indexFrom).trim();
        String afterFrom = combinedWord.substring(indexFrom + "/from".length(), indexTo).trim();
        String afterTo = combinedWord.substring(indexTo + "/to".length()).trim();

        return new Event(beforeFrom, afterFrom, afterTo);
    }

    private static Command parseCommand(String[] arr) throws UnknownCommandException {
        switch (arr[0]) {
            case "bye":
                return Command.BYE;

            case "list":
                return Command.LIST;

            case "todo":
                return Command.TODO;

            case "deadline":
                return Command.DEADLINE;

            case "event":
                return Command.EVENT;

            case "mark":
                return Command.MARK;

            case "unmark":
                return Command.UNMARK;

            case "delete":
                return Command.DELETE;

            default:
                throw new UnknownCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
