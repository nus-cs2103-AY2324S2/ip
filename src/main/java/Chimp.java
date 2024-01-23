import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Chimp {
    public static void main(String[] args) {
        HashMap<String, String> phrases = Chimp.getPhrases();
        Chimp.say(phrases.get("greet"));

        Scanner sc = new Scanner(System.in);
        String inp = sc.nextLine();
        while (!inp.equalsIgnoreCase("bye")) {
            Chimp.say(inp);
            inp = sc.nextLine();
        }
        say(phrases.get("bye"));
        sc.close();
    }

    private static HashMap<String, String> getPhrases() {
        HashMap<String, String> phrases = new HashMap<>();
        String greet = " Hello! I'm Chimp\n" +
                " What can I do for you?\n";
        String bye = "Bye. Hope to see you again soon!\n";

        phrases.put("greet", greet);
        phrases.put("bye", bye);

        return phrases;
    }

    private static void say(String phrase) {
        String divider = "____________________________________________________________\n";
        System.out.println(divider);
        System.out.println(phrase);
        System.out.println(divider);
    }
}
