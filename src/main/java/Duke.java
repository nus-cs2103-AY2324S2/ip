import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;  // Import the Scanner class
import java.util.stream.Collectors;

public class Duke {
    // class variable
    static String CHATBOT_NAME = "ByteBuddy";
    static String solidLineBreak = "____________________________________________________________";
    static String START_MESSAGE = "Hello! I'm " + CHATBOT_NAME + "\n" + "\t What can I do for you?";
    static String BYE_MESSAGE = "Sad to see you leave :(";

    static ArrayList<Task> taskList;

    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        taskList = new ArrayList<>();

        // start
        printWithSolidLineBreak(START_MESSAGE);

        // repeating user commands
        label:
        while (true) {
            String command = sc.next();
            String info = sc.nextLine().trim();

            try {
                switch (command) {
                    case "bye":
                        break label;
                    case "list":
                        printTaskList(taskList);
                        break;
                    case "mark":
                        mark(info);
                        break;
                    case "unmark":
                        unmark(info);
                        break;
                    case "delete":
                        delete(info);
                        break;
                    case "todo":
                        todo(info);
                        break;
                    case "deadline":
                        deadline(info);
                        break;
                    case "event":
                        event(info);
                        break;
                    default:
                        throw new DukeException("Sorry but this command does not exist~");
                }
            } catch (DukeException e) {
                printWithSolidLineBreak(e.getMessage());
            } catch (NumberFormatException e) {
                printWithSolidLineBreak("Invalid task number format! Please enter a valid number.");
            }
        }

        // bye
        printWithSolidLineBreak(BYE_MESSAGE);
    }

    public static List<String> splitStringWithTrim(String info, String separator) {
        return Arrays.stream(info.split(separator)).map(String::trim).collect(Collectors.toList());
    }

    public static void mark(String info) throws DukeException, NumberFormatException {
        int markIndex = Integer.parseInt(info.trim()) - 1;
        if (markIndex < 0 || markIndex >= taskList.size()) {
            throw new DukeException("we do not have this task number!!");
        }
        String markToPrint = taskList.get(markIndex).markAsDone();
        printWithSolidLineBreak(markToPrint);
    }

    public static void unmark(String info) throws DukeException, NumberFormatException {
        int unmarkIndex = Integer.parseInt(info.trim()) - 1;
        if (unmarkIndex < 0 || unmarkIndex >= taskList.size()) {
            throw new DukeException("we do not have this task number!!");
        }
        String unmarkToPrint =taskList.get(unmarkIndex).unmarkAsDone();
        printWithSolidLineBreak(unmarkToPrint);
    }

    public static void delete(String info) throws DukeException, NumberFormatException {
        int deleteIndex = Integer.parseInt(info.trim()) - 1;
        if (deleteIndex < 0 || deleteIndex >= taskList.size()) {
            throw new DukeException("we do not have this task number!!");
        }
        Task removed = taskList.remove(deleteIndex);
        printTaskRemovedWithSolidLineBreak(removed);
    }

    public static void todo(String info) throws DukeException {
        if (info.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty??");
        }
        Task todo = new Todo(info);
        taskList.add(todo);
        printTaskAddedWithSolidLineBreak(todo);
    }

    public static void deadline(String info) throws DukeException {
        if (info.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty??");
        }
        List<String> deadlineInfo = splitStringWithTrim(info, "/");
        Task deadline = new Deadline(deadlineInfo.get(0), deadlineInfo.get(1).substring(3));
        taskList.add(deadline);
        printTaskAddedWithSolidLineBreak(deadline);
    }

    public static void event(String info) throws DukeException {
        if (info.isEmpty()) {
            throw new DukeException("The description of a event cannot be empty??");
        }
        List<String> eventInfo = splitStringWithTrim(info, "/");
        Task event = new Event(eventInfo.get(0), eventInfo.get(1).substring(5), eventInfo.get(2).substring(3));
        taskList.add(event);
        printTaskAddedWithSolidLineBreak(event);
    }

    public static void printWithSolidLineBreak(String s) {
        System.out.println("\t" + solidLineBreak);
        System.out.println("\t " + s);
        System.out.println("\t" + solidLineBreak);
    }

    public static void printTaskAddedWithSolidLineBreak(Task task) {
        System.out.println("\t" + solidLineBreak);
        System.out.println("\t Got it. I've Added this task:");
        System.out.println("\t\t " + task);
        System.out.println("\t Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("\t" + solidLineBreak);
    }

    public static void printTaskRemovedWithSolidLineBreak(Task task) {
        System.out.println("\t" + solidLineBreak);
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t\t " + task);
        System.out.println("\t Now you have " + taskList.size() + " tasks in the list.");
        System.out.println("\t" + solidLineBreak);
    }

    public static void printTaskList(ArrayList<Task> taskList) {
        System.out.println("\t" + solidLineBreak);
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t " + (i+1) + "." + taskList.get(i));
        }
        System.out.println("\t" + solidLineBreak);
    }
}
