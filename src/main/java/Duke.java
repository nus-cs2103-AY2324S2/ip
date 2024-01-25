import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String hRULER = "____________________________________________________________\n";
    private static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
    private static void run() s{
        ArrayList<Task> storage = new ArrayList<Task>();
        int currentIdx = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                String echoInput = sc.nextLine();
                Task newTask;

                if (echoInput.equals("bye")) {
                    return;
                }
                if (echoInput.equals("list")) {
                    System.out.printf("%s Here are the tasks in your list:\n", hRULER);
                    for (int i = 0; i < currentIdx; i++) {
                        System.out.printf(" %d.%s\n", i + 1, storage.get(i).toString());
                    }
                    System.out.println(hRULER);
                    continue;
                }
                if (echoInput.substring(0, 4).equals("mark")
                        && isNumeric(echoInput.substring(5))) {
                    int taskIdx = Integer.parseInt(echoInput.substring(5));
                    storage.get(taskIdx - 1).markDone();
                    continue;
                }
                else if (echoInput.substring(0, 4).equals("todo")) {
                    if (echoInput.length() == 4) {
                        throw new EmptyDescriptionException("todo");
                    }
                    newTask = new ToDo(echoInput.substring(5));
                }
                else if (echoInput.substring(0, 5).equals("event")) {
                    if (echoInput.length() == 5) {
                        throw new EmptyDescriptionException("event");
                    }
                    int startIdx = 0;
                    boolean foundTime = false;
                    while (startIdx < echoInput.length()) {
                        if (echoInput.charAt(startIdx) != '/') {
                            startIdx++;
                        } else {
                            foundTime = true;
                            break;
                        }
                    }
                    if (!foundTime) {
                        throw new InvalidDeadlineException();
                    }
                    int endIdx = startIdx + 1;
                    while (endIdx < echoInput.length()) {
                        if (echoInput.charAt(endIdx) != '/') {
                            endIdx++;
                        } else {
                            break;
                        }
                    }
                    if (!foundTime) {
                        throw new InvalidDeadlineException();
                    }
                    newTask = new Event(echoInput.substring(6, startIdx),
                            echoInput.substring(startIdx + 6, endIdx),
                            echoInput.substring(endIdx + 4));
                }
                else if (echoInput.substring(0, 6).equals("unmark") &&
                        isNumeric(echoInput.substring(7))) {
                    int taskIdx = Integer.parseInt(echoInput.substring(7));
                    storage.get(taskIdx - 1).unMarkDone();
                    continue;
                }
                else if (echoInput.substring(0, 6).equals("delete") &&
                        isNumeric(echoInput.substring(7))) {
                    int taskIdx = Integer.parseInt(echoInput.substring(7));
                    Task removed = storage.remove(taskIdx - 1);
                    currentIdx--;
                    System.out.printf("%sNoted. I've removed this task:\n  %s\nNow you have %d tasks in the list.\n%s",
                            hRULER, removed, currentIdx, hRULER);
                    continue;
                }
                else if (echoInput.substring(0, 8).equals("deadline")) {
                    if (echoInput.length() == 8) {
                        throw new EmptyDescriptionException("deadline");
                    }
                    int deadlineStartIdx = 0;
                    boolean foundTime = false;
                    while (deadlineStartIdx < echoInput.length()) {
                        if (echoInput.charAt(deadlineStartIdx) != '/') {
                            deadlineStartIdx++;
                        } else {
                            foundTime = true;
                            break;
                        }
                    }
                    if (!foundTime) {
                        throw new InvalidDeadlineException();
                    }
                    newTask = new Deadline(echoInput.substring(9, deadlineStartIdx),
                            echoInput.substring(deadlineStartIdx + 4));
                }
                else {
                    throw new UnknownInputException();
                }
                storage.add(newTask);
                currentIdx++;
                System.out.printf("%s Got it. I've added this task:\n  " +
                        " %s\n Now you have %d tasks in the list.\n%s", hRULER, newTask, currentIdx, hRULER);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.printf("%s%s%s", hRULER, new UnknownInputException(), hRULER);
            } catch (DukeException e) {
                System.out.printf("%s%s%s", hRULER, e, hRULER);
            }
        }
    }
    public static void main(String[] args) {
        String[] token = new String[]{
                "____________________________________________________________",
                "Hello! I'm chinesepoliceman",
                "What can I do for you?",
                "____________________________________________________________",
                " Bye. Hope to see you again soon!",
                "____________________________________________________________"
        };
        for (int i = 0; i < 4; i++) {
            System.out.println(token[i]);
        }
        run();
        for (int i = 3; i < 6; i++) {
            System.out.println(token[i]);
        }
    }
}
