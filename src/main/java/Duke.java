import java.util.*;

public class Duke {
    public static final String DIVIDER = "____________________________________________________________";
    public static final String WELCOME_TEXT = "Hello! I'm SlayBot\nWhat can I do for you?";
    public static final String BYE_TEXT = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Task> list = new ArrayList<>();
        boolean flag = true;

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

        while (flag) {
            String input = sc.nextLine();
            String[] arr = input.split(" ");

            switch (arr[0]) {
                case "bye":
                    flag = false;
                    System.out.println(DIVIDER + "\n" + BYE_TEXT + "\n" + DIVIDER);
                    break;
                case "list":
                    int counter = 1;
                    System.out.println(DIVIDER);
                    for (Task t : list) {
                        String mark = t.getMarked() ? "X" : " ";
                        System.out.println(counter + ". [" + mark + "] " + t.getTitle());
                        counter++;
                    }
                    System.out.println(DIVIDER);
                    break;
                case "mark":
                    Task t = list.get(Integer.parseInt(arr[1]) - 1);
                    t.setMarked(true);
                    String mark = t.getMarked() ? "X" : " ";
                    System.out.println(DIVIDER + "\nNice! I've marked this task as done:" );
                    System.out.println("[" + mark + "] " + t.getTitle() + "\n" + DIVIDER);
                    break;
                case "unmark":
                    Task t1 = list.get(Integer.parseInt(arr[1]) - 1);
                    t1.setMarked(false);
                    String mark1 = t1.getMarked() ? "X" : " ";
                    System.out.println(DIVIDER + "\nOK, I've marked this task as not done yet:" );
                    System.out.println("[" + mark1 + "] " + t1.getTitle() + "\n" + DIVIDER);
                    break;
                default:
                    list.add(new Task(arr[0]));
                    System.out.println(DIVIDER + "\n added: " + input + "\n" + DIVIDER);
                    break;
            }
        }
    }
}
