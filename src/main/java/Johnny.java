import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Johnny {

    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Johnny here. What do you want bro?\n");
        Johnny.takeCommands();
        System.out.println("Bye bro. I'm going back to sleep.");
    }

    public static void takeCommands() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                String input = scanner.nextLine();
                String command = input.split(" ")[0];

                if (command.equals(Command.BYE.getInput())) {
                    break;
                } else if (command.equals(Command.LIST.getInput())) {
                    Johnny.list();
                } else if (command.equals(Command.MARK.getInput())) {
                    Johnny.mark(input);
                } else if (command.equals(Command.UNMARK.getInput())) {
                    Johnny.unmark(input);
                } else if (command.equals(Command.TODO.getInput())) {
                    Johnny.addToDo(input);
                } else if (command.equals(Command.DEADLINE.getInput())) {
                    Johnny.addDeadline(input);
                } else if (command.equals(Command.EVENT.getInput())) {
                    Johnny.addEvent(input);
                } else if (command.equals(Command.DELETE.getInput())) {
                    Johnny.delete(input);
                } else {
                    throw new JohnnyException("Your command does not make sense bro.");
                }
            } catch (JohnnyException e) {
                System.out.println(e.getMessage() + "\n");
            }
        }

        scanner.close();
    }

    public static void list() {
        System.out.println("Get all these done bro:");
        for (int i = 0; i < Johnny.list.size(); i++) {
            System.out.println(i + 1 + ". " + list.get(i));
        }
        System.out.println();
    }

    public static void mark(String command) throws JohnnyException {
        try {
            String[] arr = command.split(" ");

            if (arr.length == 1) {
                throw new JohnnyException("Which task am I supposed to mark bro?");
            } else if (arr.length > 2) {
                throw new JohnnyException("I can only mark 1 task bro.");
            }

            int index = Integer.parseInt(arr[1]) - 1;
            Task task = Johnny.list.get(index);
            task.mark();
            System.out.println("Finally done bro.");
            System.out.println(task + "\n");
        } catch (NumberFormatException e) {
            throw new JohnnyException("Key in a number bro.");
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("This task does not exist bro.");
        }
    }

    public static void unmark(String command) throws JohnnyException {
        try {
            String[] arr = command.split(" ");

            if (arr.length == 1) {
                throw new JohnnyException("Which task am I supposed to unmark bro?");
            } else if (arr.length > 2) {
                throw new JohnnyException("I can only unmark 1 task bro.");
            }

            int index = Integer.parseInt(arr[1]) - 1;
            Task task = Johnny.list.get(index);
            task.unmark();
            System.out.println("Why are you not done yet bro?");
            System.out.println(task + "\n");
        } catch (NumberFormatException e) {
            throw new JohnnyException("Key in a number bro.");
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("This task does not exist bro.");
        }
    }

    public static void addToDo(String command) throws JohnnyException {
        List<String> l = Arrays.asList(command.split(" "));

        if (l.size() == 1) {
            throw new JohnnyException("What is your todo bro?");
        }

        Task task = new ToDo(String.join(" ", l.subList(1, l.size())));
        Johnny.list.add(task);
        System.out.println("Go get this done bro:");
        System.out.println(task);
        System.out.println("You still have " + Johnny.list.size() + " tasks in your list bro.\n");
    }

    public static void addDeadline(String command) throws JohnnyException {
        List<String> l = Arrays.asList(command.split(" "));

        if (l.size() == 1) {
            throw new JohnnyException("What is your deadline bro?");
        }

        int i = l.indexOf("/by");

        if (i == -1) {
            throw new JohnnyException("When is your deadline by bro?");
        }

        Task task = new Deadline(String.join(" ", l.subList(1, i)),
                String.join(" ", l.subList(i + 1, l.size())));
        Johnny.list.add(task);
        System.out.println("Go get this done bro:");
        System.out.println(task);
        System.out.println("You still have " + Johnny.list.size() + " tasks in your list bro.\n");
    }

    public static void addEvent(String command) throws JohnnyException {
        List<String> l = Arrays.asList(command.split(" "));

        if (l.size() == 1) {
            throw new JohnnyException("What is your event bro?");
        }

        int i = l.indexOf("/from");

        if (i == -1) {
            throw new JohnnyException("When does your event start from bro?");
        }

        int j = l.indexOf("/to");

        if (j == -1) {
            throw new JohnnyException("When does your event last to bro?");
        }

        Task task = new Event(String.join(" ", l.subList(1, i)),
                String.join(" ", l.subList(i + 1, j)),
                String.join(" ", l.subList(j + 1, l.size())));
        Johnny.list.add(task);
        System.out.println("Go get this done bro:");
        System.out.println(task);
        System.out.println("You still have " + Johnny.list.size() + " tasks in your list bro.\n");
    }

    public static void delete(String command) throws JohnnyException {
        try {
            String[] arr = command.split(" ");

            if (arr.length == 1) {
                throw new JohnnyException("Which task am I supposed to delete bro?");
            } else if (arr.length > 2) {
                throw new JohnnyException("I can only delete 1 task bro.");
            }

            int index = Integer.parseInt(arr[1]) - 1;
            Task task = Johnny.list.remove(index);
            System.out.println("Task removed. Why so lazy bro?");
            System.out.println(task);
            System.out.println("You still have " + Johnny.list.size() + " tasks in your list bro.\n");
        } catch (NumberFormatException e) {
            throw new JohnnyException("Key in a number bro.");
        } catch (IndexOutOfBoundsException e) {
            throw new JohnnyException("This task does not exist bro.");
        }
    }

}
