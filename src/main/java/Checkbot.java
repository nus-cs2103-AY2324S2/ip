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
                toPrint = "Here is your todo list:\n" + INDENTATION + todoList;
            } else if (input.startsWith("mark ")) {
                int i = Integer.parseInt(input.split("mark ")[1]) - 1;
                todoList.markTask(i);
                toPrint = "Good job! I have marked this task as completed:\n"
                        + INDENTATION + todoList.getTask(i);
            } else if (input.startsWith("unmark ")) {
                int i = Integer.parseInt(input.split("unmark ")[1]) - 1;
                todoList.unmarkTask(i);
                toPrint = "Alright, I have marked this task as incomplete:\n"
                        + INDENTATION + todoList.getTask(i);
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
