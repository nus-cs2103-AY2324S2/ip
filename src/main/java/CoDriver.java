import java.util.Scanner;

public class CoDriver {
    public static void main(String[] args) {
        greeting();
        Scanner scanner = new Scanner(System.in);
        TaskList tl = TaskList.openTaskList("./data/codriver.txt");
        while (true) {
            String commandLine = scanner.nextLine();
            String[] arguments = commandLine.split(" ");
            Command c = Parser.parse(arguments[0]);
            try {
                if (c == Command.BYE) {
                    break;
                } else if (c == Command.LIST) {
                    tl.listTasks();
                } else if (c == Command.MARK) {
                    if (arguments.length > 2) {
                        throw new CoDriverException("Error! You should only provide 1 argument for mark!");
                    } else if (arguments.length < 2) {
                        throw new CoDriverException("Error! You should provide an integer argument for mark!");
                    }
                    int index = Integer.parseInt(arguments[1]);
                    tl.markTask(index);
                } else if (c == Command.UNMARK) {
                    if (arguments.length > 2) {
                        throw new CoDriverException("Error! You should only provide 1 argument for unmark!");
                    } else if (arguments.length < 2) {
                        throw new CoDriverException("Error! You should provide an integer argument for unmark!");
                    }
                    int index = Integer.parseInt(arguments[1]);
                    tl.unmarkTask(index);
                } else if (c == Command.TODO) {
                    ToDo task = ToDo.createToDo(commandLine);
                    tl.addTask(task);
                } else if (c == Command.DEADLINE) {
                    Deadline task = Deadline.createDeadline(commandLine);
                    tl.addTask(task);
                } else if (c == Command.EVENT) {
                    Event task = Event.createEvent(commandLine);
                    tl.addTask(task);
                } else if (c == Command.DELETE) {
                    if (arguments.length > 2) {
                        throw new CoDriverException("Error! You should only provide 1 argument for delete!");
                    } else if (arguments.length < 2) {
                        throw new CoDriverException("Error! You should provide an integer argument for delete!");
                    }
                    int index = Integer.parseInt(arguments[1]);
                    tl.deleteTask(index);
                } else if (c == Command.UNKNOWN) {
                    throw new CoDriverException("I'm sorry, I don't understand this command: " + arguments[0]);
                }
            } catch (CoDriverException e) {
                Format.printSepLine();
                System.out.println(e);
                Format.printSepLine();
            } catch (NumberFormatException e) {
                Format.printSepLine();
                System.out.println("Error! Argument provided must be a number!");
                Format.printSepLine();
            }
        }
        goodbye();
        tl.saveTaskList("./data/codriver.txt");
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
