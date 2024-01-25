import java.util.ArrayList;
import java.util.Scanner;

public class Buddy {
    private final String lineBreak = "____________________________________________________________\n";
    ArrayList<Task> taskList = new ArrayList<>();

    private void greet() {
        String logo =
            "               ____            _     _       \n" +
            "              |  _ \\          | |   | |      \n" +
            "              | |_) |_   _  __| | __| |_   _ \n" +
            "              |  _ <| | | |/ _` |/ _` | | | |\n" +
            "              | |_) | |_| | (_| | (_| | |_| |\n" +
            "              |____/ \\__,_|\\__,_|\\__,_|\\__, |\n" +
            "                                        __/ |\n" +
            "                                       |___/ \n";
        System.out.println(lineBreak + logo + lineBreak + " Hello friend!\n" + " How can I help you?\n" + lineBreak);
    }

    private void exit() {
        System.out.println(lineBreak + " Bye. Hope to see you again soon!\n" + lineBreak);
    }

    private void run() {
        boolean running = true;
        Scanner sc = new Scanner(System.in);

        while (running) {
            String input = sc.nextLine().trim();

            if (!input.equals("")) {
                int idx = input.indexOf(" ");
                String command;

                if (idx >= 0) {
                    command = input.substring(0, idx);
                } else {
                    command = input;
                }

                running = command(command, input);
            }
        }
        sc.close();
    }

    private boolean command(String cmd, String input) {
        switch (cmd) {
            case "bye":
                return false;
            case "list":
                System.out.print(lineBreak);
                System.out.println("Here you go bud!:");

                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". "+ taskList.get(i));
                }

                System.out.println(lineBreak);
                return true;
            case "mark":
            case "unmark":
                try {
                    int intIdx = input.indexOf(" ") + 1;
                    int taskIdx = Integer.parseInt(input.substring(intIdx)) - 1;
                    Task task = taskList.get(taskIdx);
                    task.changeStatus(cmd);

                    String msg = "I've updated this task for you!\n" + task + "\n";
                    System.out.println(lineBreak + msg + lineBreak);
                    return true;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(lineBreak + "The index you provided is out of bounds!\n" + lineBreak);
                    return true;
                } catch (NumberFormatException e) {
                    System.out.println(lineBreak + "I don't know which task you are talking about!\n" +
                                       "Please provide a valid integer!\n" + lineBreak);
                    return true;
                }
            case "todo":
                try {
                    int todoIdx = input.indexOf(" ");
                    if (todoIdx >= 0) {
                        Todo todo = new Todo(input.substring(todoIdx + 1));
                        addTask(todo);
                        return true;
                    } else {
                        throw new BuddyException("Please provide a task to do!\n");
                    }
                } catch (BuddyException e) {
                    System.out.println(lineBreak + e.getMessage() + lineBreak);
                    return true;
                }
            case "deadline":
                try {
                    int deadlineIdx = input.indexOf(" ");
                    if (deadlineIdx >= 0) {
                        int deadlineEndIdx = input.indexOf("/by");
                        if (deadlineEndIdx >= 0) {
                            String deadlineEnd = input.substring(deadlineEndIdx + 4);
                            Deadline deadline = new Deadline(input.substring(deadlineIdx + 1, deadlineEndIdx - 1),
                                                             deadlineEnd);
                            addTask(deadline);
                            return true;
                        } else {
                            throw new BuddyException("Please prove a deadline for the task using /by!\n");
                        }
                    } else {
                        throw new BuddyException("Please provide a task to do!\n");
                    }
                } catch (BuddyException e) {
                    System.out.println(lineBreak + e.getMessage() + lineBreak);
                    return true;
                }
            case "event":
                try {
                    int eventIdx = input.indexOf(" ");
                    if (eventIdx >= 0) {
                        int eventStartIdx = input.indexOf("/from");
                        int eventEndIdx = input.indexOf("/to");

                        if (eventStartIdx >= 0 && eventEndIdx >= 0) {
                            String eventStart = input.substring(eventStartIdx + 6, eventEndIdx);
                            String eventEnd = input.substring(eventEndIdx + 4);
                            Event event = new Event(input.substring(eventIdx + 1, eventStartIdx - 1), eventStart, eventEnd);
                            addTask(event);
                            return true;
                        } else {
                            throw new BuddyException("Please provide a valid time range using /from and /to!\n");
                        }
                    } else {
                        throw new BuddyException("Please provide an event!\n");
                    }
                } catch (BuddyException e) {
                    System.out.println(lineBreak + e.getMessage() + lineBreak);
                    return true;
                }
            default:
                try {
                    throw new BuddyException("Sorry friend but that's not a valid command!\n");
                } catch (BuddyException e) {
                    System.out.println(lineBreak + e.getMessage() + lineBreak);
                    return true;
                }
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
        String msg = "Alrighty! I've added the task to your list!\n" + task + "\n" +
                     "You have " + taskList.size() + " tasks!\n";
        System.out.println(lineBreak + msg + lineBreak);
    }

    public static void main(String[] args) {
        Buddy buddy = new Buddy();
        buddy.greet();
        buddy.run();
        buddy.exit();
    }
}
