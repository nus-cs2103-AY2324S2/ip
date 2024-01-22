import java.util.Scanner;

public class Checkbot {
    public static final String INDENTATION = "  ";
    private static final String SEPARATOR = INDENTATION + "____________________________________________________________\n";

    public static void main(String[] args) {
        TodoList todoList = new TodoList();

        String txt = SEPARATOR
                + INDENTATION + "Hello, I'm Checkbot, your personal assistant.\n"
                + INDENTATION + "What tasks do you have to do?\n"
                + SEPARATOR;
        System.out.println(txt);

        String input = "";
        while (!input.equals("bye")) {
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextLine();
            String toPrint = input;
            if (input.equals("bye")) {
                toPrint = "Goodbye!";
            } else if (input.equals("list")) {
                toPrint = todoList.toString();
            } else {
                toPrint = "added: " + toPrint;
                todoList.addTask(input);
            }
            txt = SEPARATOR
                    + INDENTATION + toPrint + "\n"
                    + SEPARATOR;
            System.out.println(txt);
        }
    }
}
