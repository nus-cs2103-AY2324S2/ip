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
            Integer arg = null;
            if (inp.split(" ").length > 1) {
                arg = Integer.parseInt(inp.split(" ")[1]);
                if (arg < 1 || arg > chimp.list.size())
                    throw new InvalidParameterException();
            }
            switch (command) {
                case "list":
                    chimp.print();
                    break;
                case "mark":
                    chimp.list.get(arg - 1).mark();
                    chimp.say(phrases.get("mark"), chimp.list.get(arg - 1));
                    break;
                case "unmark":
                    chimp.list.get(arg - 1).unmark();
                    chimp.say(phrases.get("unmark"), chimp.list.get(arg - 1));
                    break;
                default:
                    chimp.say("added: " + inp);
                    chimp.addToList(inp);
            }
            inp = sc.nextLine();
        }
        chimp.bye();
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
        this.list.add(new Task(task, TaskStatus.UNMARKED));
    }

    private void say(String phrase) {
        String divider = "____________________________________________________________\n";
        System.out.println(divider);
        System.out.println(phrase);
        System.out.println(divider);
    }

    private void say(String phrase, Task task) {
        String divider = "____________________________________________________________\n";
        System.out.println(divider);
        System.out.println(phrase);
        System.out.println(task);
        System.out.println(divider);
    }

    // Treat bye as a special case for extensibility
    private void bye() {
        String divider = "____________________________________________________________\n";
        System.out.println(divider);
        String bye = "Bye. Hope to see you again soon!\n";
        System.out.println(bye);
        System.out.println(divider);
    }

    private void print() {
        String divider = "____________________________________________________________\n";
        System.out.println(divider);
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println((i + 1) + ". " + this.list.get(i));
        }
        System.out.println(divider);
    }
}
