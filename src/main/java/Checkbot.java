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
            } else if (input.startsWith("todo")
                    || input.startsWith("deadline")
                    || input.startsWith("event")) {
                Task task;
                if (input.startsWith("todo")) {
                    task = new Todo(input.split("todo ")[1]);
                } else if (input.startsWith("deadline")) {
                    String[] splitString = input.split("deadline |\\/by ");
                    task = new Deadline(splitString[1], splitString[2]);
                } else {
                    String[] splitString = input.split("event |\\/(from|to) ");
                    task = new Event(splitString[1], splitString[2], splitString[3]);
                }
                toPrint = "I have added this task to the list:\n"
                        + INDENTATION + INDENTATION + task + "\n"
                        + INDENTATION + "You have now " + (todoList.getLength()+1) + " task" + (todoList.getLength() > 1 ? "s" : "") + " in the list.";
                todoList.addTask(task);
            } else {
                // TODO: Handle?
            }
            txt = SEPARATOR
                    + INDENTATION + toPrint + "\n"
                    + SEPARATOR;
            System.out.println(txt);
        }
    }
}
