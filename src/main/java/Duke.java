import java.util.HashMap;
import java.util.Scanner;
public class Duke {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<Integer, String> lsts = new HashMap<>();
        int counter = 0;

        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Emis!\n \tWhat can I do for you?");
        while (sc.hasNextLine()) {
            String user_input = sc.nextLine();
            if (user_input.equals("bye")) {
                exit();
            } else if (user_input.equals("list")) {
                print_list(lsts);
            } else {
                counter += 1;
                add(user_input, counter, lsts);
            }
        }
        exit();
    }

    public static void exit() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void echo(String userInput) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\t" + userInput);
        System.out.println("\t____________________________________________________________");
    }

    public static void print_list(HashMap<Integer, String> hs) {
        System.out.println("\t____________________________________________________________");
        for (Integer i : hs.keySet()) {
            System.out.println("\t" + i + ". " + hs.get(i));
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void add(String input, int counter, HashMap<Integer, String> hs) {
        hs.put(counter, input);
        System.out.println("\t____________________________________________________________");
        System.out.println("\tadded: " + input);
        System.out.println("\t____________________________________________________________");
    }
}
