import java.util.Scanner;

public class CoDriver {
    public static void main(String[] args) {
        greeting();
        Scanner scanner = new Scanner(System.in);
        TaskList tl = new TaskList();
        while (true) {
            String command = scanner.nextLine();
            try {
                if (command.equals("bye")) {
                    break;
                } else if (command.equals("list")) {
                    tl.listTasks();
                } else if (command.startsWith("mark")) {
                    String[] words = command.split(" ");
                    if (!words[0].equals("mark")) {
                        throw new CoDriverException("I'm sorry, I don't understand this command: " + words[0]);
                    }
                    int index = Integer.parseInt(words[1]);
                    tl.markTask(index);
                } else if (command.startsWith("unmark")) {
                    String[] words = command.split(" ");
                    if (!words[0].equals("unmark")) {
                        throw new CoDriverException("I'm sorry, I don't understand this command: " + words[0]);
                    }
                    int index = Integer.parseInt(words[1]);
                    tl.unmarkTask(index);
                } else if (command.startsWith("todo")) {
                    ToDo task = ToDo.parseCommand(command);
                    tl.addTask(task);
                } else if (command.startsWith("deadline")) {
                    Deadline task = Deadline.parseCommand(command);
                    tl.addTask(task);
                } else if (command.startsWith("event")) {
                    Event task = Event.parseCommand(command);
                    tl.addTask(task);
                } else {
                    throw new CoDriverException("I'm sorry, I don't understand this command: " + command);
                }
            } catch (CoDriverException e) {
                Format.printSepLine();
                System.out.println(e);
                Format.printSepLine();
            }
        }
        goodbye();
    }

    private static void greeting() {
        Format.printSepLine();
        System.out.println("Hello! I'm CoDriver, your everyday AI companion!");
        System.out.println("What can I do for you?");
        Format.printSepLine();
    }

    private static void goodbye() {
        Format.printSepLine();
        System.out.println("Bye. Hope to see you again soon!");
        Format.printSepLine();
    }
}
