import java.util.HashMap;
import java.util.Scanner;

public class Taylor {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Taylor");
        System.out.println("What can I do for you?");

        HashMap<Integer, Action> listing = new HashMap<>();
        Integer pos = 1;

        while(true) {
            Scanner type = new Scanner(System.in);
            String input = type.nextLine();

            if (input.equals("bye")) {
                break;

            } else if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                listing.forEach((key, value) -> {
                    System.out.println(key + ". " + "[" + value.getMarking() + "] " + value.getExecute());
                });

            } else if (input.contains("mark")) {
                String[] markwhat = input.split(" ");
                String what = markwhat[0];
                Integer num = Integer.valueOf(markwhat[1]);
                if (what.equals("mark")) {
                    listing.get(num).markIt();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + listing.get(num).getMarking() + "] " + listing.get(num).getExecute());
                } else if (what.equals("unmark")){
                    listing.get(num).unMark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("[" + listing.get(num).getMarking() + "] " + listing.get(num).getExecute());
                }

            } else {
                Action whatAction = new Action(input);
                listing.put(pos, whatAction);
                pos++;
                System.out.println("added: " + input);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
