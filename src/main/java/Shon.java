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
            if (input.equals("list")) {
                print(todo.getList());
            } else {
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
