import java.util.*;

public class Duke {
    public static void main(String[] args) throws TaskNotFoundException, UnknownCommandException, InvalidSyntaxException {
        List taskList = new List(new ArrayList<>());
        System.out.println(
                "__________________________________________________________\n"
                        + "Hello! I'm KitchenSink!\n"
                        + "What can I do for you?\n"
                        + "__________________________________________________________\n"
        );
        Scanner sc = new Scanner(System.in);
        String input;
        while (true) {
            input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(
                        "__________________________________________________________\n"
                                + "Bye. Hope to see you again soon!\n"
                                + "__________________________________________________________"
                );
                break;
            }
            if (input.equalsIgnoreCase("list")) {
                taskList.displayTasks();
                continue;
            }
            if (input.split(" ")[0].equalsIgnoreCase("mark")) {
                if (input.split(" ").length != 2) {
                    throw new InvalidSyntaxException("mark");
                } else if (taskList.validTaskNum(Integer.parseInt(input.split(" ")[1]))) {
                    taskList.markTask(Integer.parseInt(input.split(" ")[1]) - 1);
                    continue;
                } else {
                    throw new TaskNotFoundException(taskList);
                }
            }
            if (input.split(" ")[0].equalsIgnoreCase("unmark")) {
                if (input.split(" ").length != 2) {
                    throw new InvalidSyntaxException("unmark");
                } else if (taskList.validTaskNum(Integer.parseInt(input.split(" ")[1]))) {
                    taskList.unmarkTask(Integer.parseInt(input.split(" ")[1]) - 1);
                    continue;
                } else {
                    throw new TaskNotFoundException(taskList);
                }
            }
            if (input.split(" ")[0].equalsIgnoreCase("todo")) {
                if (input.split(" ").length > 1) {
                    taskList.addTask(new ToDo(input.substring(5)));
                    continue;
                } else {
                    throw new InvalidSyntaxException("todo");
                }
            }
            if (input.split(" ")[0].equalsIgnoreCase("deadline")) {
                if (input.split(" ").length > 1 &&
                        input.split(" /by ").length == 2) {
                    taskList.addTask(new Deadline(
                            input.substring(9).split(" /by")[0],
                            input.split("/by ")[1]));
                    continue;
                } else {
                    throw new InvalidSyntaxException("deadline");
                }
            }
            if (input.split(" ")[0].equalsIgnoreCase("event")) {
                if (input.split(" ").length > 1 &&
                    input.split(" /by ").length == 2 &&
                    input.split(" /by ")[1].split(" /to ").length == 2 &&
                    input.split(" /to ").length == 2) {
                    taskList.addTask(new Event(
                            input.substring(6).split(" /from")[0],
                            input.split("/from ")[1].split(" /to")[0],
                            input.split("/to ")[1]));
                    continue;
                } else {
                    throw new InvalidSyntaxException("event");
                }
            }
            if (input.split(" ")[0].equalsIgnoreCase("delete")) {
                if (input.split(" ").length != 2) {
                    throw new InvalidSyntaxException("delete");
                } else if (taskList.validTaskNum(Integer.parseInt(input.split(" ")[1]))) {
                    taskList.deleteTask(Integer.parseInt(input.split(" ")[1]) - 1);
                    continue;
                } else {
                    throw new TaskNotFoundException(taskList);
                }
            }
            throw new UnknownCommandException();
        }
    }

}
