import java.util.Scanner;

public class Shon {

    private final static String LINE = "    _______________" +
            "_____________________________________________";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Todo todo = new Todo();

        print("Hello! I'm Shon", "What can I do for you?");
        String input = scanner.nextLine();
        while (!input.equals("bye")) {

            String[] s = input.split(" ", 2);
            String action = s[0];
            switch (action) {
                case "list":
                    print(todo.getList());
                    break;
                case "mark":
                    print(todo.mark(Integer.parseInt(s[1])));
                    break;
                case "unmark":
                    print(todo.unmark(Integer.parseInt(s[1])));
                    break;
                default:
                    print(todo.add(input));
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
