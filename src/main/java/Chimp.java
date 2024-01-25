import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Chimp {
    private static final String divider = "____________________________________________________________\n";
    private ArrayList<Task> list;
    Chimp() {
        list = new ArrayList<>();
    }
    public static void main(String[] args) throws InvalidParameterException{
        Chimp chimp = new Chimp();
        HashMap<String, String> phrases = chimp.getPhrases();
        chimp.say(phrases.get("greet"));

        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine();
        while (!inp.equalsIgnoreCase("bye")) {
            inp = inp.toLowerCase();
            String command = inp.split(" ")[0];
            String arg = null;
            if (inp.split(" ").length > 1) {
                // split at first space, and take everything on the right
                arg = inp.substring(inp.indexOf(' '), inp.length());
            }

            try {
                commandHandler(chimp, phrases, inp, command, arg);
            } catch (InvalidCommandException | CommandParseException e) {
                chimp.say(phrases.get("hoo") + " - " + e);
            } catch (IndexOutOfBoundsException e) {
                chimp.say(phrases.get("hoo") + " - " + "Invalid number of arguments provided");
            } finally {
                inp = sc.nextLine();
            }
        }
        chimp.say(phrases.get("bye"));
        sc.close();
    }

    private static void commandHandler(Chimp chimp, HashMap<String, String> phrases, String inp, String command, String arg) throws InvalidCommandException, CommandParseException {
        int num;
        switch (command) {
            case "list":
                chimp.print();
                break;
            case "mark":
                num = Integer.parseInt(arg);
                if (num < 1 || num > chimp.list.size())
                    throw new CommandParseException("mark must have number argument");
                chimp.list.get(num - 1).mark();
                chimp.say(phrases.get("mark"), chimp.list.get(num - 1));
                break;
            case "unmark":
                num = Integer.parseInt(arg);
                if (num < 1 || num > chimp.list.size())
                    throw new CommandParseException("unmark must have number argument");
                chimp.list.get(num - 1).unmark();
                chimp.say(phrases.get("unmark"), chimp.list.get(num - 1));
                break;
            case "todo": // TODO: Exception handling
                if (arg == null || arg.equals(""))
                    throw new CommandParseException("todo must have a desc");
                chimp.addToList(arg);
                chimp.say(chimp.list.get(chimp.list.size() - 1));
                break;
            case "event":
                String fromSubCommand = arg.split("/")[1];
                String from = fromSubCommand.substring(fromSubCommand.indexOf(' '));
                from = from.strip();
                if (from == null || from.equals(""))
                    throw new CommandParseException("deadline needs by date/time!");

                String toSubCommand = arg.split("/")[2];
                String to = toSubCommand.substring(toSubCommand.indexOf(' '));
                to = to.strip();
                if (to == null || to.equals(""))
                    throw new CommandParseException("deadline needs by date/time!");

                String text = arg.split("/")[0].strip();
                chimp.addToList(text, from, to);
                chimp.say(chimp.list.get(chimp.list.size() - 1));
                break;
            case "deadline":
                String bySubCommand = arg.split("/")[1];
                String by = bySubCommand.substring(3);
                by = by.strip();

                if (by == null || by.equals(""))
                    throw new CommandParseException("deadline needs by date/time!");

                // TODO: switch case scoping best practice?
                text = arg.split("/")[0].strip();
                chimp.addToList(text, by);
                chimp.say(chimp.list.get(chimp.list.size() - 1));
                break;
            default:
                throw new InvalidCommandException("command \"" + command + "\" is invalid");
        }
    }


    private HashMap<String, String> getPhrases() {
        HashMap<String, String> phrases = new HashMap<>();
        String greet = " Hello! I'm Chimp\n" +
                " What can I do for you?\n";
        String bye = "Bye. Hope to see you again soon!\n";
        String mark = "Nice! I've marked this task as done: \n";
        String unmark = "OK, I've marked this task as not done yet: \n";
        String hoo = "HOO-HOO-HOO-HOO, I don't know what that means\n";

        phrases.put("greet", greet);
        phrases.put("bye", bye);
        phrases.put("mark", mark);
        phrases.put("unmark", unmark);
        phrases.put("hoo", hoo);

        return phrases;
    }

    // TODO: Is this a maintainable way of doing things?
    private void addToList(String task){
        this.list.add(new Todo(task, TaskStatus.UNMARKED));
    }
    private void addToList(String task, String by) {
        this.list.add(new Deadline(task, TaskStatus.UNMARKED, by));
    }

    private void addToList(String task, String from, String to) {
        this.list.add(new Event(task, TaskStatus.UNMARKED, from, to));
    }

    private void say(String phrase) {
        System.out.println(divider);
        System.out.println(phrase);
        System.out.println(divider);
    }

    private void say(Task task) {
        System.out.println(divider);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
        System.out.println(divider);
    }

    private void say(String phrase, Task task) {
        System.out.println(divider);
        System.out.println(phrase);
        System.out.println(task);
        System.out.println(divider);
    }

    private void print() {
        System.out.println(divider);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println((i + 1) + ". " + this.list.get(i));
        }
        System.out.println(divider);
    }
}
