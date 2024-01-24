import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Chimp {
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
            int num;
            switch (command) {
                case "list":
                    chimp.print();
                    break;
                case "mark":
                    num = Integer.parseInt(arg);
                    if (num < 1 || num > chimp.list.size())
                        throw new InvalidParameterException();
                    chimp.list.get(num - 1).mark();
                    chimp.say(phrases.get("mark"), chimp.list.get(num - 1));
                    break;
                case "unmark":
                    num = Integer.parseInt(arg);
                    if (num < 1 || num > chimp.list.size())
                        throw new InvalidParameterException();
                    chimp.list.get(num - 1).unmark();
                    chimp.say(phrases.get("unmark"), chimp.list.get(num - 1));
                    break;
                case "todo":
                    chimp.addToList(inp);
                    chimp.say(chimp.list.get(chimp.list.size() - 1));
                    break;
                case "event":
                    String fromSubCommand = arg.split("/")[1];
                    String from = fromSubCommand.substring(fromSubCommand.indexOf(' '), fromSubCommand.length());
                    from = from.strip();
                    String toSubCommand = arg.split("/")[2];
                    String to = toSubCommand.substring(toSubCommand.indexOf(' '), toSubCommand.length());
                    to = to.strip();
                    chimp.addToList(arg.split("/")[0].strip(), from, to);
                    chimp.say(chimp.list.get(chimp.list.size() - 1));
                    break;
                case "deadline":
                    String bySubCommand = arg.split("/")[1];
                    String by = bySubCommand.substring(3, bySubCommand.length());
                    by = by.strip();
                    chimp.addToList(arg.split("/")[0].strip(), by);
                    chimp.say(chimp.list.get(chimp.list.size() - 1));
                    break;
                default:
                    chimp.addToList(inp);
                    chimp.say(chimp.list.get(chimp.list.size() - 1));
            }
            inp = sc.nextLine();
        }
        chimp.say(phrases.get("bye"));
        sc.close();
    }

    private HashMap<String, String> getPhrases() {
        HashMap<String, String> phrases = new HashMap<>();
        String greet = " Hello! I'm Chimp\n" +
                " What can I do for you?\n";
        String bye = "Bye. Hope to see you again soon!\n";
        String mark = "Nice! I've marked this task as done: \n";
        String unmark = "OK, I've marked this task as not done yet: \n";

        phrases.put("greet", greet);
        phrases.put("bye", bye);
        phrases.put("mark", mark);
        phrases.put("unmark", unmark);

        return phrases;
    }

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
        String divider = "____________________________________________________________\n";
        System.out.println(divider);
        System.out.println(phrase);
        System.out.println(divider);
    }

    private void say(Task task) {
        String divider = "____________________________________________________________\n";
        System.out.println(divider);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
        System.out.println(divider);
    }

    private void say(String phrase, Task task) {
        String divider = "____________________________________________________________\n";
        System.out.println(divider);
        System.out.println(phrase);
        System.out.println(task);
        System.out.println(divider);
    }

    private void print() {
        String divider = "____________________________________________________________\n";
        System.out.println(divider);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println((i + 1) + ". " + this.list.get(i));
        }
        System.out.println(divider);
    }
}
