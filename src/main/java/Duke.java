import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Duke {
    private static final String lineBreak = "_______________________________________________________________________________";
    private static ArrayList<Task> lst = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        greet();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command;

        while (!(command = br.readLine()).equals("bye")) {
            try {
                Duke.checkCmd(command);
            } catch (InvalidCommandException | IncompleteCommandException ice){
                System.out.println(ice.getMessage());
            }
        }
        exit();
    }

    public static void checkCmd(String cmd) throws InvalidCommandException, IncompleteCommandException {
        String[] commandArr = cmd.split(" ");
        switch (commandArr[0]) {
            case "list":
                printLst();
                break;
            case "mark":
                if (commandArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, please input the list index for me to mark\n%s", lineBreak, lineBreak));
                }
                if (!commandArr[1].matches("\\d+")) {
                    throw new InvalidCommandException(String.format("%s\n Sorry, please input a valid list index for me to mark\n%s", lineBreak, lineBreak));
                }
                int toMark = Integer.parseInt(commandArr[1]);
                if (toMark > lst.size()) {
                    throw new InvalidCommandException(String.format("%s\n Sorry, please input a valid list index for me to mark\n%s", lineBreak, lineBreak));
                }
                markTask(toMark);
                break;
            case "unmark":
                if (commandArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, please input the list index for me to unmark\n%s", lineBreak, lineBreak));
                }
                if (!commandArr[1].matches("\\d+")) {
                    throw new InvalidCommandException(String.format("%s\n Sorry, please input a valid list index for me to unmark\n%s", lineBreak, lineBreak));
                }
                int toUnmark = Integer.parseInt(commandArr[1]);
                if (toUnmark > lst.size()) {
                    throw new InvalidCommandException(String.format("%s\n Sorry, please input a valid list index for me to unmark\n%s", lineBreak, lineBreak));
                }
                unmarkTask(toUnmark);
                break;
            case "todo":
                if (commandArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, the description of a todo cannot be empty :(\n%s", lineBreak, lineBreak));
                }
                String todo = cmd.substring(5);
                Task newTodo = new Todo(todo);
                addLst(newTodo);
                break;
            case "deadline":
                if (commandArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, the description and date of a deadline cannot be empty :(\n%s", lineBreak, lineBreak));
                }
                String[] deadlineArr = cmd.substring(9).split(" /by ");
                if (deadlineArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, the date of a deadline cannot be empty :(\n%s", lineBreak, lineBreak));
                }
                Task newDeadline = new Deadline(deadlineArr[0], deadlineArr[1]);
                addLst(newDeadline);
                break;
            case "event":
                if (commandArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, the description and start and end dates of an event cannot be empty :(\n%s", lineBreak, lineBreak));
                }
                String[] eventFromArr = cmd.substring(6).split(" /from ");
                if (eventFromArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, the start and end dates of an event cannot be empty :(\n%s", lineBreak, lineBreak));
                }
                String[] eventToArr = eventFromArr[1].split(" /to ");
                if (eventToArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, the end date of an event cannot be empty :(\n%s", lineBreak, lineBreak));
                }
                Task newEvent = new Event(eventFromArr[0], eventToArr[0], eventToArr[1]);
                addLst(newEvent);
                break;
            case "delete":
                if (commandArr.length == 1) {
                    throw new IncompleteCommandException(String.format("%s\n Sorry, please input the list index for me to delete\n%s", lineBreak, lineBreak));
                }
                if (!commandArr[1].matches("\\d+")) {
                    throw new InvalidCommandException(String.format("%s\n Sorry, please input a valid list index for me to delete\n%s", lineBreak, lineBreak));
                }
                int toDelete = Integer.parseInt(commandArr[1]);
                if (toDelete > lst.size()) {
                    throw new InvalidCommandException(String.format("%s\n Sorry, please input a valid list index for me to delete\n%s", lineBreak, lineBreak));
                }
                deleteTask(toDelete);
                break;
            default:
                throw new InvalidCommandException(String.format("%s\n Sorry, %s is not a valid command :(\n%s", lineBreak, cmd, lineBreak));
        }
    }

    public static void greet() {
        System.out.println(lineBreak);
        System.out.println(" Hello! I'm SnowBoy\n" + " What can I do for you?");
        System.out.println(lineBreak);
    }

    public static void addLst(Task newTask) {
        lst.add(newTask);
        System.out.println(lineBreak);
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + newTask.toString());
        System.out.println(" Now you have " + lst.size() + " tasks in the list.");
        System.out.println(lineBreak);
    }
    public static void printLst() {
        System.out.println(lineBreak);
        if (lst.isEmpty()) {
            System.out.println(" Whoops! Your list is empty :(");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < lst.size(); i++) {
                System.out.println(" " + (i + 1) + "." + lst.get(i).toString());
            }
        }
        System.out.println(lineBreak);
    }

    public static void markTask(int num) {
        lst.get(num - 1).markAsDone();
        System.out.println(lineBreak);
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + lst.get(num - 1).toString());
        System.out.println(lineBreak);
    }

    public static void unmarkTask(int num) {
        lst.get(num - 1).markAsUndone();
        System.out.println(lineBreak);
        System.out.println(" OK! I've marked this task as not done yet:");
        System.out.println("   " + lst.get(num - 1).toString());
        System.out.println(lineBreak);
    }

    public static void deleteTask(int num) {
        System.out.println(lineBreak);
        System.out.println(" Noted. I've removed this task:");
        System.out.println("   " + lst.get(num - 1).toString());
        lst.remove(num - 1);
        System.out.println(" Now you have " + lst.size() + " tasks in the list.");
        System.out.println(lineBreak);
    }

    public static void exit() {
        System.out.println(lineBreak);
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(lineBreak);
    }
}