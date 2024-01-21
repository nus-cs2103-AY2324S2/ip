import java.util.*;

public class Duke {
    public static void main(String[] args) {
        List taskList = new List(new ArrayList<>());
        HashSet<Integer> validTaskNum = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            validTaskNum.add(i + 1);
        }
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
            if (input.split(" ")[0].equalsIgnoreCase("mark") &&
                    input.split(" ").length == 2 &&
                    taskList.validTaskNum(Integer.parseInt(input.split(" ")[1]))) {
                taskList.markTask(Integer.parseInt(input.split(" ")[1]) - 1);
                continue;
            }
            if (input.split(" ")[0].equalsIgnoreCase("unmark") &&
                    input.split(" ").length == 2 &&
                    taskList.validTaskNum(Integer.parseInt(input.split(" ")[1]))) {
                taskList.unmarkTask(Integer.parseInt(input.split(" ")[1]) - 1);
                continue;
            }
//            if (input.equalsIgnoreCase("")) {
//                if (taskList.validTaskNum()) {
//                    taskList.
//                } else {
//                    System.out.println("Invalid task.");
//                }
//                continue;
//            }
            taskList.addTask(new Task(input));
            System.out.println(
                    "__________________________________________________________\n"
                            + "added: " + input
                            + "\n__________________________________________________________\n"
            );
        }
    }

}
