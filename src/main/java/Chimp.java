import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Chimp {
    private ArrayList<String> list;
    Chimp() {
        list = new ArrayList<>();
    }
    public static void main(String[] args) {
        Chimp chimp = new Chimp();
        HashMap<String, String> phrases = chimp.getPhrases();
        chimp.say(phrases.get("greet"));

        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine();
        while (!inp.equalsIgnoreCase("bye")) {
            if (inp.equalsIgnoreCase("list")) {
                chimp.print();
            } else {
                chimp.say(inp);
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

        phrases.put("greet", greet);
        phrases.put("bye", bye);

        return phrases;
    }

    private void addToList(String task){
        this.list.add(task);
    }

    private void say(String phrase) {
        String divider = "____________________________________________________________\n";
        System.out.println(divider);
        System.out.print("added: ");
        System.out.println(phrase);
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
