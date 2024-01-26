import java.util.Scanner;

public class Shon {

    private final static String LINE = "    _______________" +
            "_____________________________________________";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TodoList list = new TodoList();

        print("Hello! I'm Shon", "What can I do for you?");
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            String[] s = input.split(" ", 2);
            String action = s[0];
            switch (action) {
                case "list":
                    print(list.getList());
                    break;
                case "mark":
                    print(list.mark(Integer.parseInt(s[1])));
                    break;
                case "unmark":
                    print(list.unmark(Integer.parseInt(s[1])));
                    break;
                case "todo":
                    print(list.addTodo(s[1]));
                    break;
                case "deadline":
                    String[] d = s[1].split(" /by ", 2);
                    print(list.addDeadline(d[0], d[1]));
                    break;
                case "event":
                    String[] e = s[1].split(" /from ", 2);
                    String[] duration = e[1].split(" /to ", 2);
                    print(list.addEvent(e[0], duration[0], duration[1]));
                    break;
                default:

            }

            input = scanner.nextLine();
        }
        print("Bye. Hope to see you again soon!");
    }

    private static void print(String... messages) {
        System.out.println(LINE);
        for (String msg : messages) {
            System.out.println("     " + msg);
        }
        System.out.println(LINE);
        System.out.println();
    }
}
