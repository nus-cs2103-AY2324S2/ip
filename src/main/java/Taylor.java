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
                    System.out.println(key + ". " + value);
                });

            } else if (input.contains("mark")) {
                String[] markwhat = input.split(" ");
                String what = markwhat[0];
                Integer num = Integer.valueOf(markwhat[1]);
                if (what.equals("mark")) {
                    listing.get(num).markIt();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(listing.get(num));
                } else if (what.equals("unmark")) {
                    listing.get(num).unMark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(listing.get(num));
                }

            } else {
                System.out.println("Got it. I've added this task:");

                /**
                 *  Retrieve the action to be done
                 */
                String[] parts = input.split(" ", 2);
                String content = parts[1];

                switch (parts[0]) {
                    case "todo":
                        Todo task = new Todo(content);
                        listing.put(pos++, task);
                        System.out.println(task);

                        break;
                    case "deadline":
                        /**
                         * Retrieve the action and time
                         */
                        String[] splitter = content.split("/by");
                        Deadline dl = new Deadline(splitter[0], splitter[1]);
                        listing.put(pos++, dl);
                        System.out.println(dl);

                        break;
                    case "event":
                        /**
                         * Retrieve the action and time by first
                         * getting action, then split the 2 times
                         */
                        String[] splitter1 = content.split("/from");
                        String[] splitter2 = splitter1[1].split("/to");
                        Event eve = new Event(splitter1[0], splitter2[0], splitter2[1]);
                        listing.put(pos++, eve);
                        System.out.println(eve);
                        break;
                }
                System.out.println("Now you have " + listing.size() + " tasks in the list.");
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
