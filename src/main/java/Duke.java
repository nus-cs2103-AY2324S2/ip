import java.util.*;

public class Duke {
    public static void main(String[] args) {
        List taskList = new List(new ArrayList<>());
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(
                "__________________________________________________________\n"
                        + "Hello! I'm KitchenSink! \n"
                        + "What can I do for you? \n"
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
                                + "__________________________________________________________\n"
                );
                break;
            }
            if (input.equalsIgnoreCase("list")) {
                taskList.displayTasks();
                continue;
            }
            taskList.addTask(new Task(input));
            System.out.println(
                    "__________________________________________________________\n"
                            + "added: " + input
                            + "\n__________________________________________________________\n"
            );
        }
    }

}
