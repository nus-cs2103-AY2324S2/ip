import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    static ArrayList<Task> list = new ArrayList<>();


    public static void main(String[] args) {
        System.out.println(Constant.SEPERATOR);
        System.out.print("Hello! I'm Lex\nWhat can I do for you?\n");
        System.out.println(Constant.SEPERATOR);

        Scanner scanner = new Scanner(System.in);
        String input;

        event:
        while (scanner.hasNextLine()) {
            try {
                input = scanner.nextLine();
                String[] inputs = input.split(" ", 2);

                switch (inputs[0]) {
                    case "list":
                        for (int i = 0; i < list.size(); i++) {
                            System.out.println((i + 1) + ". " + list.get(i));
                        }
                        break;
                    case "todo":
                        if (inputs.length < 2) {
                            throw new Exception("OOPS!!! Formatting error.");
                        }

                        list.add(new Todo(inputs[1]));
                        printAddSuccessMessage();
                        break;
                    case "deadline":
                        String[] deadlineInputs = inputs[1].split(" /by ", 2);
                        if (deadlineInputs.length < 2) {
                            throw new Exception("OOPS!!! Formatting error.");
                        }

                        list.add(new Deadline(deadlineInputs[0], deadlineInputs[1]));
                        printAddSuccessMessage();
                        break;
                    case "event":
                        String[] eventInputs = inputs[1].split(" /from ", 2);
                        String[] eventTimeInputs = eventInputs[1].split(" /to ", 2);
                        if (eventTimeInputs.length < 2) {
                            throw new Exception("OOPS!!! Formatting error.");
                        }

                        list.add(new Event(eventInputs[0], eventTimeInputs[0], eventTimeInputs[1]));
                        printAddSuccessMessage();
                        break;
                    case "mark":
                        int index = Integer.parseInt(inputs[1]) - 1;
                        list.get(index).isDone(true);
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(index));
                        break;
                    case "unmark":
                        index = Integer.parseInt(inputs[1]) - 1;
                        list.get(index).isDone(false);
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(list.get(index));
                        break;
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!");
                        break event;
                    default:
                        throw new Exception("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                System.out.println(Constant.SEPERATOR);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(Constant.SEPERATOR);
            }
        }

        scanner.close();
    }

    public static void printAddSuccessMessage() {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + list.get(list.size() - 1));
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }
}
