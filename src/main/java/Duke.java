import java.util.*;

public class Duke {
    public static final String DIVIDER = "____________________________________________________________";
    public static final String WELCOME_TEXT = "Hello! I'm SlayBot\nWhat can I do for you?";
    public static final String BYE_TEXT = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        String logo = "\n" +
                "   _____ _             ____        _   \n" +
                "  / ____| |           |  _ \\      | |  \n" +
                " | (___ | | __ _ _   _| |_) | ___ | |_ \n" +
                "  \\___ \\| |/ _` | | | |  _ < / _ \\| __|\n" +
                "  ____) | | (_| | |_| | |_) | (_) | |_ \n" +
                " |_____/|_|\\__,_|\\__, |____/ \\___/ \\__|\n" +
                "                  __/ |                \n" +
                "                 |___/                 \n";

        System.out.println(logo);

        System.out.println(DIVIDER + "\n" + WELCOME_TEXT + "\n" + DIVIDER);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(DIVIDER + "\n" + BYE_TEXT + "\n" + DIVIDER);
                break;
            } else if (input.equals("list")) {
                int counter = 1;
                System.out.println(DIVIDER);
                for (String s : list) {
                    System.out.println(counter + ". " + s);
                    counter++;
                }
                System.out.println(DIVIDER);
            } else {
                list.add(input);
                System.out.println(DIVIDER + "\n added: " + input + "\n" + DIVIDER);
            }
        }

    }
}
